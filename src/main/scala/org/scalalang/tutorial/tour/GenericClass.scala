package org.scalalang.tutorial.tour

/**
  * Created by Administrator on 2016-08-17.
  */
class Stacks[T] {
  var elems: List[T] = Nil
  def push(x: T) { elems = x :: elems}
  def top: T = elems.head
  def pop() { elems = elems.tail}
}

object GenericClass extends scala.App {
  val stack = new Stacks[Int]
  stack.push(1)
  stack.push('a')
  println(stack.top)
  stack.pop()
  println(stack.top)
}
