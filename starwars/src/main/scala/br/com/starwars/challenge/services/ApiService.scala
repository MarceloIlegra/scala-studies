package br.com.starwars.challenge.services

import br.com.starwars.challenge.model.{Vehicle, Travel, Person}
import br.com.starwars.challenge.services.rescue.RescueNonElder

import scala.collection.mutable.ListBuffer

class ApiService {

  def createTravels(people: List[Person]):List[Travel] = {
    val groups = groupBySpecies(people)
    val elders = getAllElders(groups)

    val vehicle = new Vehicle("XXXX", 4)

    var travels = new ListBuffer[Travel]()

    elders.foreach(p => {
      val empty: Boolean = travels.isEmpty
      if(empty) {
        travels += new Travel(vehicle, ListBuffer[Person](p), "high", "elder")
      } else {
        if(travels.last.itHasFreePlace(p))
          travels.last.add(p)
        else{
          travels += new Travel(vehicle, ListBuffer[Person](p), "high", "elder");
        }
      }
    })

    val rescueNonElders = new RescueNonElder
    List(travels.toList, rescueNonElders.rescue(people, vehicle)).flatten.toList

  }

  def groupBySpecies(people :List[Person]) = people.groupBy(person => person.urlSpecie)

  def getAllElders(groups: Map[String, List[Person]]) =
    groups map {case (specie, people) => sortList(people)(0)} toList

  def getNonElders(groups: Map[String, List[Person]]) =
    (groups map { case (specie, people) => sortList(people).tail} toList).flatten

  def sortList(people: List[Person]) = people.sortBy(p => p)

}