package learning.concurrent.programming

import akka.actor.{ActorSystem, Props, Actor}

/**
  * Created by infosea on 2016-08-03.
  */
class Router extends Actor {
  var i = 0
  val children = for (_ <- 0 until 4) yield context.actorOf(Props[StringPrinter])
  def receive = {
    case msg =>
      children(i) forward msg
      i = (i + 1) % 4
  }

}

object Router extends App {
  lazy val ourSystem = ActorSystem("ourSystem")
  val router = ourSystem.actorOf(Props[Router], "router")
  router ! "Hola"
  router ! "Hey"
  router ! "Hi"
  router ! "Hello"
  Thread sleep 1000
  ourSystem.terminate()
}
