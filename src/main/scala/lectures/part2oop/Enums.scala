package lectures.part2oop

object Enums extends App {

  enum Permissions {
    case READ, WRITE, EXECUTE, NONE

    def openDocument(): Unit = {
      if (this == READ) println("opening document...")
      else println("reading not allowed")
    }
  }

  val somePermissions: Permissions = Permissions.WRITE
  somePermissions.openDocument()

  enum PermissionsWithBits(bits: Int) {
    case READ extends PermissionsWithBits(4)
    case WRITE extends PermissionsWithBits(2)
    case EXECUTE extends PermissionsWithBits(1)
    case NONE extends PermissionsWithBits(0)

  }

  object PermissionsWithBits {
    def fromBits(bits: Int): PermissionsWithBits =
      PermissionsWithBits.NONE
  }

  // standard API
  println(somePermissions.ordinal)
  val allPermissions = Permissions.values
  val readPermissions = Permissions.valueOf("READ")
  println(readPermissions)

}
