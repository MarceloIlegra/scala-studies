package week1


object Run {

  def main(args: Array[String]) {
    println(Session.sqrt(2));
    println(Session.sqrt(4));
    println(Session.sqrt(16));
    println(Session.sqrt(1e-6));
    println(Session.sqrt(1e-60));
  }

}

object Session {

  def abs(x: Double) = if(x < 0) -x else x

  def sqrt(x: Double) : Double = {

    def sqrtInt(guess: Double) :Double =
      if(isGoodEnough(guess)) guess
      else sqrtInt(improve(guess))

    def isGoodEnough(guess: Double) = abs(guess * guess - x) / x < 0.001

    def improve(guess: Double) = (guess + x / guess) / 2

    sqrtInt(1.0)
  }

}
