package lectures.part1basics

object ValuesVariablesTypes extends App {
  val x: Int = 42
  println(x)

  // VALS ARE IMMUTABLE

  // COMPILER can infer types


  val aString: String = "Hello"
  val anotherString: String = "Goodbye"

  val aBoolean: Boolean = false
  val aChar: Char = 'a'
  val anInt: Int = x
  val aShort: Short = 3455
  val aLong: Long = 4544444444444444L
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.14


  //variables
  var aVariable: Int = 4
  aVariable = 5 // side effects
}
