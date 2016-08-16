package org.scalalang.tutorial.tour

/**
  * Created by Administrator on 2016-08-16.
  */
object XMLTest1 extends scala.App{
  val page =
    <html>
       <head>
         <title>Hello XHTML world</title>
       </head>
        <body>
          <h1>Hello world</h1>
          <p><a href="scala-lang.org">talks xml</a></p>
        </body>
    </html>
  println(page.toString)

}
