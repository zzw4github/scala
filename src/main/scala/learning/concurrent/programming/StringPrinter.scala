package learning.concurrent.programming

import akka.actor.{ActorSystem, Props, ActorRef, Actor}
import akka.event.Logging

/**
  * Created by infosea on 2016-08-03.
  */
class StringPrinter extends Actor {
  val log = Logging(context.system, this)

  def receive = {
    case msg => log.info(s"printer got message '$msg'")
  }

  override def preStart(): Unit =
    log.info(s"printer preStart.")

  override def postStop(): Unit =
    log.info(s"printer postStop.")
}

class LifecycleActor extends Actor {
  val log = Logging(context.system, this)
  var child: ActorRef = _

  def receive = {
    case num: Double => log.info(s"got a double - $num")
    case num: Int => log.info(s"got an integer - $num")
    case lst: List[_] => log.info(s"list - ${lst.head}, ...")
    case txt: String => child ! txt
  }

  override def preStart(): Unit = {
    log.info("about to start")
    child = context.actorOf(Props[StringPrinter],
      "kiddo")
  }

  override def preRestart(t: Throwable, msg:
  Option[Any]): Unit = {
    log.info(s"about to restart because of $t, during message $msg")
    super.preRestart(t, msg)
  }


  override def postRestart(t: Throwable): Unit = {
    log.info(s"just restarted due to $t")
    super.postRestart(t)
  }

  //Finally, we override  postStop to track when the actor is stopped:
  override def postStop() = log.info("just stopped")

}

object LifecycleActor extends App {
  lazy val ourSystem = ActorSystem("ourSystem")
  val testy =
    ourSystem.actorOf(Props[LifecycleActor],
      "testy")
  testy ! math.Pi
  testy ! "hi there!"
  testy ! Nil
  ourSystem.terminate()
}

