package org.learningconcurrency.ch1

/**
  * Created by infosea on 2016-08-04.
  */
trait Logging {
  def log(s: String): Unit
  def warn(s: String) = log("WARN: " + s)
  def error(s: String) = log("ERROR: " + s)
}
object PrintLogging extends Logging {
  def log(s: String) = println(s)
}