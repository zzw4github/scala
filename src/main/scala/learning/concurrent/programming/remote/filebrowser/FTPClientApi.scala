package learning.concurrent.programming.remote.filebrowser

import akka.actor.Props
import akka.util.Timeout
import scala.concurrent.duration._
import akka.pattern.ask
import learning.concurrent.programming.MyRemoteActorRefProvider

import scala.concurrent.Future
import scala.util.Try

/**
  * Created by infosea on 2016-08-04.
  */
trait FTPClientApi {
//  import scala.concurrent.ExecutionContext.Implicits.global
//  implicit val timeout: Timeout = Timeout(4
//    seconds)
//  private val props =
//    Props(classOf[FTPClientActor], timeout)
//  private val system =
//    new MyRemoteActorRefProvider().remotingSystem("FTPClientSystem", 0)
//  private val clientActor =
//    system.actorOf(props)
//  def host: String
//  val connected: Future[Boolean] = {
//    val f = clientActor ? FTPClientActor.Start
//    f.mapTo[Boolean]
//  }
//  def getFileList(d: String): Future[(String,
//    Seq[FileInfo])] = {
//    val f = clientActor ?
//      FTPServerActor.GetFileList(d)
//    f.mapTo[Seq[FileInfo]].map(fs => (d, fs))
//  }
//  def copyFile(src: String, dest: String):
//  Future[String] = {
//    val f = clientActor ?
//      FTPServerActor.CopyFile(src, dest)
//    f.mapTo[Try[String]].map(_.get)
//  }
//  def deleteFile(srcpath: String):
//  Future[String] = {
//    val f = clientActor ?
//      FTPServerActor.DeleteFile(srcpath)
//    f.mapTo[Try[String]].map(_.get)
//  }
}
