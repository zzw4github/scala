package learning.concurrent.programming

import akka.actor._
import akka.event.Logging
import learning.concurrent.programming.pingpong.{Pongy, Pingy}


/**
  * Created by infosea on 2016-08-04.
  */
class MyRemoteActorRefProvider {

  import com.typesafe.config._

  def remotingConfig(port: Int) =
    ConfigFactory.parseString(
      s"""
      akka {
      actor.provider =
      "akka.remote.RemoteActorRefProvider"
      remote {
      enabled-transports =
      ["akka.remote.netty.tcp"]
      netty.tcp {
      hostname = "127.0.0.1"
      port = $port
      }
      }
      }
      """)

  def remotingSystem(name: String, port: Int):
  ActorSystem = ActorSystem(name, remotingConfig(port))

}



  object RemotingPongySystem extends App {
   val provider:MyRemoteActorRefProvider  = new MyRemoteActorRefProvider
    val system = provider.remotingSystem("PongyDimension", 24321)
    val pongy = system.actorOf(Props[Pongy],
      "pongy")
    Thread.sleep(15000)
    system.terminate()
  }

class Runner extends Actor {
//  import akka.remote.ContainerFormats.ActorIdentity
  val log = Logging(context.system, this)
  val pingy = context.actorOf(Props[Pingy], "pingy")
  def receive = {
    case "start" =>
      val pongySys =
        "akka.tcp://PongyDimension@127.0.0.1:24321"
      val pongyPath = "/user/pongy"
      val url = pongySys + pongyPath
      val selection =
        context.actorSelection(url)
      selection ! Identify(0)
    case ActorIdentity(0, Some(ref)) =>
      log.info("this is a message")
      pingy ! ref
    case ActorIdentity(0, None) =>
      log.info("Something's wrong – ain't no pongy anywhere!")
        context.stop(self)
    case "pong" =>
      log.info("got a pong from another dimension.")
      context.stop(self)
  }
}

object RemotingPingySystem extends App {
  val provider:MyRemoteActorRefProvider  = new MyRemoteActorRefProvider
  val system = provider.remotingSystem("PingyDimension", 24567)
  val runner = system.actorOf(Props[Runner], "runner")
  runner ! "start"
  Thread.sleep(15000)
  system.terminate()
}
