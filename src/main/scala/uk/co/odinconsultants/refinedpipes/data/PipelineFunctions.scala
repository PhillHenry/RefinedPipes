package uk.co.odinconsultants.refinedpipes.data

import uk.co.odinconsultants.refinedpipes.data.ExampleDataTypes.{Age, BinaryInt, Patient}

trait PipelineFunctions[T[_]] {

  def filter(rawPatients: T[Patient[Int]]): T[Patient[Age]]

  def categorize(cleanPatients: T[Patient[Age]]): T[Patient[BinaryInt]]

}
