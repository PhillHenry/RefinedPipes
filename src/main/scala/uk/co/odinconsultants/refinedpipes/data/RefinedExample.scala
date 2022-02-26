package uk.co.odinconsultants.refinedpipes.data

import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import eu.timepit.refined.boolean._
import eu.timepit.refined.numeric._

object RefinedExample {

  type NonZeroDouble = Double Refined (Greater[0d] Or Less[0d])

  def inverse_refined(x: NonZeroDouble): Double = 1 / x

  def main(args: Array[String]): Unit = {
    println(inverse_refined(2.0))
//    println(inverse_refined(0.0)) // compilation error
  }

}
