package org.scalalang.tutorial.tour

/**
  * Created by infosea on 2016-08-17.
  */
object LocalTypeInference extends scala.App{
  val x = 1 + 2 * 3         // the type of x is Int
  val y = x.toString()      // the type of y is String
  def succ(x: Int) = x + 1  // method succ returns Int values

//  def fac0(n: Int) = if (n == 0) 1 else n * fac0(n - 1) //For recursive methods, the compiler is not able to infer a result type
  def fac(n: Int):Int = if (n == 0) 1 else n * fac(n - 1)

  case class MyPair[A, B](x: A, y: B);

  def id[T](x: T) = x
  val p = MyPair(1, "scala") // type: MyPair[Int, String]
  val q = id(1)              // type: Int
}
