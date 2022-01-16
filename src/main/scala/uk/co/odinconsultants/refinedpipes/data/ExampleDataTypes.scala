package uk.co.odinconsultants.refinedpipes.data

import eu.timepit.refined.api.Refined
import eu.timepit.refined.numeric.Interval.Closed

object ExampleDataTypes {

  type MaxAge     = 120
  type MinAge     = 0
  type Age        = Int Refined Closed[MinAge, MaxAge]
  type BinaryInt  = Int Refined Closed[MinAge, 1]

  case class Patient[AGE](age: AGE)

  def makePatient(age: Int = 42): Patient[Int] = Patient(age)
}
