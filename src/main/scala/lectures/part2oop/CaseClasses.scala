package lectures.part2oop

object CaseClasses extends App {

  /*
    equals, hashCode, toString
  */

  case class Person(name: String, age: Int)
  //1.class parameters are fields

  val jim = new Person("Jim", 24)
  println(jim.name)

  //2. sensible toString
  //println(instance) = println(instance.toString)
  println(jim.toString)
  println(jim)

  // equals and hashCode implemented OOTB

  val jim2 = new Person("Jim", 24)
  println(jim == jim2)


  //4. CCs have handy copy methods
  val jim3 = jim.copy(age = 45)
  println(jim3)

  //5. CCs have companion objects

  val thePerson = Person
  val mary = Person("Mary", 23) //Person apply method
  println(mary)

  //6. CCs are serializable
  // Akka

  //7. CCs have extractor patterns == CCs can be used in pattern matching

  //same as case class except it's an object and doesn't has a companion object(as it's already its companion object)
  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

  /*
  Expand MyList - use case classes and case objects
  */
}
