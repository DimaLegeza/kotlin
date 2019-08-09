package org.homemade.mockk

import io.mockk.every
import io.mockk.mockk
import org.homemade.kotlin.utils.Calc
import org.junit.Assert.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class CalcMockkTest : Spek({

    describe("calc test wioth mocks") {
        lateinit var calc: Calc

        beforeEach {
            calc = mockk(relaxed = true)
            every { calc.add(any(), any()) } returns 42
        }

        it("should return 42") {
            assertEquals(42, calc.add(1, 2))
        }
    }

})