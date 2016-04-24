package br.com.starwars.challenge.restclient

import br.com.starwars.challenge.model.{Vehicle, Person}
import br.com.starwars.challenge.restclient.model.{PersonRestModel, VehicleRestModel}
import com.stackmob.newman._
import com.stackmob.newman.dsl._

import scala.concurrent._
import scala.concurrent.duration._
import java.net.URL
import org.json4s._
import org.json4s.jackson.JsonMethods._

object ApiRestClient {

  def findPeople(listOfIds: List[Int]) = {
    listOfIds.map(id => findPerson(id))
  }

  def findPerson(id:Int) = {
    val APIUrl = "http://swapi.co/api/people/"
    implicit val httpClient = new ApacheHttpClient
    val url = new URL(APIUrl+id)
    val response = Await.result(GET(url).apply, 5.second)
    ApiRestParser.personParse(response)
  }

  def findVehicle(id: Int) = {
    val APIUrl = "http://swapi.co/api/vehicles/"
    implicit val httpClient = new ApacheHttpClient
    val url = new URL(APIUrl+id)
    val response = Await.result(GET(url).apply, 70.second)
    val jsonString = parse(response.bodyString)

    implicit val formats = DefaultFormats
    println(jsonString)
    val vehicleRestModel = jsonString.extract[VehicleRestModel]
    new Vehicle(vehicleRestModel.name, vehicleRestModel.passengers.toInt)
  }

}
