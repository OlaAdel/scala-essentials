package lectures.part2oop

object Objects {

  //SCALA DOES NOT HAVE CLASS-LEVEL-FUNCTIONALITY ("static")


  object Person { // type + its only instance
    // class-level functionality
    val N_EYES = 2

    def canFly: Boolean = false

    //factory method
    def apply(mother: Person, father: Person): Person =
      new Person("Bobbie")
  }

  //Person-object and Person-class are COMPANIONS, as they reside in the same file\scope
  class Person(val name: String) {
    // instance-level functionality
  }

  //Scala Applications = Scala objects with
  // def main(args: Array[String]): Unit

  def main(args: Array[String]): Unit = {

    println(Person.N_EYES)
    println(Person.canFly)

    // Scala object = SINGLETON instance

    val mary = new Person("Mary")
    val john = new Person("John")
    println(mary == john)

    val person1 = Person
    val person2 = Person
    println(person1 == person2)

    val bobbie = Person(mary, john)

  }
}
