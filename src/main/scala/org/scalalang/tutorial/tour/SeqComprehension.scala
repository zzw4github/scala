package org.scalalang.tutorial.tour

/**
  * Created by Administrator on 2016-08-17.
  */
object SeqComprehension extends scala.App{
 def even(from: Int, to: Int): List[Int] =
   for (i <- List.range(from, to) if i % 2 == 0) yield i
  Console.println(even(0, 20))

  def foo(n: Int, v: Int) =
    for ( i <- 0 until n;
          j <- i until n if i + j == v) yield
      Pair(i, j);
  foo(20, 32) foreach {
    case (i, j) => println("(" + i + ", " + j + ")")
  }

  for( i <- Iterator.range(0, 20);
       j <- Iterator.range(i, 20) if i + j == 32)
    println("(" + i + ", " + j + ")")

}




