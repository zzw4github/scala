package learning.concurrent.programming.pingpong

import akka.actor.{ActorRef, Actor}
import akka.event.Logging
import akka.util.Timeout
import scala.concurrent.duration._
import akka.pattern.ask
import akka.pattern.pipe
import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by infosea on 2016-08-04.
  */

class Pingy extends Actor {
  val log = Logging(context.system, this)
  def receive = {
    case pongyRef: ActorRef =>
      log.info("pong")
      implicit val timeout = Timeout(2 seconds)
      val f = pongyRef ? "ping"
      pipe(f) to sender


  }
}
