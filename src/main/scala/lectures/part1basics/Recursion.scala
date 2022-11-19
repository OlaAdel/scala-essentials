package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): Int = {
    if (n <= 1) 1
    else {
      println(s"Computing factorial of ${n}")
      val result = n * factorial(n - 1)
      println(s"Computed factorial of ${n} = ${result}")
      result
    }
  }

  println(factorial(10))

  def anotherFactorial(n: Int): BigInt = {

    @tailrec
    def factorialHelper(n: Int, accumulator: BigInt): BigInt = {
      if (n <= 1) accumulator
      else factorialHelper(n - 1, accumulator * n)
    }

    factorialHelper(n, 1)
  }

  println(anotherFactorial(5000))

  // WHEN YOU NEED LOOPS, USE TAIL RECURSION


  /*
    1. concatenate a string n times
    2  isPrime function
    3. fibonacci function
  */

  //1
  def repeatString(aString: String, n: Int): String = {
    def helper(n: Int, resultString: String): String = {
      if (n == 0) resultString
      else helper(n - 1, resultString + aString)
    }

    helper(n, "")
  }

  println(repeatString("Hello", 3))

  //2

  def isPrime(n: Int): Boolean = {

    def isPrimeUntil(t: Int, accumulator: Boolean): Boolean = {
      if (t <= 1) accumulator
      else isPrimeUntil(t - 1, accumulator && (n % t != 0))
    }

    isPrimeUntil(n - 1, true)
  }

  println(isPrime(2003))
  println(isPrime(13 * 17))
  println(isPrime(37))

  //3

  def fibonacci(n: Int): Int = {
    def helper(i: Int, last: Int, nextToLast: Int): Int = {
      if (i >= n) last
      else helper(i + 1, last + nextToLast, last)
    }

    helper(2, 1, 1)
  }

  println(fibonacci(8))
  println(fibonacci(10))

}
