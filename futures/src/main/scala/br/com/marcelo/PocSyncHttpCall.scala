package br.com.marcelo

import java.net.URL

import com.stackmob.newman.ApacheHttpClient
import com.stackmob.newman.response.HttpResponse
import org.json4s.DefaultFormats
import org.json4s.jackson.JsonMethods._
import com.stackmob.newman.dsl._
import scala.concurrent.duration._
import scala.concurrent.Await


object PocSyncHttpCall {

  def main(args: Array[String]) {
    println("PocSyncHttpCall")
    PersonApiFacade.findPeople(List(1,2,3))
  }

}

object PersonApiFacade{

  def findPeople(listOfIds: List[Int]) = {
    val start = System.nanoTime()
    val people = listOfIds.map(id => findPerson(id))
    val end = System.nanoTime()
    println("Elapsed time: " + ((end - start)/1000000000.0) + " s")
    people
  }

  def findPerson(id:Int) = {
    println("started request to api")
    val APIUrl = "http://swapi.co/api/people/"
    implicit val httpClient = new ApacheHttpClient
    val url = new URL(APIUrl+id)
    val response = Await.result(GET(url).apply, 70.second)
    println("ended request to api")
    PersonApiParser.personParse(response)
  }

}

object PersonApiParser {

  def personParse(response:HttpResponse) = {
    implicit val formats = DefaultFormats
    parse(response.bodyString).extract[PersonRestModel]
  }

}

case class PersonRestModel(val name: String,
                           val birth_year: String,
                           val species: List[String],
                           val mass: String)