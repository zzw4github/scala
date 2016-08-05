package learning.concurrent.programming.remote.filebrowser

import akka.actor.{Actor, Props}
import akka.event.Logging
import learning.concurrent.programming.remote.filebrowser.FTPServerActor.{DeleteFile, CopyFile, GetFileList}

import scala.concurrent.Future
import scala.util.Try
import akka.pattern.pipe

/**
  * Created by infosea on 2016-08-04.
  */
object FTPServerActor {
  sealed trait Command
  case class GetFileList (dir: String) extends Command
  case class CopyFile(src: String, dest: String) extends Command
  case class DeleteFile(path: String) extends Command
//  def apply(fs: FileSystem) = Props(classOf[FTPServerActor], fs)

}

//class FTPServerActor(fileSystem: FileSystem) extends Actor {
//  val log = Logging(context.system, this)
//  def receive = {
//    case GetFileList(dir) =>
//      val filesMap =
//        fileSystem.getFileList(dir)
//      val files = filesMap.map(_._2).to[Seq]
//      sender ! files
//    case CopyFile(srcpath, destpath) =>
//      pipe (Future {
//        Try(fileSystem.copyFile(srcpath,
//          destpath))
//      } ) to sender
//    case DeleteFile(path) =>
//     pipe (Future {
//        Try(fileSystem.deleteFile(path))
//      } ) to sender
//  }
//}
