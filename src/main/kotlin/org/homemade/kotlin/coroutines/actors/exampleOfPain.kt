package org.homemade.kotlin.coroutines.actors

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext
import kotlin.system.measureTimeMillis

open class Counter {
    private var counter = 0

    open suspend fun increment() {
        counter++
    }

    open var value: Int
        get() = counter
        set(value) {
            counter = value
        }

    suspend fun run(context: CoroutineContext, numberOfJobs: Int, count: Int, action: suspend () -> Unit): Long {
        return measureTimeMillis {
            val jobs = List(numberOfJobs) {
                GlobalScope.launch(context) {
                    repeat(count) {
                        action()
                    }
                }
            }
            jobs.forEach { it.join() }
        }
    }
}

fun main() = runBlocking {
    val jobs = 1000
    val count = 1000

    val counter = Counter()

    counter.run(Dispatchers.Default, jobs, count) { }

    counter.value = 0

    val time = counter.run(Dispatchers.Default, jobs, count) {
        counter.increment()
    }

    println("Base counter completed ${jobs * count} actions in $time ms")
    println("Counter: ${counter.value}")
}