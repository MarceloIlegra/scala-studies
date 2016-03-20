package week2

object App {

  def main(args: Array[String]) {
    println(factorial(4))
    println(factorial2(4))
  }

  def factorial(x:Int) :Int = {
    if(x == 0) 1 else x * factorial(x - 1)
  }

  def factorial2(x: Int) :Int = {

    def loop(acc:Int, x:Int) :Int = {
      if(x == 0) acc
      else loop(acc * x, x -1)
    }
    loop(1, x)
  }

}
