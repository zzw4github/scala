package org.scalalang.tutorial.tour

/**
  * Created by Administrator on 2016-08-16.
  */
object RegEl extends scala.App{
  def containsScala(x: String): Boolean = {
    val z: Seq[Char] = x
    z match {
      case Seq('s', 'c', 'a', 'l', 'a', rest @ _*) =>
        println("rest is " + rest)
        true
      case Seq(_*) =>
        false
    }
  }
  println(containsScala("###General RegExp patterns temporarily retracted from Scala###"))
  println(containsScala("scala scala"))
}
