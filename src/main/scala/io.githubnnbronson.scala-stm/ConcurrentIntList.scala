package io.githubnnbronson.scala

import scala.concurrent.stm._

/**
  * Created by Administrator on 2016-08-06.
  */
class ConcurrentIntList {
  private class Node(val elem: Int, prev0: Node, next0: Node) {
    val isHeader = prev0 == null
    val prev = Ref( if (isHeader) this else prev0)
    val next = Ref( if (isHeader) this else next0)
  }

  def addLast (elem: Int): Unit = {
    atomic { implicit txn =>
      val p = header.prev()
      val newNode = new Node(elem, p, header)
      p.next() = newNode
      header.prev() = newNode
    }
  }

  def addLast(e1: Int, e2: Int, elems: Int*): Unit = {
    atomic { implicit txn =>
      addLast(e1)
      addLast(e2)
      elems foreach { addLast(_)
      }
    }
  }

  def isEmpty = header.next.single() == header

  def removeFirst(): Int = atomic { implicit txn=>
    val n = header.next()
    if (n == header)
      retry
    val nn = n.next()
    header.next() = nn
    nn.prev() = header
    n.elem
  }

  def maybeRemoveFirst(): Option[Int] = {
    atomic { implicit txn =>
      Some(removeFirst())
    } orAtomic { implicit  txn =>
      None
    }
  }
  def badToString: String = {
    val buf = new StringBuilder("ConcurrentIntList(")
    atomic { implicit txn =>
      var n = header.next()
      while (n != header) {
        buf ++= n.elem.toString
        n = n.next()
        if (n != header) buf ++= ","
      }
    }
    buf ++= ")"
    buf toString
  }

  override def toString: String = {
    atomic { implicit txn =>
      val buf = new StringBuilder("ConcurrentIntList(")
      var n = header.next()
      while (n != header) {
        buf ++= n.elem.toString
        n = n.next()
        if (n != header) buf ++= ","
      }
      buf ++= ")"
      buf toString
    }
  }
  private val header = new Node(-1, null, null)

}

object ConcurrentIntList {
  def select(stacks: ConcurrentIntList*): (ConcurrentIntList, Int) = {
    atomic { implicit txn =>
      for (s <- stacks) {
        s.maybeRemoveFirst() match {
          case Some(e) => return (s -> e)
//          case None => _
        }
      }
      retry
    }
  }
}

object Test extends App {
   val intList: ConcurrentIntList = new ConcurrentIntList
  intList.addLast(1, 3, 3, 4, 5)
  System.out.println(intList.toString)
}
