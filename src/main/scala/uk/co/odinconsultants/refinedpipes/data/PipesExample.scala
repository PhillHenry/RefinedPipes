package uk.co.odinconsultants.refinedpipes.data

import uk.co.odinconsultants.refinedpipes.data.ExampleDataTypes._

trait PipelineFunctions[T[_]] {

  def filter(rawPatients: T[Patient[Int]]): T[Patient[Age]]

  def categorize(cleanPatients: T[Patient[Age]]): T[Patient[BinaryInt]]

}

class InMemoryPipeline extends PipelineFunctions[List] {

  import eu.timepit.refined._
  import eu.timepit.refined.auto._

  override def filter(rawPatients: List[Patient[Int]]): List[Patient[Age]] = rawPatients.flatMap { patient =>
    val maybeAge: Either[String, Age] = refineV(patient.age)
    maybeAge match {
      case Right(age) => List(Patient(age))
      case Left(_) => List()
    }
  }

  override def categorize(cleanPatients: List[Patient[Age]]): List[Patient[BinaryInt]] = cleanPatients.map { patient =>
    val binary: BinaryInt = if (patient.age < 40) 1 else 0
    Patient[BinaryInt](binary)
  }
}

object PipesExample {

  def categorizePatients[T[_]](rawPatients: T[Patient[Int]],
                               pipeline:    PipelineFunctions[T]): T[Patient[BinaryInt]] = {
    val cleaned     = pipeline.filter(rawPatients)
    val categorized = pipeline.categorize(cleaned)
    categorized
  }

  val RawPatients = List(0, 1, 2, 42, 120, 999).map(makePatient)

  def main(args: Array[String]): Unit = {
    val pipeline = new InMemoryPipeline()
    val results  = categorizePatients(RawPatients, pipeline)
    println(results.mkString(", "))
  }
}
