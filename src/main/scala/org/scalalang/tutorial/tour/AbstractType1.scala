package org.scalalang.tutorial.tour

/**
  * Created by Administrator on 2016-08-17.
  */
abstract class Buffer1[+T] {
  val element: T
}
abstract class SeqBuffer1[U, +T <: Seq[U]] extends Buffer1[T] {
  def length = element.length
}
object AbstractTypeTest2 extends scala.App {
  def newIntSeqBuf1(e1: Int, e2: Int): SeqBuffer1[Int, Seq[Int]] =
    new SeqBuffer1[Int, List[Int]] {
      val element = List(e1, e2)
    }
  val buf = newIntSeqBuf1(7, 8)
  println("length = " + buf.length)
  println("content = " + buf.element)
}
