package org.scalalang.tutorial.tour

/**
  * Created by Administrator on 2016-08-16.
  */
class Decorator(left: String, right: String) {
  def layout[A](x: A) = left + x.toString + right
}

object FunTest extends scala.App {
  def apply(f: Int => String, v: Int) = f(v)
  val decorator = new Decorator("[", "]")
  println(apply(decorator.layout, 7))
}
