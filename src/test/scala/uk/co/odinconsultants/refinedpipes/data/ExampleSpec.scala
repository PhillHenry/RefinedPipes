package uk.co.odinconsultants.refinedpipes.data

import org.scalatest.{Matchers, WordSpec}
import uk.co.odinconsultants.refinedpipes.data.PipesExample._

class ExampleSpec extends WordSpec with Matchers {

  "Raw patients" should {
    val pipeline = new InMemoryPipeline()
    "be refined" in {
      val cleaned = pipeline.filter(RawPatients)
      assert(cleaned.length == RawPatients.length - 1)
    }
  }

}
