package uk.co.odinconsultants.refinedpipes.data

import uk.co.odinconsultants.refinedpipes.data.ExampleDataTypes.{Age, BinaryInt, Patient}

class InMemoryPipeline extends PipelineFunctions[List] {

  import eu.timepit.refined._
  import eu.timepit.refined.auto._

  override def filter(rawPatients: List[Patient[Int]]): List[Patient[Age]] = rawPatients.flatMap { patient =>
    val maybeAge: Either[String, Age] = refineV(patient.age)
    maybeAge match {
      case Right(age) => List(Patient(age))
      case Left(_)    => List()
    }
  }

  override def categorize(cleanPatients: List[Patient[Age]]): List[Patient[BinaryInt]] = cleanPatients.map { patient =>
    val binary: BinaryInt = if (patient.age < 40) 1 else 0
    Patient[BinaryInt](binary)
  }
}
