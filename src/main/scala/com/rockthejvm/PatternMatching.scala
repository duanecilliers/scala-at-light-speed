package com.rockthejvm

object PatternMatching extends App {
  // switch expression
  val anInteger = 55
  val order = anInteger match {
    case 1 => "first"
    case 2 => "second"
    case 3 => "third"
    case _ => anInteger + "th"
  }
  // PM is an expression
  println(order)

  case class Person(name: String, age: Int)
  val bob = Person("Bob", 33) // Person.apply("Bob", 33)

  val personGreeting = bob match {
    case Person(n, a) => s"Hi, my name is $n and I am $a years old"
  }

  // deconstructing tuples
  val aTuple = ("Bon jovi", "Rock")
  val bandDescription = aTuple match {
    case (band, genre) => s"$band belongs to the genre $genre"
    case _ => "I don't know what you're talking about"
  }
  println(bandDescription)

  // decomposing lists
  val aList = List(1, 2, 3)
  val listDescription = aList match {
    case List(_, 2, _) => s"List containing 2 on it's second position"
    case _ => "Unknown list"
  }

  // if the PM doesn't match anything, it will throw a MatchError
  // PM will try all cases in sequence
}
