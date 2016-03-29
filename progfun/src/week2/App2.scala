package week2

object App2 {

  def main(args: Array[String]) {
    print(sum(x => x * x, 3, 5))
    print(sumCurrying(x => x * x)( 3, 5))
    print(sumShort(x=>x*x)(3,5))
  }

  def sum(f: Int => Int, a:Int, b:Int) = {

    def loop(a:Int, acc:Int): Int ={
      if(a > b) acc
      else loop(a+1, f(a) + acc)
    }

    loop(a, 0)
  }

  def sumCurrying(f:Int => Int): (Int, Int) => Int = {

    def sumf(a:Int, b:Int) :Int = if (a > b) 0 else f(a) + sumf( a + 1, b )

    sumf

  }

  def sumShort(f:Int => Int)(a: Int, b: Int): Int =
    if (a > b) 0 else f(a) + sumShort(f)( a + 1, b )


}
