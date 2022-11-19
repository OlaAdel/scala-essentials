package lectures.part1basics

import scala.annotation.tailrec

object Functions extends App {

  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("Hello", 5))

  def aParameterlessFunction(): Int = 42

  println(aParameterlessFunction())

  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n - 1)
  }

  println(aRepeatedFunction("Hello", 3))

  // WHEN YOU NEED LOOPS, USE RECURSION

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int) = a + b

    aSmallerFunction(n, n - 1)
  }

  /*
    1. A greeting function(name, age) -> "Hi, my name is $name, I'm $age years ola"
    2. Factorial function 1 * 2 * ... * n
    3. A Fibonacci function
        f(1) = 1
        f(2) = 1
        f(n) = f(n - 1) + f(n - 2)
    4. Tests if a number is prime
  */
  //1
  def greetingFunction(name: String, age: Int): Unit = {
    println(s"Hi, my name is ${name}, I'm ${age} years old")
  }

  greetingFunction("Ola", 24)

  //2
  def factorialFunction(n: Int): Int = {
    if (n == 1) 1
    else n * factorialFunction(n - 1)
  }

  println(factorialFunction(5))

  //3
  def fibonacciFunction(n: Int): Int = {
    if (n == 1 || n == 2) 1
    else fibonacciFunction(n - 1) + fibonacciFunction(n - 2)
  }

  println(fibonacciFunction(10))
  //4

  def isPrime(n: Int): Boolean = {

    def isPrimeUntil(t: Int): Boolean = {
      if (t <= 1) true
      else (n % t != 0) && isPrimeUntil(t - 1)
    }

    isPrimeUntil(n / 2)
  }

  println(isPrime(37))
  println(isPrime(2003))
  println(isPrime(37 * 18))
}
