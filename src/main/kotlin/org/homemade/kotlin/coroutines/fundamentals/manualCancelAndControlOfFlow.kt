package org.homemade.kotlin.coroutines.fundamentals

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch {
        repeat(1000) {
            if (!isActive) return@launch
            // if (!isActive) throw CancellationException()
            print(".")
            Thread.sleep(10)
        }
    }

    delay(100)
    job.cancelAndJoin()
    println("done")
}