package week4

import week3.{IntSet, NonEmpty, Empty}


object TypesTest {

  def main(args: Array[String]) {
    val a: Array[NonEmpty] = new Array(new NonEmpty(1, new Empty, new Empty)
    //val b: Array[IntSet] = a; <---- error
    //b(0) = new Empty

  }
}
