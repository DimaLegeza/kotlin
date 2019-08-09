package org.homemade.kotlin.coroutines.returnData

import kotlinx.coroutines.*
import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val job = launch {
        val timeElapsed = measureTimeMillis {
            val first: Deferred<Int> = async { doWorkOne() }
            val second: Deferred<Int> = async { doWorkTwo() }
            println("Got result of ${first.await() + second.await()}")
        }
        println("Done in $timeElapsed")
    }
    job.join()
}

suspend fun doWorkOne(): Int {
    delay(100)
    println("Working 1")
    return Random(System.currentTimeMillis()).nextInt(42)
}

suspend fun doWorkTwo(): Int {
    delay(200)
    println("Working 2")
    return Random(System.currentTimeMillis()).nextInt(42)
}