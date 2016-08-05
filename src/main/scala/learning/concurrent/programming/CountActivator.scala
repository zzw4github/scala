package learning.concurrent.programming

import akka.actor.{ActorSystem, Props, Actor}
import akka.event.Logging
/**
  * Created by infosea on 2016-08-03.
  */
class CountdownActor1 extends Actor {
  val log = Logging(context.system, this)
  var n = 10
  def receive = if (n > 0) { // never do this
    case "count" =>
      log.info(s"n = $n")
      n -= 1
  } else PartialFunction.empty
}

class CountdownActor extends Actor {
  val log = Logging(context.system, this)
  var n = 10
  def counting: Actor.Receive = {
    case "count" =>
      n -= 1
      log.info(s"n = $n")
      if (n == 0) context.become(done)
  }
  def done = PartialFunction.empty
  def receive = counting
}

object ActorsCountdown extends App {
  lazy val ourSystem =
    ActorSystem("OurExampleSystem")
  val countdown =
    ourSystem.actorOf(Props[CountdownActor])
  for (i <- 0 until 20) countdown ! "count"
  Thread.sleep(1000)
  ourSystem.shutdown()
}