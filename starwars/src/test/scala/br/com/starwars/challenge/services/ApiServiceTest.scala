package br.com.starwars.challenge.services

import br.com.starwars.challenge.model.Person
import org.scalatest.FunSuite

class ApiServiceTest extends FunSuite{

  test("Should people by species") {
    val service = new ApiService()

    val a = new Person("url", "person a", "18 BBY", "speciea", "100")
    val b = new Person("url", "person b", "20 ABY", "speciea", "100")
    val c = new Person("url", "person c", "20 ABY", "specieb", "100")
    val d = new Person("url", "person d", "20 ABY", "speciec", "100")
    val e = new Person("url", "person e", "20 ABY", "speciec", "100")

    val people = List(a, b, c, d, e)
    val groupedSpecies = service.groupBySpecies(people)

    assert(groupedSpecies.get("speciea").get.size == 2)
    assert(groupedSpecies.get("specieb").get.size == 1)
    assert(groupedSpecies.get("speciec").get.size == 2)
  }

}
