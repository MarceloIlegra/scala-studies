package br.com.starwars.challenge.services

import br.com.starwars.challenge.model.Person

class ApiService {

  def groupBySpecies(people :List[Person]) = {
    people.groupBy(person => person.urlSpecie)
  }

}
