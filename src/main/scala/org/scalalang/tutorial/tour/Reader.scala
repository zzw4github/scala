package org.scalalang.tutorial.tour

/**
  * Created by infosea on 2016-08-17.
  */
import java.io._
class Reader(fname: String) {
  private val in = new BufferedReader(new FileReader(fname))
  @throws(classOf[IOException])
  def read() = in.read()
}
