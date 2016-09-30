package learning.concurrent.programming.actors.remote

import akka.actor.{ActorSystem, Props}

/**
  * Created by infosea on 2016-09-30.
  */
class RemotingPongySystem {

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
  ActorSystem =
    ActorSystem(name, remotingConfig(port))


}

//object RemotingPongySystem extends App {
//  val system = remotingSystem("PongyDimension",
//    24321)
//  val pongy = system.actorOf(Props[Pongy],
//    "pongy")
//  Thread.sleep(15000)
//  system.shutdown()
//}
