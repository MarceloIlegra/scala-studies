package week3

object rationals {

  def main(args: Array[String]) {
    val x = new Rational(1, 2)
    val y = new Rational(2,3)
    println(x + y)
    println(x < y )
    println(x max y)
  //  val strage = new Rational(1,0)
    //println(strage.add(strage))
    println(new Rational(3))


    //Evaluation

    //new Rational(1,2)numer
    //[1/x,2/y][][new Rational(1,2)/this]
    //1

    //new Rational(1,2).less(new Rational(2,3))
    //[1/x,2/y][new Rational(2,3)/that][new Rational(1,2)/this]
    //this.numer * that.denom < that.numer * this.denom

  }

}


class Rational(x:Int, y:Int){

  require(y != 0, "denomminator must be nonzero")

  private def gcd(a:Int, b:Int):Int = if(b == 0) a else gcd(b, a % b)

  def this(x:Int) = this(x, 1)

  def numer = x
  def denom = y

  def <(that:Rational) = numer * that.denom < that.numer * denom

  def max(that:Rational) = if(this < that) that else this

  def + (that:Rational) :Rational = {
    new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
  }

  def unary_-  = new Rational(-numer, -denom)

  def -(that:Rational) = this + -that

  override def toString() = {
    val g = gcd(x, y)
    numer/g + "/" + denom/g
  }

}
