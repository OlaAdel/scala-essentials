package lectures.part4pm

import lectures.part4pm.PatternMatching.Person

import scala.util.Random

object PatternMatching extends App {

  val random = new Random
  val x = random.nextInt(10)

  val description = x match
    case 1 => "the ONE"
    case 2 => "double or nothing"
    case 3 => "third time is the charm"
    case _ => "something else" // _ = WILDCARD

  println(x)
  println(description)

  // 1. Decompose values
  case class Person(name: String, age: Int)

  val bob: Person = Person("Bob", 20)

  val greeting: Any = bob match {
    case Person(n, a) if a < 21 => s"Hi, my name is $n and I can't drink in the US"
    case Person(n, a) => s"Hi, my name is $n and I am $a years old"
    case _ => 42

  }
  println(greeting)

  /*
    1. cases are matched in order
    2. what if no cases match? MatchError
    3. type of the PM expression, unified type of all the types in all the cases
    4. PM works really well with case classes

  */

  // PM on sealed hierarchies

  sealed class Animal

  case class Dog(breed: String) extends Animal

  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Terra Nova")
  animal match
    case Dog(breed) => println(s"Matched a dog of the $breed")

  // match everything
  val isEven = x match
    case n if n % 2 == 0 => true
    case _ => false
  // WHY?

  val isEvenCond = if (x % 2 == 0) true else false
  val isEvenNormal = x % 2 == 0
  /*
  Exercise
    simple function uses PM
      takes an Expr => human readable form
    Sum(Number(2), Number(3)) => 2 + 3
    Sum(Number(2), Sum(Number(3), Number(4))`) => 2 + 3 + 4
    Prod(Sum(Number(2), Number(1)), Number(3)) => (2 + 1) * 3
    Sum(Prod(Number(2), Number(1)), Number(3)) => 2 * 1 + 3
  */

  trait Expr {
  }

  case class Number(n: Int) extends Expr

  case class Sum(e1: Expr, e2: Expr) extends Expr

  case class Prod(e1: Expr, e2: Expr) extends Expr

  val expr: Expr = Sum(Number(2), Number(3))
  val expr1: Expr = Prod(Number(2), Number(3))

  def readableFormat(expression: Expr): String = {
    expression match
      case Number(x) => x.toString
      case Sum(x, y) => s"${readableFormat(x)} + ${readableFormat(y)}"
      case Prod(x, y) => {
        def maybeShowParentheses(expr: Expr) = expr match
          case Prod(_, _) => readableFormat(expr)
          case Number(_) => readableFormat(expr)
          case _ => s"(${readableFormat(expr)})"

        s"${maybeShowParentheses(x)} * ${maybeShowParentheses(y)}"
      }
  }

  println(readableFormat(Sum(Number(1), Number(2))))
  println(readableFormat(Prod(Number(1), Sum(Prod(Number(3), Number(5)), Number(6)))))
  println(readableFormat(Prod(Sum(Number(2), Number(1)), Number(3))))
  print(readableFormat(Sum(Prod(Number(2), Number(1)), Number(3))))

}
