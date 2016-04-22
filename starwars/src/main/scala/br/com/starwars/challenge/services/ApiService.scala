package br.com.starwars.challenge.services

import br.com.starwars.challenge.model.{Person, Travel, Vehicle}
import br.com.starwars.challenge.services.rescue.{RescueElder, RescueNonElder}

class ApiService {

  def createTravels(people: List[Person]):List[Travel] = {
    val vehicle = new Vehicle("XXXX", 4)
    val rescueNonElders = new RescueNonElder
    val rescueElders = new RescueElder
    List(rescueElders.rescue(people, vehicle), rescueNonElders.rescue(people, vehicle)).flatten.toList
  }

  def groupBySpecies(people :List[Person]) = people.groupBy(person => person.urlSpecie)

  def getAllElders(groups: Map[String, List[Person]]) =
    groups map {case (specie, people) => sortList(people)(0)} toList

  def getNonElders(groups: Map[String, List[Person]]) =
    (groups map { case (specie, people) => sortList(people).tail} toList).flatten

  def sortList(people: List[Person]) = people.sortBy(p => p)

}