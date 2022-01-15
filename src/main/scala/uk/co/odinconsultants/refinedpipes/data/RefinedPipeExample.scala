package uk.co.odinconsultants.refinedpipes.data

import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import eu.timepit.refined.numeric.Interval.Closed



object RefinedPipeExample {

  type Age = Int Refined Closed[0, 120]
  type BinaryInt = Int Refined Closed[0, 1]

  def toBinaryInt(age: Age): BinaryInt =
    if (age < 40) 1 else 0

  val One: BinaryInt = 1

  def doModel(x: BinaryInt): Boolean = x == One

  def main(args: Array[String]): Unit = {
    println(s"Old? ${toBinaryInt(39)}")
    println(s"pipeline: ${doModel(toBinaryInt(39))}")
//    println(s"does not compile: ${doModel(39)}")
  }

}
