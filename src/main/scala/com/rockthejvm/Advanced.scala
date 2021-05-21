package com.rockthejvm

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}

object Advanced extends App {
  // lazy evaluation
  lazy val aLazyValue = 2
  val lazyValueWithSideEffect = {
    println("I am so lazy")
    43
  }

  // useful in infinite collections and some more rare use cases

  // "pseudo-collections": Option, Try
  def methodWhichCanReturnNull(): String = "hello, Scala"
  //if (methodWhichCanReturnNull() == null) {
  //  // defensive code against null
  //}
  val anOption = Option(methodWhichCanReturnNull()) // Some("hello, Scala")
  // option = "collection" which contains at most one element: Some(value) or None

  val stringProcessing = anOption match {
    case Some(string) => s"I have a valid string: $string"
    case None => "I have nothing"
  }

  def methodWhichCanThrowException(): String = throw new RuntimeException
  //  try {
  //    methodWhichCanThrowException()
  //  } catch {
  //    case e: Exception => "defend against exception"
  //  }
  val aTry = Try(methodWhichCanThrowException())
  // a try = "collection" with either a value if the code went well, or an exception if the code threw one

  val anotherStringProcessing = aTry match {
    case Success(value) => s"I have obtained a valid string: $value"
    case Failure(exception) => s"I have obtained an exception: $exception"
  }

  /**
   * Evaluate something on another thread
   * (async programming)
   */
  val aFuture = Future({
    println("Loading...")
    Thread.sleep(1000)
    print("I have computed a value")
    67
  })
  // future is a "collection" which contains a value when it's evaluated
  // future is composable with map, flatMap and filter...
  // monads

  /**
   * Implicits basics
   */
  // #1: implicit arguments
  def aMethodWithImplicitArgs(implicit arg: Int) = arg + 1
  implicit val myImplicitInt = 46
  println(aMethodWithImplicitArgs) // aMethodWithImplicitArgs(myImplicitInt)

  // #2 implicit conversions
  implicit class MyRichInteger(n: Int) {
    def isEven() = n % 2 == 0
  }
  println(23.isEven()) // new MyRichInteger(23).isEven()

  /**
   * Implicits are incredibly powerful, but can also be very dangerous. USE CAREFULLY
   */
}
