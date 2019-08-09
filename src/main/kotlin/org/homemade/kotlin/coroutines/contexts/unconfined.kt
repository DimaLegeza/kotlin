package org.homemade.kotlin.coroutines.contexts

import kotlinx.coroutines.*

fun main() = runBlocking {
    val jobs = arrayListOf<Job>()

    jobs += launch(Dispatchers.Unconfined) {
        println("coroutineContext: I'm working in thread ${Thread.currentThread().name}")
        delay(100)
        println("coroutineContext: After delay in thread ${Thread.currentThread().name}")
    }
    jobs.forEach { it.join() }
}