package lectures.part3fp

object WhatsAFunction extends App {

  // DREAM: use functions as first class elements
  // problem: oop

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  // function types = Function1[A, B]

  val stringToIntConverter: Function1[String, Int] = new Function[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("34") + 4)


  val adder: (Int, Int) => Int =
    (a: Int, b: Int) => a + b

  println(adder(2, 3))

  // Function types Function2[A, B, R] === (A, B) => R

  // ALL SCALA FUNCTIONS ARE OBJECTS


  /*
  1. a function which takes 2 strings and concatenates them
  2. transform the MyPredicate and MyTransformer into function types
  3. define a function which takes an int and returns another function which takes an int and returns an int
      - what's the type of this function
      - how to do it
  */

  val concatenator: (String, String) => String = (a: String, b: String) => a + " " + b
  println(concatenator("Hello", "World!"))

  val multiply2: Int => Int = _ * 2
  val multiply3: Int => Int = element => element * 3

  val multiplier: Int => (Int => Int) = (n: Int) => n match
    case 2 => multiply2
    case 3 => multiply3


  println(multiplier(2)(5))
  println(multiplier(3)(5)) //curried function


}

trait MyFunction[A, B] {
  def apply(element: A): B
}
