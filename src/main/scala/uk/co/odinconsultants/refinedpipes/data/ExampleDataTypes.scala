package uk.co.odinconsultants.refinedpipes.data

import eu.timepit.refined.api.Refined
import eu.timepit.refined.numeric.Interval.Closed

object ExampleDataTypes {

  type Age = Int Refined Closed[0, 120]
  type BinaryInt = Int Refined Closed[0, 1]

  case class Patient[AGE](age: AGE)

  def makePatient(age: Int = 42): Patient[Int] = Patient(age)
}
