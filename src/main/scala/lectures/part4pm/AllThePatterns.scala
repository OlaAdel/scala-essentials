package lectures.part4pm

import exercises.{ConsGenericList, EmptyGenericList, GenericList}

object AllThePatterns extends App {

  // 1 - constants
  val x: Any = "Scala"
  val constants = x match
    case 1 => "a number"
    case "Scala" => "THE Scala"
    case true => "The Truth"
    case AllThePatterns => "A singleton object"


  // 2 - match anything
  // 2.1 wildcard

  val matchAnything = x match
    case _ =>

  //2.2 variable
  val matchAVariable = x match
    case something => s"I've found $something"

  // 3. tuples
  val aTuple = (1, 2)
  val matchATuple = aTuple match
    case (1, 1) =>
    case (something, 2) => s"I've found $something"

  val nestedTuple = (1, (2, 3))
  val matchANestedTuple = nestedTuple match
    case (_, (2, v)) =>

  // PMs can be NESTED

  // 4 - case classes - constructor pattern
  // PMs can be nested with CCs as well
  val aList: GenericList[Int] = ConsGenericList(1, ConsGenericList(2, EmptyGenericList))

  val matchAList = aList match
    case EmptyGenericList =>
    case ConsGenericList(head, ConsGenericList(subhead, subTail)) =>

  // 5 - list patterns

  val aStandardList = List(1, 2, 3, 42)
  val standardListMatching = aStandardList match
    case List(1, _, _, _) => // extractor - advanced
    case List(1, _*) => // list of arbitrary length - advanced
    case 1 :: List(_) => //infix pattern
    case List(1, 2, 3) :+ 42 => //infix pattern


  // 6 - type specifiers

  val unknown: Any = 2
  val unknownMatch = unknown match
    case list: List[Int] => //explicit type specifiers
    case x: Int =>

  // 7 - name binding

  val nameBindingMatch = aList match
    case nonEmptyList@ConsGenericList(_, _) => //name binding - use the name later(here)
    case ConsGenericList(1, rest@ConsGenericList(_, _)) => //name binding inside nested patterns

  // 8- multi-patterns
  val multiPattern = aList match
    case EmptyGenericList | ConsGenericList(0, _) => //composed pattern (multi-pattern)

  // 9 - if guards
  val secondElementSpecial = aList match
    case ConsGenericList(_, ConsGenericList(specialElement, _)) if specialElement % 2 == 0 =>


  val numbers = List(1, 2, 3)
  val numbersMatch = numbers match
    case listOfStrings: List[String] => "a list of strings"
    case listOfNumbers: List[Int] => "a list of numbers"
    case _ => ""

  println(numbersMatch)
  //JVM trick question




}
