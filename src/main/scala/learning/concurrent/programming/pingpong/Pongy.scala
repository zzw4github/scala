package learning.concurrent.programming.pingpong

import akka.actor.{ActorSystem, Props, Actor}
import akka.event.Logging

/**
  * Created by infosea on 2016-08-04.
  */
class Pongy extends Actor{
  lazy val ourSystem = ActorSystem("ourSystem")
  val log = Logging(context.system, this)
  def receive = {
    case "ping" =>
      log.info("ping")
      context.stop(self)
  }
  override def postStop() = log.info("pongy going down")

}
