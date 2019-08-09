package org.homemade.coroutines

import kotlinx.coroutines.runBlocking
import org.homemade.kotlin.coroutines.fundamentals.doWork
import org.junit.Assert.assertEquals
import org.junit.Test

class BasicSuspendTest {
    @Test
    fun firstTest() {
        assertEquals(2, 1 + 1)
    }

    @Test
    fun secondTest() = runBlocking {
        doWork()
        assertEquals(2, 1 + 1)
    }
}