package org.scalalang.tutorial.tour

/**
  * Created by infosea on 2016-08-17.
  */
object TargetTest1 extends scala.App {
  def whileLoop(cond: => Boolean)(body: => Unit): Unit = {
    if (cond)
      body
      whileLoop(cond)(body)
  }

  var i = 10
  whileLoop(i > 0){
    println(i)
    i -=1
  }
}

object TargetTest2 extends  scala.App {
  def loop(body: => Unit): LoopUnlessCond =
    new LoopUnlessCond(body)
  protected class LoopUnlessCond(body: => Unit) {
    def unless(cond: => Boolean): Unit = {
      body
      if (!cond) unless(cond)
    }
  }

  var i =10;
  loop {
    println("i= " + i)
    i -= 1
  }unless(i == 0)
}