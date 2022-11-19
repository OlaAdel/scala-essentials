package lectures.part2oop

import playground.{PrinceCharming, Cindrella as Princess}

import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports extends App {

  // you need to be in the same package
  // package members are accessible by their simple name
  val writer = new Writer("Daniel", "RockTheJVM", 2018)

  // if in another package
  //import the package
  val princess = new Princess
  //or use the fully qualified name
  val princess2 = new playground.Cindrella


  // packages are in hierarchy
  // matching folder structure

  // package object
  sayHello
  println(SPEED_OF_LIGHT)

  //imports

  val prince = new PrinceCharming

  //use fully qualified names
  val date = new java.util.Date()
  //use aliasing
  val sqlDate = new SqlDate(2018, 5, 12)

  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???



}
