package lectures.part3fp

import scala.util.Random

object Options extends App {

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None
  println(myFirstOption)
  println(noOption)

  //unsafe APIs

  def unsafeMethod(): String = null

  val result = Some(unsafeMethod()) //WRONG Some(null)
  println(result)

  val result1 = Option(unsafeMethod()) //Some or None
  println(result1)

  //chained methods

  def backupMethod(): String = "A valid result"

  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))
  println(chainedResult)

  //DESIGN unsafe APIs
  def betterUnsafeMethod(): Option[String] = None

  def betterBackupMethod(): Option[String] = Some("A valid result")

  betterUnsafeMethod().orElse(betterBackupMethod())

  //functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) //unsafe, DON't use it

  //map
  println("Option functions")
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(_ % 2 != 0))
  println(myFirstOption.flatMap(x => Option(x * 10)))
  println(myFirstOption.flatMap(_ => None))

  println(None.flatMap(_ => Option(5)))
  //for-comprehensions


  /*



  */

  val config: Map[String, String] = Map(
    //fetched from elsewhere
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected" //connect to some server
  }

  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] = {
      if (random.nextBoolean()) Some(new Connection)
      else None
    }
  }

  //try to establish a connection, if so - print the connect method

  val host: Option[String] = config.get(key = "host")
  val port: Option[String] = config.get(key = "port")

  (host, port) match
    case (Some(hostVal), Some(portVal)) => Connection(hostVal, portVal) match
      case Some(connection) => println(connection.connect)
      case _ => ()
    case _ => ()

  val connection = host.flatMap(h => port.flatMap(p => Connection(h, p)))
  /*
  if(h!= null)
    if(p!=null)
      return connection.apply(h, p)

  return null


  */
  val connectionStatus: Option[String] = connection.map(connection => connection.connect)

  /*
  if(connection != null)
    return connection.connect

  return null
  */
  println(connectionStatus)
  connectionStatus.foreach(println)

  //chained calls
  config.get("host").flatMap { h =>
    config.get("port").flatMap { p =>
      Connection.apply(h, p).map { connection => connection.connect }
    }
  }.foreach(println)


  val statusConnection = for {
    h <- config.get("host")
    p <- config.get("port")
    c <- Connection.apply(h, p)
  } yield c.connect

  println(statusConnection)


}
