package uk.co.odinconsultants.refinedpipes.data

import uk.co.odinconsultants.refinedpipes.data.ExampleDataTypes._

object PipesExample {

  val RawPatients = List(0, 1, 2, 42, 120, 999).map(makePatient)

  def categorizePatients[T[_]](rawPatients: T[Patient[Int]],
                               pipeline:    PipelineFunctions[T]): T[Patient[BinaryInt]] =
    pipeline.categorize(pipeline.filter(rawPatients))

  def main(args: Array[String]): Unit = {
    val pipeline = new InMemoryPipeline()
    val results  = categorizePatients(RawPatients, pipeline)
    println(results.mkString(", "))
  }
}
