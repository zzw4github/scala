package org.scalalang.tutorial.tour

/**
  * Created by Administrator on 2016-08-17.
  */
/**
  The annotation +T declares type T to be used only in covariant positions.
      Similarly, -T would declare T to be used only in contravariant positions.
  For covariant type parameters we get a covariant subtype relationship regarding this type parameter.
  For our example this means Stack[T] is a subtype of Stack[S] if T is a subtype of S.
  The opposite holds for type parameters that are tagged with a -.

  For the stack example we would have to use the covariant type parameter T in a contravariant position for being able to define method push.
  Since we want covariant subtyping for stacks, we use a trick and abstract over the parameter type of method push.
  We get a polymorphic method in which we use the element type T as a lower bound of push’s type variable.
  This has the effect of bringing the variance of T in sync with its declaration as a covariant type parameter.
  Now stacks are covariant, but our solution allows that e.g. it’s possible to push a string on an integer stack.
  The result will be a stack of type Stack[Any]; so only if the result is used in a context where we expect an integer stack,
  we actually detect the error. Otherwise we just get a stack with a more general element type.
  */

class Stack[+A] {
  def push[B >: A](elem: B): Stack[B] = new Stack[B] {
    override def top: B = elem
    override def pop: Stack[B] = Stack.this
    override def toString() = elem.toString() + " " +
      Stack.this.toString()
  }
  def top: A = sys.error("no element on stack")
  def pop: Stack[A] = sys.error("no element on stack")
  override def toString() = ""
}
object VariancesTest extends scala.App {
  var s: Stack[Any] = new Stack().push("hello");
  s = s.push(new Object())
  s = s.push(7)
  println(s)
}
