package br.com.starwars.challenge.services.rescue

import br.com.starwars.challenge.model.{Person, Travel, Vehicle}

import scala.collection.mutable.ListBuffer

class RescueNonElder{

  def rescue(people: List[Person], vehicle: Vehicle) = {
    var nonEldersTravels = new ListBuffer[Travel]()

    getNonElders(people).groupBy(person => person.urlSpecie).foreach{ case (specie, people) => people.foreach(p => addNonEldersTravels(p, specie)) }

    def addNonEldersTravels(person: Person, specie: String) = {
      val travelAvailable: ListBuffer[Travel] = nonEldersTravels.filter(t => t.specie == specie && t.itHasFreePlace(person))
      if(travelAvailable.size > 0) travelAvailable(0).add(person)
      else nonEldersTravels += new Travel(vehicle, ListBuffer[Person](person), "low", specie)
    }
    nonEldersTravels.toList

  }

  def getNonElders(allPeople: List[Person]) =
    (allPeople.groupBy(p => p.urlSpecie) map { case (urlSpecie, people) => people.sortBy(p => p).tail} toList).flatten

}
