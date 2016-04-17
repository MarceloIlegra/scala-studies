package br.com.starwars.challenge.services

import br.com.starwars.challenge.model.{Travel, Person}
import org.scalatest.FunSuite

class ApiServiceTest extends FunSuite {

  test("Should people by species") {
    val service = new ApiService()

    val a = new Person("url", "person a", "18 BBY", "speciea", "100")
    val b = new Person("url", "person b", "20 ABY", "speciea", "100")
    val c = new Person("url", "person c", "20 ABY", "specieb", "100")
    val d = new Person("url", "person d", "20 ABY", "speciec", "100")
    val e = new Person("url", "person e", "20 ABY", "speciec", "100")

    val groupedSpecies = service.groupBySpecies(List(a, b, c, d, e))

    assert(groupedSpecies.get("speciea").get.size == 2)
    assert(groupedSpecies.get("specieb").get.size == 1)
    assert(groupedSpecies.get("speciec").get.size == 2)
  }


  test("Should return the elder of each specie") {
    val service = new ApiService()

    val a = new Person("url", "person a", "18 BBY", "speciea", "100")
    val b = new Person("url", "person b", "20 ABY", "speciea", "100")

    val c = new Person("url", "person c", "20 ABY", "specieb", "100")

    val d = new Person("url", "person d", "20 ABY", "speciec", "100")
    val e = new Person("url", "person e", "18 ABY", "speciec", "100")

    val f = new Person("url", "person f", "50 BBY", "specied", "100")
    val g = new Person("url", "person g", "30 BBY", "specied", "100")

    val groups = Map("speciea" -> List(a, b), "specieb" -> List(c), "speciec" -> List(d, e), "specied" -> List(f, g))

    val elders = service.getAllElders(groups)

    assert(elders.size == 4)
    assert(elders(0).name == "person a")
    assert(elders(1).name == "person c")
    assert(elders(2).name == "person e")
    assert(elders(3).name == "person f")

  }

  test("Should sort people by age") {
    val service = new ApiService()

    val a = new Person("url", "person a", "20 BBY", "speciea", "100")
    val b = new Person("url", "person b", "30 ABY", "speciea", "100")
    val c = new Person("url", "person c", "30 BBY", "specieb", "100")
    val d = new Person("url", "person d", "10 BBY", "speciec", "100")
    val e = new Person("url", "person e", "5 ABY", "speciec", "100")

    val people = service.sortList(List(a, b, c, d, e))

    assert(people(0).name == "person c")
    assert(people(1).name == "person a")
    assert(people(2).name == "person d")
    assert(people(3).name == "person e")
    assert(people(4).name == "person b")

  }

  test("should return non-elders by each specie") {
    val service = new ApiService()

    val a = new Person("url", "person a", "18 BBY", "speciea", "100")
    val b = new Person("url", "person b", "20 ABY", "speciea", "100")

    val c = new Person("url", "person c", "20 ABY", "specieb", "100")

    val d = new Person("url", "person d", "20 ABY", "speciec", "100")
    val e = new Person("url", "person e", "18 ABY", "speciec", "100")

    val f = new Person("url", "person f", "50 BBY", "specied", "100")
    val g = new Person("url", "person g", "30 BBY", "specied", "100")

    val nonElders = service.getNonElders(Map("speciea" -> List(a, b), "specieb" -> List(c), "speciec" -> List(d, e), "specied" -> List(f, g)))

    assert(nonElders.size == 3)
    assert(nonElders(0).name == "person b")
    assert(nonElders(1).name == "person d")
    assert(nonElders(2).name == "person g")
  }

  test("should create travel OK") {
    val service = new ApiService()

    val a = new Person("url", "person a", "18 BBY", "speciea", "100")
    val b = new Person("url", "person b", "20 ABY", "speciea", "100")

    val c = new Person("url", "person c", "20 ABY", "specieb", "100")

    val d = new Person("url", "person d", "20 ABY", "speciec", "100")
    val e = new Person("url", "person e", "18 ABY", "speciec", "100")

    val f = new Person("url", "person f", "50 BBY", "specied", "100")
    val g = new Person("url", "person g", "30 BBY", "specied", "100")
    val g2 = new Person("url", "person g", "30 BBY", "specied", "100")

    val people = List(a, b, c, d, e, f, g, g2)

    val travels: List[Travel] = service.createTravels(people)

    assert(travels(0).people.size == 4)
    assert(travels.size == 4)


  }

}
