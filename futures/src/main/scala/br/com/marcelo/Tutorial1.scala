package br.com.marcelo

import scala.concurrent.{ExecutionContext, Future}
import ExecutionContext.Implicits.global
import scala.util.Random

object Tutorial1 {

  def main(args: Array[String]) {

    //Example 1
    val sayHello: Future[String] = Future {
      Thread.sleep(1000)
      "hello"
    }
    sayHello onSuccess {
      case message => Console.println(s"He said '$message'")
    }
    Console.println("Waiting..")
    Thread.sleep(2000)

    //Example 2

    val tryDivideByZero = Future {
      Thread.sleep(1000)
      1 / 0
    }

    tryDivideByZero onFailure {
      case e: ArithmeticException => Console.println(s"Don't be silly!")
    }

    Console.println("Try dividing by zero")

    Thread.sleep(2000)


    // example 2
    //
    // if one future depends on the result of another
    // you could nest callbacks
    // or use `map` to "chain" them
    // `map` returns a future which can be trapped via callbacks

    val firstLove = Future {
      Thread.sleep(500)
      "i love you"
    }

    val thenBetray = firstLove map {
      case loveLetter => {
        Console.println(loveLetter)
        Thread.sleep(500)
        "not really"
      }
    }

    thenBetray onSuccess {
      case partingWords => Console.println(partingWords)
    }

    Thread.sleep(2000)


    // example 3
    //
    // multiple inter-dependent futures typically create a nested callback mess
    // this is avoided with the `for` construct
    // `for` can "chain" multiple futures, and "apply" another `future` on their results

    val calculateMyScore = Future {
      Thread.sleep(500)
      new Random().nextInt(10)
    }

    val calculateYourScore = Future {
      Thread.sleep(500)
      new Random().nextInt(10)
    }

    val doIWin = for {
      myScore <- calculateMyScore
      yourScore <- calculateYourScore
    } yield myScore > yourScore


    doIWin onSuccess {
      case b: Boolean => Console.println(if (b) "yes" else "no")
    }

    Console.println("Do I win?")

    Thread.sleep(2000)


    //Fonte:
  }

}
