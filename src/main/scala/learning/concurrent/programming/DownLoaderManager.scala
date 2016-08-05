package learning.concurrent.programming

import akka.actor.SupervisorStrategy.{Escalate, Resume}
import akka.actor._
import akka.event.Logging
import learning.concurrent.programming.DownloadManager.Download
import org.apache.commons.io.FileUtils

import scala.collection.mutable
import scala.io.Source
import scala.concurrent.duration._

/**
  * Created by infosea on 2016-08-03.
  */


object DownloadManager {

  case class Download(url: String, dest: String)

  case class Finished(dest: String)

}


class Downloader extends Actor {
  def receive = {
    case DownloadManager.Download(url, dest) =>
      val content = Source.fromURL(url)
      FileUtils.write(new java.io.File(dest),
        content.mkString)
      sender ! DownloadManager.Finished(dest)
  }
}

class DownloadManager(val downloadSlots: Int) extends Actor {

  import DownloadManager._

  val log = Logging(context.system, this)
  val downloaders = mutable.Queue[ActorRef]()
  val pendingWork = mutable.Queue[Download]()
  val workItems = mutable.Map[ActorRef, Download]()

  private def checkDownloads(): Unit = {
    if (pendingWork.nonEmpty &&
      downloaders.nonEmpty) {
      val dl = downloaders.dequeue()
      val item = pendingWork.dequeue()
      log.info(s"$item starts, ${downloaders.size} download slots left")
      dl ! item
      workItems(dl) = item
    }
  }

  def receive = {
    case msg@DownloadManager.Download(url,
    dest) =>
      pendingWork.enqueue(msg)
      checkDownloads()
    case DownloadManager.Finished(dest) =>
      workItems.remove(sender)
      downloaders.enqueue(sender)
      log.info(
        s"'$dest' done, ${downloaders.size} download slots left")
      checkDownloads()
  }

  override def preStart(): Unit = {
    for (i <- 0 until downloadSlots) {
      val dl = context.actorOf(Props[Downloader], s"dl$i")
      downloaders.enqueue(dl)
    }
  }

  override val supervisorStrategy =
    OneForOneStrategy(
      maxNrOfRetries = 20, withinTimeRange = 2
        seconds
    ) {
      case fnf: java.io.FileNotFoundException =>
        log.info(s"Resource could not be found: $fnf")
          workItems.remove(sender)
          downloaders.enqueue(sender)
          Resume // ignores the exception and
//          resumes the actor
          case _ =>
          Escalate
          }
}



object  DownloadApp extends App {
  lazy val ourSystem = ActorSystem("ourSystem")
  val downloadManager =
    ourSystem.actorOf(Props(classOf[DownloadManager
      ], 4), "man")
  downloadManager ! Download("http://www.w3.org/Addressing/URL/url-spec.txt", "url-spec.txt")
  downloadManager ! Download("https://github.com/scala/blob/master/README.md", "README.md")
//  ourSystem.terminate()
}