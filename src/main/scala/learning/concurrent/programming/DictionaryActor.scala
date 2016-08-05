package learning.concurrent.programming

import java.lang

import akka.actor.{Props, ActorSystem, Actor}
import akka.event.Logging

import scala.collection.mutable
import scala.io.Source


/**
  * Created by infosea on 2016-08-03.
  */

object DictionaryActor {

  case class Init(path: String)

  case class IsWord(w: String)

  case object End

}

class DictionaryActor extends Actor {
  private val log = Logging(context.system, this)
  private var dictionary = mutable.HashSet[String]()

  def receive = uninitialized

  def uninitialized: PartialFunction[Any, Unit] = {
    case DictionaryActor.Init(path) =>
      val stream =
        getClass.getResourceAsStream(path)
      val words =
        Source.fromInputStream(stream)
      for (w <- words.getLines) dictionary += w
      context.become(initialized)
  }

  def initialized: PartialFunction[Any, Unit] = {
    case DictionaryActor.IsWord(w) =>
      log.info(s"word '$w' exists: ${dictionary(w)}")
    case DictionaryActor.End =>
      dictionary.clear()
      context.become(uninitialized)
  }

  override def unhandled(msg: Any) = {
    log.info(s"cannot handle message $msg in this state.")
  }
}


object Test extends App {
  lazy val ourSystem = ActorSystem("OurExampleSystem")
  val dict =
    ourSystem.actorOf(Props[DictionaryActor],
      "dictionary")
  dict ! DictionaryActor.IsWord("program")
  Thread.sleep(1000)
  dict !
    DictionaryActor.Init("/learning/concurrent/programming/word.txt"); Thread.sleep(1000)

  dict ! DictionaryActor.IsWord("program")
  Thread.sleep(1000)
  dict ! DictionaryActor.IsWord("balaban")
  Thread.sleep(1000)
  dict ! DictionaryActor.End
  Thread.sleep(1000)
  ourSystem.shutdown()
}
