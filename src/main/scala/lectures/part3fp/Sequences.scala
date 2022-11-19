package lectures.part3fp

import scala.+:
import scala.util.Random

object Sequences extends App {
  // Seq

  val aSequence: Seq[Int] = Seq(1, 3, 2, 4)
  println(aSequence)

  println(aSequence.reverse)
  println(aSequence(2))

  println(aSequence ++ Seq(6, 5, 6))
  println(aSequence.sorted)

  // Ranges (they are also seq)

  val aRange: Seq[Int] = 1 until 10
  aRange.foreach(println)

  (1 to 10).foreach(_ => println("Hello"))

  // Lists

  val aList = List(1, 2, 3)
  val prepended = 42 :: aList
  println(prepended)

  val appended = 42 +: aList :+ 42
  println(appended)


  val apples5 = List.fill(5)("Apple")
  println(apples5)

  println(aList.mkString("-|-"))

  // arrays
  val numbers = Array(1, 2, 3, 4)
  val threeElements = Array.ofDim[Int](3)
  println(threeElements)
  println(threeElements(0))
  threeElements.foreach(println)

  //for reference type, values are initialized with null
  val threeStrings = Array.ofDim[String](3)
  println(threeStrings)
  println(threeStrings(0))
  threeStrings.foreach(println)

  numbers(2) = 0 // numbers.update(2, 0)
  println(numbers.mkString(" "))


  //arrays and seq
  //conversion from array to seq can be applied
  val numbersSeq: Seq[Int] = numbers //implicit conversion
  println(numbersSeq)

  //vectors
  val vector: Vector[Int] = Vector(1, 2, 3)
  println(vector)

  //vectors vs lists
  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), 0)
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // keeps references to tail
  // updating an element in the middle takes long time
  println(getWriteTime(numbersList))
  // depth of the tree is small
  // needs to replace an entire 32-element chunk
  println(getWriteTime(numbersVector))

  //2344113.291
  //4955.801


}
