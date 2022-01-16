package uk.co.odinconsultants.refinedpipes.data

import eu.timepit.refined.auto._
import uk.co.odinconsultants.refinedpipes.data.ExampleDataTypes._

object RefinedPipeExample {

  def toBinaryInt(age: Age): BinaryInt =
    if (age < 40) 1 else 0

  val One: BinaryInt = 1
  val Zero: BinaryInt = 0

  def doModel(x: BinaryInt): BinaryInt = if (x == One) Zero else One

  def main(args: Array[String]): Unit = {
    println(s"Old? ${toBinaryInt(39)}")
//    println(s"Old? ${toBinaryInt(toBinaryInt(One))}") // doesn't compile
    println(s"pipeline: ${doModel(toBinaryInt(39))}")
//    println(s"does not compile: ${doModel(39)}")
  }

}
