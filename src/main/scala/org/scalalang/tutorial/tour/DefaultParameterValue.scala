package org.scalalang.tutorial.tour

/**
  * Created by infosea on 2016-08-17.
  */
class HashMap[K,V] (initailCapacity:Int = 16, loadFactor: Float = 0.75f) {

}

object DefaultParameterValue extends scala.App {
  val ml = new HashMap[String, Int]

  val m2 = new HashMap[String,Int](20)

  val m3 = new HashMap[String,Int](20, 0.8f)

  val m4 = new HashMap[String, Int](loadFactor = 0.8f)

//  val m5 = new HashMap[String, Int](0.8f) // type misMatch
}
