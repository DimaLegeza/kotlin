package org.homemade.kotlin.coroutines.actors.intro

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor
import kotlin.coroutines.CoroutineContext
import kotlin.system.measureTimeMillis

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

sealed class CounterMsg
object InitCounter: CounterMsg()
object IncCounter: CounterMsg()

class GetCounter(val response: CompletableDeferred<Int>): CounterMsg()

@ObsoleteCoroutinesApi
fun counterActor() = GlobalScope.actor<CounterMsg> {
    var counter = 0
    for (msg in channel) {
        when(msg) {
            is InitCounter -> counter = 0
            is IncCounter -> counter++
            is GetCounter -> msg.response.complete(counter)
        }
    }
}

fun main() = runBlocking {
    val jobs = 1000
    val count = 1000

    val counter = counterActor()

    counter.send(InitCounter)

    val time = run(Dispatchers.Default, jobs, count) {
        counter.send(IncCounter)
    }

    val response = CompletableDeferred<Int>()
    counter.send(GetCounter(response))

    log("${response.await()} in $time ms")
}

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")