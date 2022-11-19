package lectures.part4pm

object BracelessSyntax {

  // if - expresions
  val onIFExperssion = if (2 > 3) "bigger" else "smaller"
  //java-style
  val onIFExperssion_v2 =
    if (2 > 3) {
      "bigger"
    } else {
      "smaller"
    }

  //compact
  val onIFExpression_v3 =
    if (2 > 3) "bigger"
    else "smaller"

  // scala 3
  val onIFExpression_v4 =
    if 2 > 3 then
      "bigger" //higher indentation than the if part
    else
      "smaller"

  val onIFExpression_v5 =
    if 2 > 3 then
      val result = "bigger"
      result
    else
      val result = "smaller"
      result

  val onIFExpression_v6 = if 2 > 3 then "bigger" else "smaller"

  val aForComprehension = for {
    n <- List(1, 2, 3)
    s <- List("black", "white")
  } yield s"$n$s"


  //scala 3
  val aForComprehension_v2 =
    for
      n <- List(1, 2, 3)
      s <- List("black", "white")
    yield s"$n$s"

  //pattern matching
  val meaningOfLife = 42
  val aPatternMatch = meaningOfLife match {
    case 1 => "the one"
    case 2 => "double or nothing"
    case _ => "somthing else"
  }

  val aPatternMatch_v2 =
    meaningOfLife match
      case 1 => "the one"
      case 2 => "double or nothing"
      case _ => "somthing else"

  // methods without braces

  def computeMeaningOfLife(arg: Int): Int = {
    val partialResult = 40

    partialResult + 2
  }

  def computeMeaningOfLife_v2(arg: Int): Int =
    val partialResult = 40

    partialResult + 2

  // class definition with significant indentation (same for traits)

  class Animal {
    def ear(): Unit =
      println("I'm eating")
  }

  //scala 3
  class Animal2: //compiler expects the body of Animal
    def eat(): Unit =
      println("I'm eating")

    def grow(): Unit =
      println("I'm growing")
    end grow

    // 3000 more lines of code, it's preferred to add an end token
  end Animal2 //for if, match for, methods, classes, traits

  // anonymous classes

  val aSpecialAnimal = new Animal2 :
    override def eat(): Unit = println("I'm special")

  // indentation = strictly larger indentation
  // 3 spaces + 2 tabs > 2 spaces + 2 tabs
  // 3 spaces + 2 tabs > 3 spaces + 1 tab
  // 3 tabs + 2 spaces ??? 2 tabs + 3 spaces //not comparable


  def main(args: Array[String]): Unit = {
    println(onIFExpression_v5)
    println(computeMeaningOfLife_v2(55))
  }

}
