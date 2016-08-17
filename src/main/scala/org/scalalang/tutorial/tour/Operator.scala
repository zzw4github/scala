package org.scalalang.tutorial.tour

/**
  * Created by infosea on 2016-08-17.
  */
class MyBool(x: Boolean) {
  def and(that: MyBool) = if (x) that else this
  def or(that: MyBool): MyBool = if(x) this else that
  def negate: MyBool = new MyBool(!x)

  def not(x: MyBool) = x negate ; // semicolon required here
  def xor(x: MyBool, y: MyBool) = (x or y) and not(x and y)

//  more traditional object-oriented programming language syntax
  def not1(x: MyBool) = x.negate; // semicolon required here
  def xor1(x: MyBool, y: MyBool) = x.or(y).and(x.and(y).negate)
}
