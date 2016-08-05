package learning.concurrent.programming


import akka.actor.{ActorSystem, Props}
import scala.concurrent.duration._
import  akka.pattern.gracefulStop
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

/**
  * Created by infosea on 2016-08-03.
  */
object CommunicatingGracefulStop extends App {
  val ourSystem = ActorSystem("outSystem")
  val grace = ourSystem.actorOf(Props[GracefulPingy], "grace")
  val stopped = gracefulStop(grace, 3.seconds, "Die, Pingy!")
      stopped onComplete {
      case Success(x) =>
        log("graceful shutdown successful")
        ourSystem.terminate()
      case Failure(t) =>
        log("grace not stopped!")
        ourSystem.terminate()
    }

 def log(string:String){
    System.out.println(string)
  }
}
