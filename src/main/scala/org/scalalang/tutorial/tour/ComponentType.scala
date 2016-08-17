package org.scalalang.tutorial.tour

/**
  * Created by infosea on 2016-08-17.
  */
trait Cloneable extends java.lang.Cloneable {
  override def clone(): Cloneable = {
    super.clone().asInstanceOf[Cloneable]
  }
}
trait Resetable {
  def reset: Unit
}

  object ComponentType extends scala.App {
//    def cloneAndReset(obj: Cloneable with Resetable): Cloneable = {
//      //...
//    }
  }