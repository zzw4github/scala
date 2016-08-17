package org.scalalang.tutorial.tour

/**
  * Created by infosea on 2016-08-17.
  */
object NamedParameter extends scala.App {
  def printName(first:String, last:String) = {
    println(first + " " + last)
  }

  printName("zzw", "zzw")
  printName(first ="zzw", last = "zzw")
  printName(last ="z", first = "zz")

  def printNameDefault(first: String = "z", last: String = "zw") = {
    println(first + " " + last);
  }

  printNameDefault(last = "zq")
}
