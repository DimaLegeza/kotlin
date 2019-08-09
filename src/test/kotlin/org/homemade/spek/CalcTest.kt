package org.homemade.spek

import org.homemade.kotlin.utils.Calc
import org.junit.Assert.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class CalcTest : Spek({
    describe("the calculator") {
        lateinit var calculator: Calc

        beforeEach {
            calculator = Calc()
        }

        it("should work always") {
            assertEquals(26, 24 + 2)
        }

        it("should add two numbers") {
            assertEquals(26, calculator.add(24, 2))
        }
    }
})