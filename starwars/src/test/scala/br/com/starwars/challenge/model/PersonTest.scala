package br.com.starwars.challenge.model

import org.scalatest.FunSuite

import scala.collection.SortedSet

class PersonTest extends FunSuite{

  test("when person that was born in BBY age should is older than person was born in ABY"){
    val person = new Person("url", "person name", "18 BBY", "specie", "100")
    val otherPerson = new Person("url", "other person", "20 ABY", "specie", "100")
    assert(person.isOlderThan(otherPerson))

    val person2 = new Person("url", "person name", "18 BBY", "specie", "100")
    val otherPerson2 = new Person("url", "other person", "20 ABY", "specie", "100")
    assert(otherPerson2.isOlderThan(person2) == false)

    val person3 = new Person("url", "person name", "18 BBY", "specie", "100")
    val otherPerson3 = new Person("url", "other person", "18 BBY", "specie", "100")
    assert(otherPerson3.isOlderThan(person3) == false)
  }

  test("BBY tests"){
    val person = new Person("url", "person name", "1800 BBY", "specie", "100")
    val otherPerson = new Person("url", "other person", "1700 BBY", "specie", "100")
    assert(person.isOlderThan(otherPerson))
  }

  test("ABY tests"){
    val person = new Person("url", "person name", "150 ABY", "specie", "100")
    val otherPerson = new Person("url", "other person", "200 ABY", "specie", "100")
    assert(person.isOlderThan(otherPerson))
  }

  test("testing sort"){
    val person1 = new Person("url", "person 1", "150 ABY", "specie", "100")
    val person2 = new Person("url", "person 2", "200 ABY", "specie", "100")
    val person3 = new Person("url", "person 3", "175 ABY", "specie", "100")

    val list = SortedSet(person1, person2, person3)

    list.foreach(p => println(p.name))
  }

}
