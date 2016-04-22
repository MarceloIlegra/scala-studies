package br.com.starwars.challenge.services.rescue

import br.com.starwars.challenge.model.{Person, Vehicle}
import org.scalatest.FunSuite

class RescueElderTest extends FunSuite{

  test("Should return only elders of each specie") {

    val vehicle = new Vehicle("XXXX", 4)
    val service = new RescueElder()

    val a = new Person("url", "person a", "18 BBY", "speciea", "100")
    val b = new Person("url", "person b", "20 ABY", "speciea", "100")
    val c = new Person("url", "person c", "20 ABY", "specieb", "100")
    val d = new Person("url", "person d", "20 ABY", "speciec", "100")
    val e = new Person("url", "person e", "18 ABY", "speciec", "100")
    val f = new Person("url", "person f", "50 BBY", "specied", "100")
    val g = new Person("url", "person g", "30 BBY", "specied", "100")
    val h = new Person("url", "person g", "30 BBY", "speciee", "100")

    val elders = service.rescue(List(a, b, c, d, e, f, g, h), vehicle)

    assert(elders.size == 2)
    assert(elders.count(t => t.priority == "high") == 2)
    assert(elders.count(t => t.specie == "elder") == 2)
  }

}
