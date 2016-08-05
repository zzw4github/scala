package learning.concurrent.programming

import akka.actor._
import akka.event.Logging

/**
  * Created by infosea on 2016-08-03.
  */
class CheckActor extends Actor {
  val log = Logging(context.system, this)

  def receive = {
    case path: String =>
      log.info(s"checking path $path")
      context.actorSelection(path) ! Identify(path)
    case ActorIdentity(path, Some(ref)) =>
      log.info(s"found actor $ref at $path")
    case ActorIdentity(path, None) =>
      log.info(s"could not find an actor at $path")
  }
}

object CheckActor extends App{
  lazy val ourSystem = ActorSystem("outSystem")
  val checker =
    ourSystem.actorOf(Props[CheckActor], "checker")
  checker ! "../*"
  checker ! "../../*"
  checker ! "/system/*"
  checker ! "/user/checker2"
}