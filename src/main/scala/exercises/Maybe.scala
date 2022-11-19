package exercises

abstract class Maybe[+T] {

  def map[B](f: T => B): Maybe[B]

  def flatMap[B](f: T => Maybe[B]): Maybe[B]

  def filter(p: T => Boolean): Maybe[T]
}


case object MaybeNot extends Maybe[Nothing] {
  def map[B](f: Nothing => B): Maybe[B] = MaybeNot

  def flatMap[B](f: Nothing => Maybe[B]): Maybe[B] = MaybeNot

  def filter(p: Nothing => Boolean): Maybe[Nothing] = MaybeNot
}


case class Just[T](element: T) extends Maybe[T] {
  def map[B](f: T => B): Maybe[B] = Just(f(element))

  def flatMap[B](f: T => Maybe[B]): Maybe[B] = f(element)

  def filter(p: T => Boolean): Maybe[T] =
    if (p(element)) this
    else MaybeNot
}

object MaybeTest extends App {

  val none = MaybeNot
  println(none)
  println(none.map(x => x.getClass))
  println(none.flatMap(x => Just(3)))
  println(none.filter(x => x.equals(3)))


  val just3 = Just(3)
  println(just3)
  println(just3.map(x => x * 2))
  println(just3.map(x => "Hey"))
  println(just3.flatMap(x => Just("Hey")))
  println(just3.filter(x => x % 2 == 0))




}