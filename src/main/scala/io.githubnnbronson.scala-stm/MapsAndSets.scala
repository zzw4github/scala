import scala.concurrent.stm._

/**
  * Created by Administrator on 2016-08-06.
  */
object MapsAndSets extends App{
  val m = TMap("one" -> 1).single

  (new Thread { override def run {
    atomic { implicit txn =>
      m -= "one"
      m += ("ONE" -> 1)
    }
  } }).start

  for ((k, v) <- m; if v == 1) println(k)
}
