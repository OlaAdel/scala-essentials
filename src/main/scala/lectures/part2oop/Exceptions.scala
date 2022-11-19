package lectures.part2oop

object Exceptions extends App {

  val x: String = null
  //println(x.length)
  // this will crash with a NullPointerException

  //throwing and catching exceptions

  //val aWeirdValue: String = throw new NullPointerException

  // throwable classes extend the Throwable class
  // Exception and Error are the major Throwable subtypes
  // Exception: something went wrong with the program: NullPointerException
  // Error: something went wront with the system: stackoverflow error

  //2. how to catch exceptions
  def getInt(withException: Boolean): Int = {
    if (withException) throw new RuntimeException("no Int for you")
    else 42
  }

  //unification of Int and Unit is AnyVal
  val potentialFail = try {
    getInt(false)
  }
  catch {
    case e: RuntimeException => 42
  }
  finally {
    // code that will get executed no matters what
    // optional
    // doesn't influence the return type of this expression
    // use finally only for side effects
    println("finally")
  }

  println(potentialFail)


  // 1. how to define your own exceptions

  class MyException extends Exception

  val exception = new MyException
  // throw exception

  /*

    1. crash your program with an OutOfMemoryError
    2. crash with StackOverflowError
    3 PocketCalculator
        - add(x,y)
        - subtract(x, y)
        - multiply(x,y)
        - divide(x, y)

        Throw
          - OverflowException if add(x,y) exceeds Int.MAX_VALUE
          - UnderflowException if subtract(x,y) exceeds Int.MIN_VALUE
          - MathCalculationException for division by 0
  */

  import scala.reflect.classTag



  //OutOfMemoryError
  //val array = Array.ofDim(Int.MaxValue)

  //StackOverflowError
  //def infinite: Int = 1 + infinite
  //infinite

  class UnderflowException extends RuntimeException
  class OverflowException extends RuntimeException
  class MathCalculationException extends RuntimeException

  object PocketCalculator {
    def add(x: Int, y: Int): Int =
      if (x.toLong + y.toLong > Int.MaxValue) throw new OverflowException
      else if (x.toLong + y.toLong < Int.MinValue) throw new UnderflowException
      else x + y


    def subtract(x: Int, y: Int): Int =
      if (x.toLong - y.toLong > Int.MaxValue) throw new OverflowException
      else if (x.toLong - y.toLong < Int.MinValue) throw new UnderflowException
      else x - y

    def multiply(x: Int, y: Int): Int =
      if (x.toLong * y.toLong > Int.MaxValue) throw new OverflowException
      else if (x.toLong * y.toLong < Int.MinValue) throw new UnderflowException
      else x * y


    def divide(x: Int, y: Int): Int =
      if(y == 0) throw new MathCalculationException
      else x / y
  }

  //PocketCalculator.add(500000000, Int.MaxValue)
  //PocketCalculator.subtract(900, Int.MinValue)
  //PocketCalculator.divide(5, 0)
}


