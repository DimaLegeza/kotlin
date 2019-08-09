package org.homemade.kotlin.coroutines.fundamentals

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch {
        try {
            repeat(1000) {
                yield()
                print(".")
                Thread.sleep(10)
            }
        } catch (ex: CancellationException) {
            println()
            println("Cancelled: ${ex.message}")
        } finally {
            withContext(NonCancellable) {
                println()
                println("In finally")
            }
        }
    }

    delay(100)
    job.cancel(CancellationException("Too many jobs"))
    job.join()
}
