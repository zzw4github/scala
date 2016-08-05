package learning.concurrent.programming

/**
  * Created by infosea on 2016-08-03.
  */

import akka.actor._
import akka.event.Logging

class HelloActor(val hello: String) extends Actor {
  val log = Logging(context.system, this)

  def receive = {
    case `hello` =>
      log.info(s"Received a '$hello'...$hello!")
    case msg =>
      log.info(s"Unexpected message '$msg'")
      context.stop(self)
  }
}

object HelloActor {
  def props(hello: String) = Props(new
      HelloActor(hello))

  def propsAlt(hello: String) =
    Props(classOf[HelloActor], hello)
}

class HelloActorUtils {
  val defaultHi = "Aloha!"

  def defaultProps() = Props(new
      HelloActor(defaultHi))
}

object HelloActorUtils {
  def apply() = {
    new HelloActorUtils()
  }
}

class DeafActor extends Actor {
  val log = Logging(context.system, this)

  def receive = PartialFunction.empty

  override def unhandled(msg: Any) = msg match {
    case msg: String => log.info(s"I do not hear '$msg'")
    case msg => super.unhandled(msg)
  }
}

object ActorsUnhandled extends App {
  lazy val ourSystem =
    ActorSystem("OurExampleSystem")
  val deafActor: ActorRef =
    ourSystem.actorOf(Props[DeafActor], name =
      "deafy")
  deafActor ! "hi"
  Thread.sleep(1000)
  deafActor ! 1234
  Thread.sleep(1000)
  ourSystem.shutdown()
}

object ActorsCreate extends App {
  lazy val ourSystem =
    ActorSystem("OurExampleSystem")
  val hiActor: ActorRef =
    ourSystem.actorOf(HelloActor.props("hi"),
      name = "greeter")
  hiActor ! "hi"
  Thread.sleep(1000)
  hiActor ! "hola"
  Thread.sleep(1000)

  val defaultActor: ActorRef = ourSystem.actorOf(HelloActorUtils().defaultProps())
  defaultActor ! "Aloha!"
  ourSystem.shutdown()
}

