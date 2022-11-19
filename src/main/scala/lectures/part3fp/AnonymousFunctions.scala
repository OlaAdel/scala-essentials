package lectures.part3fp

object AnonymousFunctions extends App {

  val doublerr = new Function[Int, Int] {
    override def apply(x: Int): Int = x * 2
  }
  // anonymous function (LAMBDA)
  val doubler: Int => Int = x => x * 2

  //multiple params in a lambda
  val adder: (Int, Int) => Int = (a, b) => a + b

  // no params
  val justDoSomething: () => Int = () => 3

  println(justDoSomething) //function itself
  println(justDoSomething()) //call

  //curly braces with lambdas

  val stringToInt = { (str: String) =>
    str.toInt
  }

  //MOAR syntactic sugar
  val niceIncrementer: Int => Int = _ + 1 //equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _

  /*
    1. MyList: replace all functionX calls with lambdas
    2. Rewrite the special adder as an anonymous function
  */

  val superAdd = (x: Int) => (y : Int) => x + y
  println(superAdd(2)(3))
}
