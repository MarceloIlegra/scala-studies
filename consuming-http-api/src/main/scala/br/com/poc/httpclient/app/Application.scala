package br.com.poc.httpclient.app

import java.net.URL

import br.com.poc.httpclient.app.model.PeopleRequest
import com.stackmob.newman._
import com.stackmob.newman.dsl._

import scala.concurrent._
import scala.concurrent.duration._

import org.json4s._
import org.json4s.jackson.JsonMethods._

object Application {

  def main(args: Array[String]) {
    val APIUrl = "http://swapi.co/api/people/"
    implicit val httpClient = new ApacheHttpClient
    val url = new URL(APIUrl+"1")
    val response = Await.result(GET(url).apply, 5.second)
    val jsonString = parse(response.bodyString)

    implicit val formats = DefaultFormats
    println(jsonString)
    val p = jsonString.extract[PeopleRequest]
    println(s"Name:${p.name} Mass: ${p.mass} BirthYear: ${p.birth_year}")
    //val people = response.bodyAsCaseClass[PeopleRequest];


  }

}
