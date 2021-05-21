package com.rockthejvm

object FunctionalProgramming extends App {
  // Scala is OO
  class Person(name: String) {
    def apply(age: Int) = println(s"I have aged $age years")
  }

  val bob = new Person("Bob")
  bob.apply(43)
  bob(43) // INVOKING bob as a function == bob.apply(43)

  /**
   * Scala runs on the JVM
   * Functional programming:
   * - compose functions
   * - pass functions as args
   * - return functions as results
   *
   * Conclusion: FunctionX, Function2, ...Function22
   * 22 is the maximum number of args you can pass to a function!
   */
  val simpleIncrementer = new Function1[Int, Int] {
    override def apply(arg: Int): Int = arg + 1
  }

  simpleIncrementer.apply(23) // 24
  simpleIncrementer(23) // simpleIncrementer.apply(23)

  /**
   * All Scala functions are instances of these FUNCTION_X types
   */
  // functino with 2 arguments and a String return type
  val stringConcatenator = new Function2[String, String, String] {
    override def apply(arg1: String, arg2: String): String = arg1 + arg2
  }
  stringConcatenator("I love", "Scala")

  // syntax sugars
  val doubler: Function1[Int, Int] = (x: Int) => 2 * x
  val doublerAlt: Int => Int = (x: Int) => 2 * x
  doubler(4)
  /**
   * equivalent to the much longer:
   *
   * new Function1[Int, Int] {
   *   override def apply(x: Int) = 2 * x
   * }
   */

  /**
   * Higher-order functions: take functions as args/return functions as results or both
   */
  val aMappedList: List[Int] = List(1, 2, 3).map(x => x + 1) // HOF
  println(aMappedList)

  val aFlatMappedList = List(1, 2, 3).flatMap(x => List(x, 2 * x))
  println(aFlatMappedList)

  val aFlatMappedListAlt = List(1, 2, 3).flatMap { x =>
    List(x, 2 * x)
  }

  val aFilteredList = List(1, 2, 3 ,4 ,5).filter(_ <= 3) // equivalent to `.filter(x => x <= 3)`
  println(aFilteredList)

  // all pairs between the numbers 1, 2, 3 and the letters 'a', 'b', 'c'
  val allPairs = List(1, 2, 3).flatMap(number => List('a', 'b', 'c').map(letter => s"$number-$letter"))
  println(allPairs)

  // for comprehensions - more legible syntax
  val alternativePairs = for {
    number <- List(1, 2, 3)
    letter <- List('a', 'b', 'c')
  } yield s"$number-$letter"
  println(alternativePairs)

  /**
   * Collections
   */
  // lists
  val aList = List(1, 2, 3, 4, 5)
  val firstEl = aList.head
  val rest = aList.tail
  val aPrependedList = 0 :: aList
  val anExtendedList = 0 +: aList :+ 6

  // sequences
  val aSequence: Seq[Int] = Seq(1, 2, 3) // Seq.apply(1, 2, 3)
  val accessedEl = aSequence(1) // the element at index 1: 2

  // vectors (very fast for large data): fast Seq implementation
  val aVector = Vector(1, 2, 3, 4, 5)

  // sets = no duplicates
  val aSet = Set(1, 2, 3, 4, 1, 2, 3) // Set(1, 2, 3, 4)
  val setHas5 = aSet.contains(5) // false
  val anAddedSet = aSet + 5 // Set(1, 2, 3, 4, 5)
  val aRemovedSet = aSet - 3 // Set(1, 2, 4)

  // ranges (useful for "iteration")
  val aRange = 1 to 1000
  val twoByTwo = aRange.map(x => 2 * x).toList // List(2, 4, 6, 8..., 2000)

  // tuples = groups of values under the same value
  val aTuple = ("Bon Jovi", "Rock", 1982)

  // maps
  val aPhonebook: Map[String, Int] = Map(
    ("Daniel", 123123123),
    ("Jane", 123908),
    "Diff syntax" -> 12309
  )
}
