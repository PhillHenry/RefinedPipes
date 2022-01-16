package uk.co.odinconsultants.refinedpipes.data

import org.scalatest.{Matchers, WordSpec}
import uk.co.odinconsultants.refinedpipes.data.PipesExample._

class ExampleSpec extends WordSpec with Matchers {

  s"Patients ${RawPatients}" should {
    val pipeline = new InMemoryPipeline()
//    s"have ages ${MinAge} <= x <= ${MaxAge} " in {
    s"have ages 0 <= x <= 120 " in {
      val cleaned = pipeline.filter(RawPatients)
      assert(cleaned.length == RawPatients.length - 1)
    }
  }

}
