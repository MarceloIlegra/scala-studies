package br.com.marcelo

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}
import ExecutionContext.Implicits.global

object PocFutureAndLists {

  def main(args: Array[String]) {
    println("> Sequence process")
    SequenceList.example

    println("=====\n")
    println("> Future process")

    FutureList.example
  }

}

object FutureList{

  def example = {
    val start = System.nanoTime()

    val o1 = Operation(2, 3, 1);
    val o2 = Operation(2, 4, 3);
    val o3 = Operation(2, 5, 2);
    val operations = List(o1,o2,o3)

    val futures = for {
      op <- operations
    } yield {
      val f = sum(op.x, op.y, op.seconds)
      f onSuccess {
        case x => x
      }
      f
    }
    val sequence: Future[List[Int]] = Future.sequence(futures) //Success(List(5, 6, 7))

    val x = Await.result(sequence, Duration.Inf).asInstanceOf[List[Int]]

    println(x)

    val end = System.nanoTime()
    println("Elapsed time: " + ((end - start)/1000000000.0) + " s")

  }

  def sum(x: Int, y: Int, seconds: Int) = Future{
    println("Init sum process x = " + x + " y = " + y + " seconds = " + seconds )
    Thread.sleep(seconds * 1000)
    println("processing")
    x + y
  }

}

object SequenceList{

  def example = {
    val start = System.nanoTime()

    val o1 = Operation(2, 3, 1);
    val o2 = Operation(2, 4, 3);
    val o3 = Operation(2, 5, 2);
    val operations = List(o1,o2,o3)
    println(operations.map(o => sum(o.x, o.y, o.seconds)))

    val end = System.nanoTime()
    println("Elapsed time: " + ((end - start)/1000000000.0) + " s")
  }

  def sum(x: Int, y: Int, seconds: Int) = {
    println("Init sum process x = " + x + " y = " + y + " seconds = " + seconds )
    Thread.sleep(seconds * 1000)
    println("processing")
    x + y
  }

}


case class Operation(val x: Int, val y: Int, val seconds: Int)





