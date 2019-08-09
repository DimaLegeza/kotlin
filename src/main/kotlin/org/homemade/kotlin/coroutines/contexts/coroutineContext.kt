package org.homemade.kotlin.coroutines.contexts

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val jobs = arrayListOf<Job>()

    jobs += launch(coroutineContext) {
        println("coroutineContext: I'm working in thread ${Thread.currentThread().name}")
        delay(100)
        println("coroutineContext: After delay in thread ${Thread.currentThread().name}")
    }
    jobs.forEach { it.join() }
}