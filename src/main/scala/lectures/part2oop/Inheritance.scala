package lectures.part2oop

object Inheritance extends App {

  // single class inheritance
  sealed class Animal {
    val creatureType = "Wild"

    def eat: Unit = println("nomnom")
  }

  class Cat extends Animal {
    def crunch: Unit = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch

  // constructors

  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  //overriding
  class Dog(override val creatureType: String = "domestic") extends Animal {
    //override val creatureType: String = "domestic"

    override def eat: Unit = {
      super.eat
      println("crunch crunch")
    }
  }

  val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)


  //type substitution (broad: polymorphism)
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat

  // overRIDING vs overLOADING

  //super

  // preventing override
  // use final on member, final functions can't be override
  // use final on the entire class, final classes can't be inherited(being extended)
  // seal the class = extend class in THIS FILE ONLY, prevent extension in other files


}
