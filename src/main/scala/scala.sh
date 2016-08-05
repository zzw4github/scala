#!/bin/sh
exec scala "$0" "$@"
!#

case class Person(name:String)
import java.lang.io
object HelloWorld {
        def main(args:Array[String]){
               // require(args.length==1)
               // val al = Person(args(0))
               // println(al)

                "ls -al .." #> new File("output.txt")
        }
}

HelloWorld.main(args)