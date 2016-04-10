package br.com.starwars.challenge.model

class Person(val urlPeople: String, val name: String, val birthYear: String, val urlSpecie: String, val mass: String) {

  def isOlderThan(that: Person):Boolean = {

    val thisBirthAge = birthYear.split(" ")(1)
    val thatBirthAge = that.birthYear.split(" ")(1)

    if(thisBirthAge == thatBirthAge){
      val thisYear = birthYear.split(" ")(0).toInt
      val thatYear = that.birthYear.split(" ")(0).toInt
      if(thisBirthAge == "BBY"){
        thisYear > thatYear
      } else {
        thisYear < thatYear
      }
    } else if(thisBirthAge == "BBY" && thatBirthAge == "ABY"){
      true
    } else {
      false
    }

  }

}
