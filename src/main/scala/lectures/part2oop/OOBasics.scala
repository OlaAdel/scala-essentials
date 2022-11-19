package lectures.part2oop

object OOBasics extends App {

  val person = new Person("John", 26)
  println(person.age)
  println(person.x)
  person.greet("Daniel")
  person.greet()

  val anotherPerson = new Person("Ola")
  println(anotherPerson.age)

}

//constructor
class Person(name: String, val age: Int = 0) {
  //body
  val x = 2

  println(1 + 3)

  def greet(name: String): Unit = println(s"$this.name says: Hi $name")

  def greet(): Unit = println(s"Hi, I am $name")

  //multiple constructors
  def this(name: String) = this(name, 0)

  def this() = this("John Doe")

  val author: Writer = new Writer("Charles", "Dickens", 1812)
  val imposter: Writer = new Writer("Charles", "Dickens", 1812)

  val novel: Novel = new Novel("Great Expectations", 1861, author)

  println(author.fullName)
  println(novel.authorAge)
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(imposter))

  val novelNewRelease = novel.copy(2015)

  val counter = new Counter
  counter.increment.increment.increment.print
  counter.increment(10).print

}

// class parameters are NOT FIELDS, to convert params to fields add val keyword


/*
 Novel and a Writer
 Writer: first name, surname, year of birth
    - method full name
 Novel: name, year of release, author
  - authorAge (the age of the author at the year of the release)
  - isWrittenBy(author)
  - copy (new year of release) = new instance of Novel
*/

class Writer(firstName: String, surname: String, val yearOfBirth: Int) {

  def fullName: String = s"$firstName $surname"
}

class Novel(name: String, yearOfRelease: Int, author: Writer) {

  def authorAge: Int = yearOfRelease - author.yearOfBirth

  def isWrittenBy(author: Writer): Boolean = this.author == author

  def copy(yearOfTheNewRelease: Int): Novel = new Novel(name, yearOfTheNewRelease, author)

}


/*
  Counter class
    - receives an int value
    - method current count
    - method to increment/decrement = new Counter
    - overload inc/dec to receive an amount
*/

class Counter(val count: Int = 0) {

  def increment: Counter = {
    println("incrementing")
    new Counter(count + 1)
  }

  def decrement: Counter = {
    println("decrementing")
    new Counter(count - 1)
  }

  def increment(amount: Int): Counter = {
    if (amount <= 0) this
    else increment.increment(amount - 1)
  }

  def decrement(amount: Int): Counter = {
    if (amount <= 0) this
    else decrement.decrement(amount - 1)
  }

  def print: Unit = println(count)

}