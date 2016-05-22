package week5.lists

object Main {

  def main(args: Array[String]) {
    val strings = List("a", "b", "c", "er")
    print(strings)
  }

  def print(strings: List[String]): List[String] = strings match {
    case List() => List()
    case x :: xs => println(x); print(xs)
  }

}
