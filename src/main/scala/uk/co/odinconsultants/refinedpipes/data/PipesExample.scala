package uk.co.odinconsultants.refinedpipes.data

import uk.co.odinconsultants.refinedpipes.data.ExampleDataTypes._

trait PipelineFunctions[T[_]] {

  def filter(rawPatients: T[Patient[Int]]): T[Patient[Age]]

  def categorize(cleanPatients: T[Patient[Age]]): T[Patient[BinaryInt]]

}

class InMemoryPipeline extends PipelineFunctions[List] {

  import eu.timepit.refined._

  override def filter(rawPatients: List[Patient[Int]]): List[Patient[Age]] = rawPatients.flatMap { patient =>
    val maybeAge: Either[String, Age] = refineV(patient.age)
    maybeAge match {
      case Right(age) => List(Patient(age))
      case Left(_) => List()
    }
  }

  override def categorize(cleanPatients: List[Patient[Age]]): List[Patient[BinaryInt]] = ???
}

object PipesExample {

  def categorizePatients[T[_]](rawPatients: T[Patient[Int]],
                               pipeline:    PipelineFunctions[T]): T[Patient[BinaryInt]] = {
    val cleaned = pipeline.filter(rawPatients)
    val categorized = pipeline.categorize(cleaned)
    categorized
  }

  def main(args: Array[String]): Unit = {
    val ages = List(0, 1, 2, 42, 120, 999)
    val rawPatients = ages.map(makePatient)
    val pipeline = new InMemoryPipeline()
    val cleaned = pipeline.filter(rawPatients)
    println(cleaned.mkString(", "))
  }

}
