package org.scalalang.tutorial.tour

/**
  * Created by Administrator on 2016-08-16.
  */
class AnonymousFunction {

}

object AnonymousFunction extends scala.App {
  val aFunc = new Function1[Int, Int]{
    def apply(x: Int): Int = x + 1
  };

  val aFunc1 = (x: Int) => {x + 1};

  println(aFunc1(1))
  println(aFunc1)

  val aFunc2 = () => {System.getProperty("user.dir")}

  println(aFunc2())
  println(aFunc2)
}
