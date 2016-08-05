//package learning.concurrent.programming.remote.filebrowser
//
//
//import learning.concurrent.programming.remote.filebrowser.frontend.{status, menu, FilePane}
//
//import scala.swing._
//
///**
//  * Created by infosea on 2016-08-04.
//  */
//abstract class FTPClientFrame extends MainFrame {
//  import BorderPanel.Position._
//  title = "ScalaFTP"
//
//  object files extends GridPanel(1, 2) {
//    val leftPane = new FilePane
//    val rightPane = new FilePane
//    contents += leftPane += rightPane
//    def opposite(pane: FilePane) =
//      if (pane eq leftPane) rightPane else
//        leftPane
//  }
//
//  contents = new BorderPanel {
//    layout(menu) = North
//    layout(files) = Center
//    layout(status) = South
//  }
//}
//
//trait FTPClientLogic {
//  self: FTPClientFrame with FTPClientApi =>
//
//  def swing(body: =>Unit) = {
//    val r = new Runnable { def run() = body }
//    javax.swing.SwingUtilities.invokeLater(r)
//  }
//}