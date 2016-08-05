package learning.concurrent.programming

import akka.actor.{ActorSystem, Props, Actor}
import akka.event.Logging

/**
  * Created by infosea on 2016-08-03.
  */
class ChildActor extends Actor {
  val log = Logging(context.system, this)

  def receive = {
    case "sayhi" =>
      val parent = context.parent
      log.info(s"my parent $parent made me say hi!")
  }

  override def postStop() {
    log.info("child stopped!")
  }
}

class ParentActor extends Actor {
  val log = Logging(context.system, this)

  def receive = {
    case "create" =>
      context.actorOf(Props[ChildActor])
      log.info(s"created a kid; children = ${context.children}")
    case "sayhi" =>
      log.info("Kids, say hi!")
      for (c <- context.children) c ! "sayhi"
    case "stop" =>
      log.info("parent stopping")
      context.stop(self)
  }
}

object ActorsHierarchy extends App {
  val ourSystem = ActorSystem("outSystem")
  val parent =
    ourSystem.actorOf(Props[ParentActor], "parent")
  parent ! "create"
  parent ! "create"
  Thread.sleep(1000)
  parent ! "sayhi"
  Thread.sleep(1000)
  parent ! "stop"
  Thread.sleep(1000)
  ourSystem.shutdown()
}