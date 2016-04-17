package br.com.starwars.challenge.services

import br.com.starwars.challenge.model.Person

class ApiService {

  def groupBySpecies(people :List[Person]) = people.groupBy(person => person.urlSpecie)

  def getAllElders(groups: Map[String, List[Person]]) =
    groups map {case (specie, people) => sortList(people)(0)} toList

  def sortList(people: List[Person]) = people.sortBy(p => p)


}
