package org.scalalang.tutorial.tour

/**
  * Created by Administrator on 2016-08-16.
  */

abstract class Term
case class Var(name: String) extends Term
case class Fun(arg: String, body: Term) extends Term
case class App(f: Term, v: Term) extends Term

object CaseClassTest  extends scala.App{
  Fun("x", Fun("y", App(Var("x"), Var("y"))))
  var x = Var("x")
  println(x.name)

  var x1 = Var("x")
  var x2 = Var("x")
  var y1 = Var("y")

  println("" + x1 + " == " + x2 + " => " + (x1 == x2))
  println("" + x1 + " == " + y1 + " => " + (x1 == y1))

  def printTerm(term: Term): Unit = {
    term match {
      case Var(n) => println(n)
      case Fun(x, b) => print("^" + x + "."); printTerm(b)
      case App(f, v) =>
        print("(")
        printTerm(f)
        print(" ")
        printTerm(v)
        print(")")
    }
  }

  def isIdentityFun(term: Term): Boolean = term match {
    case Fun(x, Var(y)) if x == y => true
    case _ => false
  }
  val id = Fun("x", Var("x"))
  val t = Fun("x", Fun("y", App(Var("x"), Var("y"))))
  printTerm(t)
  println
  println(isIdentityFun(id))
  println(isIdentityFun(t))
}
