package br.com.marcelo

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}
import ExecutionContext.Implicits.global

object Poc {

  def main(args: Array[String]) {

    val s0 = System.nanoTime()
    SequenceProcess.example
    val s1 = System.nanoTime()
    println("Elapsed time: " + (s1 - s0) + "ns")


    val t0 = System.nanoTime()
    FutureProcess.example
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) + "ns")

  }

}

object SequenceProcess {

  def example = {
    println("Start calc")

    println("2 + 3 = " + sum(2, 3, 1))
    println("2 + 4 = " + sum(2, 4, 3))
    println("2 + 5 = " + sum(2, 5, 1))
  }

  def sum(x: Int, y: Int, seconds: Int) = {
    println("Init sum process x = " + x + " y = " + y + " seconds = " + seconds )
    Thread.sleep(seconds * 1000)
    x + y
  }

}

object FutureProcess {

  def example = {
    println("Start calc")

    val sum1 = sum(2, 3, 1)

    printSumFuture(sum1)

    val sum2 = sum(2, 4, 3)

    printSumFuture(sum2)

    val sum3 = sum(2, 5, 1)

    printSumFuture(sum3)

    Await.ready(sum1, Duration.Inf)
    Await.ready(sum2, Duration.Inf)
    Await.ready(sum3, Duration.Inf)

  }

  def printSumFuture(result: Future[Int]) = {
    result onSuccess{
      case value => Console.println(s"'$value'")
    }
  }

  def sum(x: Int, y: Int, seconds: Int): Future[Int] = Future {
    println("Init sum process x = " + x + " y = " + y + " seconds = " + seconds )
    Thread.sleep(seconds * 1000)
    x + y
  }
}
