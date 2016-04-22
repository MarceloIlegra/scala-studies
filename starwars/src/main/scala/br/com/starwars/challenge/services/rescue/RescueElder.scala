package br.com.starwars.challenge.services.rescue

import br.com.starwars.challenge.model.{Vehicle, Person, Travel}

import scala.collection.mutable.ListBuffer

class RescueElder {

  def rescue(people: List[Person], vehicle: Vehicle) = {
    val elders = getAllElders(people)

    val vehicle = new Vehicle("XXXX", 4)

    var travels = new ListBuffer[Travel]()

    elders.foreach(p => {
      if(travels.isEmpty) {
        travels += new Travel(vehicle, ListBuffer[Person](p), "high", "elder")
      } else {
        if (travels.last.itHasFreePlace(p)){
          travels.last.add(p)
        }else{
          travels += new Travel(vehicle, ListBuffer[Person](p), "high", "elder");
        }
      }
    })
    travels.toList
  }
  def getAllElders(allPeople: List[Person]) =
      allPeople.groupBy(p => p.urlSpecie) map { case (specie, people) => people.sortBy(p => p).head } toList


}
