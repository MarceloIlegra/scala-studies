package br.com.starwars.challenge.restclient

import br.com.starwars.challenge.model.Person
import br.com.starwars.challenge.restclient.model.PersonRestModel
import com.stackmob.newman.response.HttpResponse
import org.json4s.DefaultFormats
import org.json4s.jackson.JsonMethods._

object ApiRestParser {

  def personParse(response:HttpResponse) = {
    implicit val formats = DefaultFormats
    val personRestModel = parse(response.bodyString).extract[PersonRestModel]
    new Person(null, personRestModel.name, personRestModel.birth_year, personRestModel.species.head, personRestModel.mass)
  }

}
