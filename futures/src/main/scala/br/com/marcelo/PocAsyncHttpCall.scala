package br.com.marcelo

import java.net.URL

import com.stackmob.newman.ApacheHttpClient
import com.stackmob.newman.dsl._
import com.stackmob.newman.response.HttpResponse
import org.json4s.DefaultFormats
import org.json4s.jackson.JsonMethods._
import scala.concurrent.duration._

import scala.concurrent.{Await, ExecutionContext, Future}
import ExecutionContext.Implicits.global

object PocAsyncHttpCall {

  def main(args: Array[String]) {
    println("PocAsyncHttpCall")
    val people = PersonApiFacade2.findPeople(List(1,2,3))
    println("Results: ")
    people.foreach(p => println(p.name))
  }

}

object PersonApiFacade2{

  def findPeople(listOfIds: List[Int]) = {
    val start = System.nanoTime()

    val peopleFutures = for{
      idPerson <- listOfIds
    } yield {
      findPerson(idPerson)
    }

    val sequence: Future[List[PersonRestModel2]] = Future.sequence(peopleFutures)

    val x = Await.result(sequence, Duration.Inf).asInstanceOf[List[PersonRestModel2]]

    val end = System.nanoTime()
    println("Elapsed time: " + ((end - start)/1000000000.0) + " s")

    x
  }

  def findPerson(id:Int) = Future{
    println("started request to api")
    val APIUrl = "http://swapi.co/api/people/"
    implicit val httpClient = new ApacheHttpClient
    val url = new URL(APIUrl+id)
    val response = Await.result(GET(url).apply, 70.second)
    println("ended request to api")
    PersonApiParser2.personParse(response)
  }

}

object PersonApiParser2 {

  def personParse(response:HttpResponse) = {
    implicit val formats = DefaultFormats
    parse(response.bodyString).extract[PersonRestModel2]
  }

}

case class PersonRestModel2(val name: String,
                           val birth_year: String,
                           val species: List[String],
                           val mass: String)