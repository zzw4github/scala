package org.scalalang.tutorial.tour

/**
  * Created by infosea on 2016-08-17.
  */
object PolymorphicMethod  extends scala.App{
  def dup[T] (x: T, n: Int): List[T] ={
    if (n == 0)
      Nil
    else
      x :: dup(x, n - 1)
  }

  println(dup[Int](3, 4))
  println(dup(3, 4))
  println(dup("three", 3))
  println(dup[List[Int]](List(1), 4))
}
