package br.com.starwars.challenge.services

import br.com.starwars.challenge.model.{Vehicle, Travel, Person}

import scala.collection.mutable.ListBuffer

class ApiService {

  def createTravels(people: List[Person]):List[Travel] = {
    val groups = groupBySpecies(people)
    val elders = getAllElders(groups)
    val nonElders = getNonElders(groups)

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
    var nonEldersTravels = new ListBuffer[Travel]()

    nonElders.groupBy(person => person.urlSpecie).foreach{
        case (specie, people) => people.foreach(p => {
          addNonEldersTravels(p, specie, nonEldersTravels)
        })
    }

    def addNonEldersTravels(person: Person, specie: String, travels: ListBuffer[Travel]) = {
      val k: ListBuffer[Travel] = travels.filter(t => t.specie == specie).filter(t => t.itHasFreePlace(person))
      if(k.size > 0) k(0).add(person)
      else travels += new Travel(vehicle, ListBuffer[Person](person), "low", specie)
      println("trying add person in travel")
    }

    List(travels.toList, nonEldersTravels.toList).flatten.toList

  }

  def groupBySpecies(people :List[Person]) = people.groupBy(person => person.urlSpecie)

  def getAllElders(groups: Map[String, List[Person]]) =
    groups map {case (specie, people) => sortList(people)(0)} toList

  def getNonElders(groups: Map[String, List[Person]]) =
    (groups map { case (specie, people) => sortList(people).tail} toList).flatten

  def sortList(people: List[Person]) = people.sortBy(p => p)

}
