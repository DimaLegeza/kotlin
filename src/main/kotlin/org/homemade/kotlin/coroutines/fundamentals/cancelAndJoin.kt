package org.homemade.kotlin.coroutines.fundamentals

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = launch {
        repeat(1000) {
            delay(100)
            print(".")
        }
    }

    delay(2500)
    job.cancelAndJoin()
    println("done")
}