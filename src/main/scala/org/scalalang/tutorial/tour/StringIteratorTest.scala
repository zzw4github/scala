package org.scalalang.tutorial.tour

/**
  * Created by Administrator on 2016-08-16.
  */
abstract class AbsIterator {
  type T
  def hasNext: Boolean
  def next: T
}

trait RichIterator extends AbsIterator {
  def foreach(f: T => Unit): Unit = {
    while (hasNext) f(next)
  }
}

class StringIterator(s: String) extends AbsIterator {
  type T = Char
  private var i = 0
  def hasNext = i < s.length()
  def next = { val ch = s charAt i; i += 1; ch}
}
object StringIteratorTest {
  def main(args: Array[String]): Unit = {
    class Iter extends StringIterator(args(0)) with RichIterator
      val iter = new Iter
      iter foreach println

  }
}
