package lectures.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {

  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("SUPER FAILURE"))

  println(aFailure)
  println(aSuccess)


  def unsafeMethod(): String = throw new RuntimeException("NO STRING FOR YOU")

  val potentialFailure: Try[String] = Try(unsafeMethod())
  println(potentialFailure)

  //syntax sugar
  val anotherPotentialFailure = Try {
    //code that might throw
  }

  //utilities

  println(potentialFailure.isSuccess)

  //orElse
  def backupMethod(): String = "A valid result"

  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))
  println(fallbackTry)

  //IF you design the API

  def betterUnsafeMethod(): Try[String] = Failure(throw new RuntimeException("NO STRING FOR YOU"))

  def betterFallbackMethod(): Try[String] = Success("A valid result")

  val betterFallback = betterFallbackMethod().orElse(betterFallbackMethod())
  println(betterFallback)


  // map, flatMap, filter
  println("try functions")
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10))


  // => for-comprehensions

  /*
  */

  val hostname = "localhost"
  val port = "8080"

  def renderHTML(page: String): Unit = println(page)

  class Connection {
    //return page
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")
    }

    def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }

  //if you get the html page from the connection, print it the console, call renderHTML

  val possibleConnection = HttpService.getSafeConnection(hostname, port)
  val possibleHTML       = possibleConnection.flatMap(c => c.getSafe("url"))
  possibleHTML.foreach(renderHTML)

  HttpService.getSafeConnection(hostname, port)
    .flatMap(c => c.getSafe("url"))
    .foreach(renderHTML)

  for {
    connection <- HttpService.getSafeConnection(hostname, port)
    htmlPage   <- connection.getSafe("url")
  } yield renderHTML(htmlPage)

}
