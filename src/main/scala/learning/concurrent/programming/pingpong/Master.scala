package learning.concurrent.programming.pingpong

import akka.actor.{Props, ActorSystem, Actor}
import akka.event.Logging

/**
  * Created by infosea on 2016-08-04.
  */
class Master extends Actor {
  val log = Logging(context.system, this)
  lazy val ourSystem = ActorSystem("ourSystem")
  val pingy = ourSystem.actorOf(Props[Pingy], "pingy")
  val pongy = ourSystem.actorOf(Props[Pongy], "pongy")

  def receive = {
    case "start" =>
      pingy ! pongy
    case "pong" =>
      context.stop(self)
  }

  override def postStop() = log.info("master going down")



}

object Master extends App {
  lazy val ourSystem = ActorSystem("ourSystem")
  val masta = ourSystem.actorOf(Props[Master], "masta")
  masta ! "start"
    ourSystem.terminate()
}
