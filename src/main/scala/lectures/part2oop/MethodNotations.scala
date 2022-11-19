package lectures.part2oop

import scala.language.postfixOps
//import scala.language.postfixOps

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, val age: Int) {
    def likes(movie: String): Boolean = movie == favoriteMovie

    def hangoutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"

    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"

    def unary_! : String = s"$name what the heck?"

    def isAlive: Boolean = true

    def apply(): String = s"Hi my name is $name and I like $favoriteMovie"

    def +(nickname: String): Person = new Person(s"$name ($nickname)", favoriteMovie, age)

    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)

    def learns(subject: String): String = s"$name learns $subject"

    def learnsScala: String = learns("Scala")

    def apply(n: Int): String = s"$name watched $favoriteMovie $n times"

  }

  val marry = new Person("Marry", "Inception", 15)
  println(marry.likes("Inception"))
  println(marry likes "Inception")
  // infix notations = operators notations

  // "operators" in Scala
  val tom = new Person("Tom", "Fight Club", 20)
  println(marry hangoutWith tom)
  println(marry + tom)

  // ALL operators are methods


  //prefix notation

  val x = -1
  val y = 1.unary_-

  println(!marry)

  //postfix notation

  println(marry.isAlive)
  println(marry isAlive)

  //apply
  println(marry.apply())
  println(marry())

  /*
    1. overload the + operator
    marry + "the rockestar" => new person mary(the rockstar)

    2. Add an age to the Person class
    Add a unary + operator => new person with the age + 1
    +marry => with the age incremented

    3. Add a learns method in the person class => "Marry learns Scala"
    Add a learnsScala method, calls learns method with "Scala"
    Use it in postfix notation

    4. overload the apply method
    Marry.apply(2) => "Marry watched inception 2 times"
  */


  println((marry + "the rockstar").apply())

  println((+marry).age)

  println(marry learns "Go")
  println(marry learnsScala)
  println(marry(10))
}
