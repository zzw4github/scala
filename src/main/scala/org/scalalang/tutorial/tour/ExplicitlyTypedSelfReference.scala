package org.scalalang.tutorial.tour

/**
  * Created by infosea on 2016-08-17.
  */
abstract class Graph1 {
  type Edge
  type Node <: NodeIntf
  abstract class NodeIntf {
    def connectWith(node: Node): Edge
  }
  def nodes: List[Node]
  def edges: List[Edge]
  def addNode: Node
}

/**
  * the program above is not well-formed and the Scala compiler will issue an error message
  */
//abstract class DirectedGraph extends Graph {
//  type Edge <: EdgeImpl
//  class EdgeImpl(origin: Node, dest: Node) {
//    def from = origin
//    def to = dest
//  }
//  class NodeImpl extends NodeIntf {
//    def connectWith(node: Node): Edge = {
//      val edge = newEdge(this, node)
//      edges = edge :: edges
//      edge
//    }
//  }
//  protected def newNode: Node
//  protected def newEdge(from: Node, to: Node): Edge
//  var nodes: List[Node] = Nil
//  var edges: List[Edge] = Nil
//  def addNode: Node = {
//    val node = newNode
//    nodes = node :: nodes
//    node
//  }
//}

abstract class DirectedGraph extends Graph1 {
  type Edge <: EdgeImpl
  class EdgeImpl(origin: Node, dest: Node) {
    def from = origin
    def to = dest
  }

  /**
    * We can use this mechanism for fixing our code above. The explicit self type is specified within the body
    * of the class DirectedGraph.
    */
  class NodeImpl extends NodeIntf {
    self: Node =>
    def connectWith(node: Node): Edge = {
      val edge = newEdge(this, node)  // now legal
      edges = edge :: edges
      edge
    }
  }
  protected def newNode: Node
  protected def newEdge(from: Node, to: Node): Edge
  var nodes: List[Node] = Nil
  var edges: List[Edge] = Nil
  def addNode: Node = {
    val node = newNode
    nodes = node :: nodes
    node
  }
}

class ConcreteDirectedGraph extends DirectedGraph {
  type Edge = EdgeImpl
  type Node = NodeImpl
  protected def newNode: Node = new NodeImpl
  protected def newEdge(f: Node, t: Node): Edge =
    new EdgeImpl(f, t)
}

object GraphTest extends scala.App {
  val g: Graph1 = new ConcreteDirectedGraph
  val n1 = g.addNode
  val n2 = g.addNode
  val n3 = g.addNode
  n1.connectWith(n2)
  n2.connectWith(n3)
  n1.connectWith(n3)
}