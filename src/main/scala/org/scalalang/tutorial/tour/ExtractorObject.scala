package org.scalalang.tutorial.tour

/**
  * Created by Administrator on 2016-08-17.
  */
class ExtractorObject {

}

object Twice {
  def apply(x: Int): Int = x * 2
  def unapply(z: Int): Option[Int] = if (z % 2 == 0) Some(z / 2) else None
}

object TwiceTest extends scala.App {
  val x = Twice(21)
  x match {
    case Twice(n) => Console.println(n) // prints 21
  }
}
