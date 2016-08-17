package org.scalalang.tutorial.tour

/**
  * Created by Administrator on 2016-08-17.
  */
class Graph {
  class Node {
    var connectedNodes: List[Node] = Nil
    def connectTo(node: Node) {
      if (connectedNodes.find(node.equals).isEmpty) {
        connectedNodes = node :: connectedNodes
      }
    }
  }
  var nodes: List[Node] = Nil
  def newNode: Node = {
    val res = new Node
    nodes = res :: nodes
    res
  }
}

object GraphTest2 extends scala.App {
  val g = new Graph
  val n1 = g.newNode
  val n2 = g.newNode
  val n3 = g.newNode
  n1.connectTo(n2)
  n3.connectTo(n1)
}

//object IllegalGraphTest extends scala.App {
//  val g: Graph = new Graph
//  val n1: g.Node = g.newNode
//  val n2: g.Node = g.newNode
//  n1.connectTo(n2)      // legal
//  val h: Graph = new Graph
//  val n3: h.Node = h.newNode
//  n1.connectTo(n3)      // illegal!
//}


class Graph2 {
  class Node {
    var connectedNodes: List[Graph2#Node] = Nil
    def connectTo(node: Graph2#Node) {
      if (connectedNodes.find(node.equals).isEmpty) {
        connectedNodes = node :: connectedNodes
      }
    }
  }
  var nodes: List[Node] = Nil
  def newNode: Node = {
    val res = new Node
    nodes = res :: nodes
    res
  }
}

object GraphTest1 extends scala.App {
  val g: Graph2 = new Graph2
  val n1: g.Node = g.newNode
  val n2: g.Node = g.newNode
  n1.connectTo(n2)
  val h: Graph2 = new Graph2
  val n3: h.Node = h.newNode

  n1.connectTo(n3)
}

/**
  * Please note that this program doesn’t allow us to attach a node to two different graphs.
  * If we want to remove this restriction as well, we have to change the type of variable nodes to Graph#Node
  * nodes: List[Graph#Node] = Nil
  */

