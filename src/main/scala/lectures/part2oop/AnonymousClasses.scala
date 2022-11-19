package lectures.part2oop

object AnonymousClasses extends App {
  abstract class Animal {
    def eat: Unit
  }

  //anonymous class
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("ahahaha")
  }

  /*
  equivalent with
  class AnonynousClasses$$non$1 extends Animal {
      override def eat: Unit = println("ahahaha")
    }
    val funnyAnimal: Anima = new AnonynousClasses$$non$1()
  */


  println(funnyAnimal.getClass)
  funnyAnimal.eat

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name, How can I help you?")
  }

  val jim = new Person("jim") {
    override def sayHi: Unit = println(s"Hi, my name is jim")
  }
  jim.sayHi

  /*
    1. Generic trait MyPredicate[-T] - with a little method test(T] => Boolean
    2. Generic trait MyTransformer[-A, B] - method to transform(A) => B
    3. MyList:
        - map(transformer) => MyList
        - filter(predicate) => MyList
        - flatMap(transformer from A to MyList[B] => MyList[B]

        class EvenPredicate methods MyPrdicate[Int]
        class StringtoIntTransformer extends MyTransformer[String, Int]

        [1, 2, 3].map(n * 2) = [2, 4, 6)
        [1, 3, 3].filter(n % 2) = [2, 4]
        [1, 2, 3].flatMap(n => [n , n + 1]) => [1,2,2,3,3,4]
  */
}
