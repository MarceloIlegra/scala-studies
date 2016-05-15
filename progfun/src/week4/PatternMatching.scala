package week4

object PatternMatching {

  def main(args: Array[String]) {
    println(Eval(NumberExpression(2)))
    println(Eval(ProdExpression(NumberExpression(3), NumberExpression(2))))

    println(Show(SumExpression(NumberExpression(3), NumberExpression(2))))
    println(Show(ProdExpression(NumberExpression(3), NumberExpression(2))))
  }

}

trait Expression
case class NumberExpression(n: Int) extends Expression
case class SumExpression(e1: Expression, e2: Expression) extends Expression
case class ProdExpression(e1: Expression, e2: Expression) extends Expression

//Eval(SumExpression(NumberExpression(3), NumberExpression(2)))
//5
object Eval{
  def apply(e: Expression):Int = e match {
    case NumberExpression(n) => n
    case SumExpression(e1, e2) => this(e1) + this(e2)
    case ProdExpression(e1, e2) => this(e1) * this(e2)
  }
}

//Show(SumExpression(NumberExpression(3), NumberExpression(2)))
//3 + 2
object Show{
  def apply(e: Expression): String =  e match {
      case NumberExpression(n) => n.toString
      case SumExpression(e1, e2) => this(e1) + " + " + this(e2)
      case ProdExpression(e1, e2) => this(e1) + " * " + this(e2)
  }
}