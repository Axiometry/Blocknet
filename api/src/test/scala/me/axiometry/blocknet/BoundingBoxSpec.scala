package me.axiometry.blocknet

import me.axiometry.blocknet.Location.{Precise => Pos}

class BoundingBoxSpec extends UnitSpec {
  describe("BoundingBox") {
    it("should have the correct min and max coordinates") {
      Given("the bounding box((3, -3, -3), (-3, 3, 3))")
      val bb = BoundingBox(Pos(3, -3, -3), Pos(-3, 3, 3))

      Then("it should have the correct coordinates")
      assert(bb.min.x == -3)
      assert(bb.min.y == -3)
      assert(bb.min.z == -3)
      assert(bb.max.x == 3)
      assert(bb.max.y == 3)
      assert(bb.max.z == 3)

      And("it should be the same as the bounding box ((-3, -3, -3), (3, 3, 3))")
      assert(bb == BoundingBox(Pos(-3, -3, -3), Pos(3, 3, 3)))

      And("it should be the same as the bounding box ((3, 3, 3), (-3, -3, -3))")
      assert(bb == BoundingBox(Pos(3, 3, 3), Pos(-3, -3, -3)))
    }

    it("should handle expansion properly") {
      Given("the bounding box ((-3, -3, -3), (3, 3, 3))")
      val bb = BoundingBox(Pos(-3, -3, -3), Pos(3, 3, 3))

      Then("expanding by (1, 1, 1) should produce ((-4, -4, -4), (4, 4, 4))")
      assert(bb.expand(1, 1, 1) == BoundingBox(Pos(-4, -4, -4), Pos(4, 4, 4)))

      And("expanding by (-1, -1, -1) should produce ((-2, -2, -2), (2, 2, 2))")
      assert(bb.expand(-1, -1, -1) == BoundingBox(Pos(-2, -2, -2), Pos(2, 2, 2)))

      And("expanding by (0, 0, 0) should produce the same bounding box")
      assert(bb.expand(0, 0, 0) == bb)
    }

    it("should handle contraction properly") {
      Given("the bounding box ((-3, -3, -3), (3, 3, 3))")
      val bb = BoundingBox(Pos(-3, -3, -3), Pos(3, 3, 3))

      Then("contracting by (1, 1, 1) should produce ((-2, -2, -2), (2, 2, 2))")
      assert(bb.contract(1, 1, 1) == BoundingBox(Pos(-2, -2, -2), Pos(2, 2, 2)))

      And("contracting by (-1, -1, -1) should produce ((-4, -4, -4), (4, 4, 4))")
      assert(bb.contract(-1, -1, -1) == BoundingBox(Pos(-4, -4, -4), Pos(4, 4, 4)))

      And("contacting by (0, 0, 0) should produce the same bounding box")
      assert(bb.contract(0, 0, 0) == bb)
    }

    it("should handle offsetting properly") {
      Given("the bounding box ((-3, -3, -3), (3, 3, 3))")
      val bb = BoundingBox(Pos(-3, -3, -3), Pos(3, 3, 3))

      Then("offsetting by (1, 1, 1) should produce ((-2, -2, -2), (4, 4, 4))")
      assert(bb.offset(1, 1, 1) == BoundingBox(Pos(-2, -2, -2), Pos(4, 4, 4)))

      And("offsetting by (-1, -1, -1) should produce ((-4, -4, -4), (2, 2, 2))")
      assert(bb.offset(-1, -1, -1) == BoundingBox(Pos(-4, -4, -4), Pos(2, 2, 2)))

      And("offsetting by (0, 0, 0) should produce the same bounding box")
      assert(bb.offset(0, 0, 0) == bb)

      And("offsetting by a position object should be the same")
      assert(bb.offset(1, 1, 1) == bb.offset(Pos(1, 1, 1)))
      assert(bb.offset(-1, -1, -1) == bb.offset(Pos(-1, -1, -1)))
      assert(bb.offset(0, 0, 0) == bb.offset(Pos(0, 0, 0)))
    }

    it("should detect intersection properly") {
      Given("the bounding box ((-3, -3, -3), (3, 3, 3))")
      val bb = BoundingBox(Pos(-3, -3, -3), Pos(3, 3, 3))

      Then("intersection should be detected with ((-1, -1, -1), (1, 1, 1))")
      assert(bb.intersects(BoundingBox(Pos(-1, -1, -1), Pos(1, 1, 1))))

      And("intersection should be detected with ((0, 0, 0), (0, 0, 0))")
      assert(bb.intersects(BoundingBox(Pos(0, 0, 0), Pos(0, 0, 0))))

      And("intersection should be detected with ((1, 1, 1), (1, 1, 1))")
      assert(bb.intersects(BoundingBox(Pos(1, 1, 1), Pos(1, 1, 1))))

      And("intersection should not be detected with ((-3, -3, -6), (3, 3, -3))")
      assert(!bb.intersects(BoundingBox(Pos(-3, -3, -6), Pos(3, 3, -3))))

      And("intersection should not be detected with ((-3, -3, -3), (-3, -3, -3))")
      assert(!bb.intersects(BoundingBox(Pos(-3, -3, -3), Pos(-3, -3, -3))))
    }

    it("should detect containment properly") {
      Given("the bounding box ((-3, -3, -3), (3, 3, 3))")
      val bb = BoundingBox(Pos(-3, -3, -3), Pos(3, 3, 3))

      Then("containment should be detected with ((-1, -1, -1), (1, 1, 1))")
      assert(bb.contains(BoundingBox(Pos(-1, -1, -1), Pos(1, 1, 1))))

      And("containment should be detected with ((0, 0, 0), (0, 0, 0))")
      assert(bb.contains(BoundingBox(Pos(0, 0, 0), Pos(0, 0, 0))))

      And("containment should be detected with ((1, 1, 1), (1, 1, 1))")
      assert(bb.contains(BoundingBox(Pos(1, 1, 1), Pos(1, 1, 1))))

      And("containment should not be detected with ((-3, -3, -6), (3, 3, -3))")
      assert(!bb.contains(BoundingBox(Pos(-3, -3, -6), Pos(3, 3, -3))))

      And("containment should be detected with ((-3, -3, -3), (-3, -3, -3))")
      assert(bb.contains(BoundingBox(Pos(-3, -3, -3), Pos(-3, -3, -3))))
    }
  }
}