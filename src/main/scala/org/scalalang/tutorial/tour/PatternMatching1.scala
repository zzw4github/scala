package org.scalalang.tutorial.tour

/**
  * Created by Administrator on 2016-08-16.
  */

object MatchTest1 extends scala.App {
  def matchTest(x: Int): String = x match {
    case 1 => "one"
    case 2 => "two"
    case _ => "many"
  }
  println(matchTest(3))
}
