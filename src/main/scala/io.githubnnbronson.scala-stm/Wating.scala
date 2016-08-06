import java.util.concurrent.TimeUnit

import scala.concurrent.stm.{Ref, TxnExecutor, atomic, retryFor}

/**
  * Created by Administrator on 2016-08-06.
  */
object Waiting extends App{
  val (x, y) = (Ref(10), Ref(0))
  var  flag: Int  =10
  def sum = atomic { implicit txn =>
    val a = x()
    val b = y()
    a + b
  }

  def transfer(n: Int) {
    atomic { implicit txn =>
      x -= n
      y += n
    }
  }

  new Thread { override def run {
     while(flag > 0)
       flag -= 1
      sum

  } }.start

  new Thread { override def run {
    while (flag > 0)
    transfer(1)
  } }.start

//using custom TxnExecutor
  atomic.withRetryTimeout(1000) { implicit txn =>
    // any retries in this atomic block will wait for at most 1000 milliseconds
  }

  val myAtomic = atomic.withRetryTimeout(1, TimeUnit.SECONDS)
  myAtomic { implicit txn =>
    // this atomic block has a timeout of 1 seconds
  }
//  myAtomic { ... }

  TxnExecutor.transformDefault( _.withRetryTimeout(1000) )
  atomic { implicit txn =>
    // all atomic blocks now default to a 1 second timeout
  }
//using retryFor
//  val instance = atomic { implicit txn =>
//    if (!pool.hasAvailable) {
//      retryFor(100)
//      pool.grow()
//    }
//    pool.take()
//  }
}
