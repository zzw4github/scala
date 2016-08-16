package org.scalalang.tutorial.tour

/**
  * Created by Administrator on 2016-08-16.
  */
class SingletonObject {

}


object Blah {
  def sum(l: List[Int]): Int = l.sum
}

class IntPair(val x: Int, val y: Int)

object InPair {
  import math.Ordering

  implicit def ipord: Ordering[IntPair] =
    Ordering.by(ip => (ip.x, ip.y))
}
