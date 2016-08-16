package org.scalalang.tutorial.tour

/**
  * Created by Administrator on 2016-08-16.
  */
object XMLTest2 extends scala.App{
  import scala.xml._
  val df = java.text.DateFormat.getDateInstance()
  val dateString = df.format(new java.util.Date())
  def theDate(name: String) =
    <dateMsg addressedTo ={ name }>
      Hello, { name }! Today is { dateString }
    </dateMsg>;
  println(theDate("zhaoziwen").toString())

}
