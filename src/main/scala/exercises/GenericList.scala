package exercises

import exercises.ListTest2.listOfIntegers

import scala.language.postfixOps

abstract class GenericList[+A] {
  /* singly lined list methods
    head = first element of the list
    tail = remainder of the list
    isEmpty = is the list empty
    add(int) => new list with this element added
    toString => a string representation of the list
  */

  def head: A

  def tail: GenericList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): GenericList[B]

  def printElements: String

  override def toString: String = "(" + printElements + ")"

  def map[B](transformer: A => B): GenericList[B]

  def filter(predicate: A => Boolean): GenericList[A]

  def flatMap[B](transformer: A => GenericList[B]): GenericList[B]

  def ++[B >: A](list: GenericList[B]): GenericList[B]

  //hofs
  def foreach(f: A => Unit): Unit

  def sort(comparator: (A, A) => Int): GenericList[A]

  def zipWith[B, C](list: GenericList[B], zipFunction: (A, B) => C): GenericList[C]

  def fold[B](start: B)(f: (B, A) => B): B
}

case object EmptyGenericList extends GenericList[Nothing] {
  def head: Nothing = throw new NoSuchElementException

  def tail: GenericList[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): GenericList[B] = ConsGenericList(element, EmptyGenericList)

  def printElements: String = ""

  def map[B](transformer: Nothing => B): GenericList[B] = EmptyGenericList

  def filter(predicate: Nothing => Boolean): GenericList[Nothing] = EmptyGenericList

  def flatMap[B](transformer: Nothing => GenericList[B]): GenericList[B] = EmptyGenericList

  def ++[B >: Nothing](list: GenericList[B]): GenericList[B] = list

  //hofs
  def foreach(f: Nothing => Unit): Unit = ()

  def sort(comparator: (Nothing, Nothing) => Int): GenericList[Nothing] = EmptyGenericList

  def zipWith[B, C](list: GenericList[B], zipFunction: (Nothing, B) => C): GenericList[C] = {
    if (!list.isEmpty) throw new RuntimeException("lists don't have the same size")
    EmptyGenericList
  }

  def fold[B](start: B)(f: (B, Nothing) => B): B = start
}

case class ConsGenericList[+A](h: A, t: GenericList[A]) extends GenericList[A] {
  def head: A = h

  def tail: GenericList[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): GenericList[B] = ConsGenericList(element, this)

  def printElements: String =
    if (t.isEmpty) "" + h
    else s"$h ${t.printElements}"

  def map[B](transformer: A => B): GenericList[B] =
    new ConsGenericList[B](transformer(h), t.map(transformer))

  def filter(predicate: A => Boolean): GenericList[A] = {
    if (predicate(h))
      new ConsGenericList[A](h, t.filter(predicate))
    else
      t.filter(predicate)
  }

  def flatMap[B](transformer: A => GenericList[B]): GenericList[B] =
    transformer(h) ++ t.flatMap(transformer)

  def ++[B >: A](list: GenericList[B]): GenericList[B] =
    new ConsGenericList[B](h, t ++ list)

  //hofs
  def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  def sort(comparator: (A, A) => Int): GenericList[A] = {
    def insert(x: A, sortedList: GenericList[A]): GenericList[A] =
      if (sortedList.isEmpty) ConsGenericList(x, EmptyGenericList)
      else if (comparator(x, sortedList.head) <= 0) ConsGenericList(x, sortedList)
      else ConsGenericList(sortedList.head, insert(x, sortedList.tail))

    val sortedTail = t.sort(comparator)
    insert(h, sortedTail)
  }

  def zipWith[B, C](list: GenericList[B], zipFunction: (A, B) => C): GenericList[C] = {
    if (list.isEmpty) throw new RuntimeException("lists don't have the same size")
    ConsGenericList(zipFunction(h, list.head), t.zipWith(list.tail, zipFunction))
  }

  def fold[B](start: B)(f: (B, A) => B): B =
    t.fold(f(start, h))(f)

}

trait MyPredicate[-T] {
  def test(element: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(element: A): B
}


object ListTest2 extends App {
  val listOfIntegers: GenericList[Int] = ConsGenericList(1, ConsGenericList(2, ConsGenericList(3, EmptyGenericList)))
  val listOfStrings: GenericList[String] = ConsGenericList("Hello", ConsGenericList("Scala", ConsGenericList("!", EmptyGenericList)))
  println(listOfIntegers.toString)

  class EvenPredicate extends MyPredicate[Int] {
    override def test(element: Int): Boolean = element % 2 == 0
  }

  val evenPredicate: Int => Boolean = (element: Int) => element % 2 == 0
  val intToStringTransformer: Int => String = (element: Int) => s"!${element.toString}"

  class IntToStringTransformer extends MyTransformer[Int, String] {
    override def transform(element: Int): String = s"!${element.toString}"
  }

  println(listOfIntegers.filter(element => element % 2 == 0).toString)
  println(listOfIntegers.map(element => s"!${element.toString}").toString)

  class IntToStringListTransformer extends MyTransformer[Int, GenericList[String]] {
    override def transform(element: Int): GenericList[String] =
      ConsGenericList[String](s"!${element.toString}", ConsGenericList[String](s"!${(element + 1).toString}", EmptyGenericList))
  }

  val IntToStringListTransformer: Int => GenericList[String] = (element: Int) => ConsGenericList[String](s"!${element.toString}", ConsGenericList[String](s"!${(element + 1).toString}", EmptyGenericList))

  println((listOfIntegers ++ listOfIntegers).toString)

  println(listOfIntegers.flatMap(element => ConsGenericList[String](s"!${element.toString}", ConsGenericList[String](s"!${(element + 1).toString}", EmptyGenericList))).toString)

  val cloneListOfIntegers: GenericList[Int] = listOfIntegers
  /*equals already defined for case classes, without case keyword you will need to define a recursive
  function iterated over the elements and check their equality
  */
  println(listOfIntegers == cloneListOfIntegers)

  listOfIntegers.foreach(x => println(x))
  listOfIntegers.foreach(println)

  println(listOfIntegers.sort((x, y) => y - x))

  println(listOfIntegers.zipWith(listOfStrings, (x, y) => x + y))

  println(listOfIntegers.fold(5)((x, y) => x * y))
  println(listOfIntegers.fold(0)((x, y) => x + y))


  val forComprehensions =
    for {
      n <- listOfIntegers
      c <- listOfStrings
    } yield s"$n$c"

  println(forComprehensions)

}

