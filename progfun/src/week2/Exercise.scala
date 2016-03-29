package week2

object Exercise {

  def main(args: Array[String]) {
    println(product(x => x * x)(3,7))
    println(productMapReduce(x => x * x)(3, 7))
    println(factorial(5))
  }

  def product(f:Int => Int)(a:Int, b:Int) :Int =
    if(a > b) 1 else f(a) * product(f)( a + 1 ,b)

  def factorial(n:Int) = product(x => x)(1, n)

  def mapReduce(f:Int => Int, combine:(Int, Int) => Int, zero: Int)(a:Int, b:Int):Int =
    if(a > b) zero
    else combine(f(a), mapReduce(f, combine, zero)(a + 1, b))

  def productMapReduce(f:Int => Int)(a:Int, b:Int) :Int = mapReduce(f, (x,y) => x * y, 1 )(a, b)
}
