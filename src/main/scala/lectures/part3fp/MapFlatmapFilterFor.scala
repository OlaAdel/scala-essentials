package lectures.part3fp

object MapFlatmapFilterFor extends App {

  val list = List(1, 2, 3)
  println(list)

  println(list.head)
  println(list.tail)

  println(list.map(x => x * 2))
  println(list.filter(x => x % 2 == 0))
  println(list.flatMap(x => List(x, x + 1)))



  //print all combinations between two lists

  val numbers = List(1, 2, 3)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white")
  //List ("a1", "a2", ... "d4")

  //iterations
  println(numbers.flatMap(n => chars.map(c => s"$c$n")))
  println(numbers.flatMap(n => chars.flatMap(c => colors.map(color => s"$c$n$color"))))

  //foreach
  list.foreach(println)

  //for-comprehensions

  val forComprehensions =
    for {
      n     <- numbers if n % 2 == 0
      c     <- chars
      color <- colors
    } yield s"$c$n$color"

  println(forComprehensions)

  for {
    n <- numbers
  } println(n)


  // syntax overload
  list.map { x =>
    x * 2
  }

  /*
    1. MyList supports for comprehension?
        map(f : A => B) => MyList[B]
        filter(p: A => Boolean) => MyList[A]
        flatMap(f: A => MyList[B]) => MyList[B]
    2. A small collection of at most ONE element = Maybe[+T]
        - map, flatMap, filter
  */


}
