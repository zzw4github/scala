package org.scalalang.tutorial.tour

/**
  * Created by Administrator on 2016-08-16.
  */

object MatchTest2 extends scala.App {
  def matchTest(x: Any): Any = x match {
    case 1 => "one"
    case "two" => 2
    case y: Int => "scala.Int"
  }
  println(matchTest("two"))
  println(matchTest(2))
}
