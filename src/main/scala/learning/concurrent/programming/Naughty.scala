package learning.concurrent.programming

import akka.actor.Actor
import akka.event.Logging

/**
  * Created by infosea on 2016-08-03.
  */
class Naughty extends Actor {
  val log = Logging(context.system, this)
  def receive = {
    case s: String => log.info(s)
    case msg => throw new RuntimeException
  }
  override def postRestart(t: Throwable) =
    log.info("naughty restarted")
}
