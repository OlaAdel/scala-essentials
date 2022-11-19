package lectures.part3fp

import scala.annotation.tailrec

object TuplesAndMaps extends App {

  //tuples = finite ordered "lists

  val aTuple = Tuple2(2, "Hello, Scala") // Tuple2[Int, String] = (Int, String)
  val aTuple2 = (2, "Hello, Scala") // Tuple2[Int, String] = (Int, String)


  println(aTuple2._1) //2
  println(aTuple.copy(_2 = "Goodbye Java"))
  println(aTuple2.swap)

  // Maps - keys -> values
  val aMap: Map[String, Int] = Map()

  val phoneBook = Map(("Jim", 555), "Daniel" -> 789).withDefaultValue(-1)
  // a -> b is sugar for (a, b)
  println(phoneBook)

  //map ops
  println(phoneBook.contains("Jim"))
  println(phoneBook("Jim"))

  //with default value
  println(phoneBook("Mary"))

  val newPairing = "Mary" -> 678
  val newPhoneBook = phoneBook + newPairing
  println(newPhoneBook)

  //functions on maps
  //map, flatMap, filter
  println(phoneBook.map { case (str, i) => (str.toLowerCase, i) })

  //filterKeys
  println(phoneBook.filterKeys(_.startsWith("J")).toMap)

  //mapValues
  println(phoneBook.mapValues(number => number * 10).toMap)

  //conversions to other collections
  println(phoneBook.toList)
  println(List(("Daniel", 555)).toMap)

  val names = List("Bob", "James", "Angela", "Mary", "Daniel")
  println(names.groupBy(name => name.charAt(0)))

  /*
    1. what would happen if I had two original entries "Jim" -> 555, "JIM" -> 9007
  //  println(phoneBook.map { case (str, i) => (str.toLowerCase, i) })

      careful with mapping keys
    2. overly simplified social network based on maps
        Person = String
        - add a person to the network
        - remove a person
        - friend(mutual)
        - unfriend(mutual)

        - number of friends of a person
        - person with most friends
        - how many people have No friends
        - if there is a social connection between two people(direct or not)
  */
  println(Map("Jim" -> 555, "JIM" -> 9494).map { case (str, i) => (str.toLowerCase, i) })

  val mapa = Map[String, Set[String]]()
  val network1 = SocialNetwork.add("Ola", mapa)
  val network2 = SocialNetwork.add("Omar", network1)
  val network3 = SocialNetwork.add("Mohamed", network2)
  val network4 = SocialNetwork.add("Somaia", network3)

  val network5 = SocialNetwork.friend("Ola", "Omar", network4)
  val network6 = SocialNetwork.friend("Ola", "Mohamed", network5)
  val network7 = SocialNetwork.friend("Mohamed", "Somaia", network6)
  val network8 = SocialNetwork.friend("Somaia", "Omar", network7)

  val network9 = SocialNetwork.unfriend("Ola", "Mohamed", network8)

  println(network9)
  println(SocialNetwork.numberOfFriends("Somaia", network9))

  val network10 = SocialNetwork.friend("Somaia", "Ola", network9)

  println(SocialNetwork.personWithMostFriends(network10))

  println(SocialNetwork.haveNoFiends(network10))

  println(network10)
  println(SocialNetwork.areConnected("Mohamed", "Omar", network10))

  val network11 = SocialNetwork.remove("Ola", network10)

  println(network11)


}

object SocialNetwork {

  def add(name: String, network: Map[String, Set[String]]): Map[String, Set[String]] =
    network + ((name, Set.empty))

  def remove(name: String, network: Map[String, Set[String]]): Map[String, Set[String]] = {
    @tailrec
    def auxiliaryRemove(currentNetwork: Map[String, Set[String]]): Map[String, Set[String]] = {
      if (currentNetwork(name).isEmpty)
        currentNetwork - name
      else auxiliaryRemove(unfriend(name, currentNetwork(name).head, currentNetwork))

    }
    auxiliaryRemove(network)
  }

  def friend(first: String, second: String, network: Map[String, Set[String]]): Map[String, Set[String]] = {
    val firstFriends: Set[String] = network(first) + second
    val secondFriends: Set[String] = network(second) + first

    network + (first -> firstFriends) + (second -> secondFriends)
  }

  def unfriend(first: String, second: String, network: Map[String, Set[String]]): Map[String, Set[String]] = {
    val firstFriends: Set[String] = network(first).filter(_ != second)
    val secondFriends: Set[String] = network(second).filter(_ != first)

    network + (first -> firstFriends) + (second -> secondFriends)
  }

  def numberOfFriends(name: String, network: Map[String, Set[String]]): Int =
    network(name).size

  def personWithMostFriends(network: Map[String, Set[String]]): String =
    network.maxBy { case (_, friends) => friends.size }._1

  def haveNoFiends(network: Map[String, Set[String]]): Int =
    network.count { (_, friends) => friends.size == 0 }

  def areConnected(first: String, second: String, network: Map[String, Set[String]]): Boolean = {
    def auxiliaryAreConnected(first: String, curFirst: String, second: String): Boolean = {
      if (network(curFirst).contains(second))
        true
      else
        network(curFirst).filter(_ != first).exists { friend => auxiliaryAreConnected(first, friend, second) }
    }

    auxiliaryAreConnected(first, first, second)
  }
}