package com.rockthejvm

object ObjectOrientation extends App{
  /**
   * Classes and instance
   */
  class Animal {
    // define fields
    val age: Int = 0
    // define mthods
    def eat() = println("I'm eating")
  }

  val anAnimal = new Animal

  // inheritance
  class Dog extends Animal

  // constructor arguments
  class Dog2(name: String) extends Animal
  val aDog = new Dog2("Lassie")

  // constructor arguments are NOT fields: need to put a val before the construction argument
  // aDog.name
  // class Dog2(val name: String) extends Animal

  // subtype polymorphism
  val aDeclaredAnimal: Animal = new Dog2("Hachi")
  aDeclaredAnimal.eat() // the most derived method will be called at runtime

  // abstract class
  abstract class WalkingAnimal {
    val hasLegs = true // by default public, can restrict by using private or protected
    protected val canRun = false
    def walk(): Unit
  }

  // "interface" = ultimate abstract type
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  trait Philosopher {
    def ?!(thought: String): Unit // valid method name
  }

  // single class inheritence and multi-trait inheritence "mixing"
  class Crocodile extends Animal with Carnivore with Philosopher {
    override def eat(animal: Animal): Unit = println("I am eating you, animal!")

    def ?!(thought: String): Unit = println(s"I was thinking: $thought")
  }

  val aCroc = new Crocodile
  aCroc.eat(aDog)
  aCroc eat aDog // infix notation = object method argument, only available for methods with ONE argument

  // operators in Scala are actually methods
  val basicMath = 1 + 2
  val anotherBasicMath = 1.+(2) // equivalent

  // anonymous classes
  val dinosaur = new Carnivore {
    override def eat(animal: Animal): Unit = println("I am a dinosaur so I can't eat anything")
  }

  /**
   * what you tell the compiler:
   *
   * class Carnivore_Anonymous_3434 extends Carnivore {
   *   override def eat(animal: Animal): Unit = println("I am a dinosaur so I can't eat anything")
   * }
   * val dinosaur = new Carnivore_Anonymous_3434
   */

  // singleton object
  object MySingleton { // the only instance of the MySingleton type
    val mySpecialValue = 123123
    def mySpecialMethod(): Int = 12390
    def apply(x: Int): Int = x + 1
  }
  MySingleton.mySpecialMethod()
  MySingleton.apply(54)
  MySingleton(34) // equivalent to MySingleton.apply(34)

  object Animal { // companions - companion object has the same name as an existing class or trait
    // companions can access each other's private fields/methods
    // singleton Animal and instances of Animal are different things
    val canLiveIndefinitely = false
  }

  val animalsCanLiveForever = Animal.canLiveIndefinitely // "static" fields/methods

  /**
   * Case classes = lightweight data structures with some boilerplate
   * - sensible equals and hash code
   * - sensible and quick serialization
   * - companion object with apply, so instances can be constructed without the 'new' keyword
   */
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 45) // same as Person.apply("Bob", 45)

  /**
   * Exceptions
   */
  try {
    // code that can throw
    val x: String = null
    x.length
  } catch {
    case e: Exception => "some faulty error message"
  } finally {
    // execute some code no matter what
    // useful for closing connections, files or cleaning up other ways
  }

  /**
   * Generics
   * - Works for class and traits
   */
  abstract class MyList[T] {
    def head: T
    def tail: MyList[T]
  }

  val aList: List[Int] = List(1, 2, 3) // List.apply(1, 2, 3)
  val first = aList.head // Int
  val rest = aList.tail
  val aStringList = List("hello", "Scala")
  val firstString = aStringList.head // String

  /**
   * Important point #1: In Scala we usually operate with IMMUTABLE values/objects
   * Any modification to an object must return ANOTHER object
   * Benefits:
   * 1) works miracles in multithreaded/distributed env
   * 2) helps making sense of the code ("reasoning about")
   */
  val reversedList = aList.reverse // returns a NEW list

  /**
   * Important point #2: Scala is the closest to the OO ideal
   */
}
