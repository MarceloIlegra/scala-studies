package week2

import math.abs

object Exercise2 {

  val tolerance = 0.0001

  def isCloseEnough(x: Double, y: Double) =
    abs((x - y) / x) / x < tolerance

  def fixedPoint(f: Double => Double)(firstGuess:Double) = {
    def iterate(guess:Double): Double ={
      println(s"Guess:${guess}")
      val next = f(guess)
      if(isCloseEnough(guess, next)) next
      else iterate(next)
    }
    iterate(firstGuess)
  }

  def averageDamp(f: Double => Double)(x:Double) = (x + f(x)) / 2

  def sqrt(x:Double) =
    fixedPoint(averageDamp(y => x / y))(1)

  def main(args: Array[String]) {
   //println(fixedPoint(x => 1 + x/2)(1))
    print(sqrt(2))
  }

}
