package learning.concurrent.programming.remote.filebrowser

import akka.actor.{ActorIdentity, ActorRef, Identify, Actor}
import akka.util.Timeout
import learning.concurrent.programming.remote.filebrowser.FTPClientActor.Start
import learning.concurrent.programming.remote.filebrowser.FTPServerActor.Command
import akka.pattern.ask
import akka.pattern.pipe
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by infosea on 2016-08-04.
  */
class FTPClientActor(implicit val timeout: Timeout) extends Actor{

  def receive = unconnected

  def unconnected: Actor.Receive = {
    case Start(host) =>
      val serverActorPath = s"akka.tcp://FTPServerSystem@$host/user/server"
      val serverActorSel = context.actorSelection(serverActorPath)
      serverActorSel ! Identify(())
      context.become(connecting(sender))
  }

  def connecting(clientApp: ActorRef):
  Actor.Receive = {
    case ActorIdentity(_, Some(ref)) =>
    clientApp ! true
    context.become(connected(ref))
    case ActorIdentity(_, None) =>
    clientApp ! false
    context.become(unconnected)
  }

  def connected(serverActor: ActorRef):
  Actor.Receive = {
    case command: Command =>
      pipe((serverActor ? command)) to sender
  }
}



object FTPClientActor {
  case class Start(host: String)
}
