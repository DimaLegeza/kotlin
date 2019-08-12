package org.homemade.kotlin.coroutines.actors.calculatePi

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

sealed class PiMessage
class Start(val response: CompletableDeferred<Double>, val workers: Long): PiMessage()
class Work(var channel: Channel<PiMessage>, val start: Long, val end: Long, val worker: Long): PiMessage()
class Result(val result: Double): PiMessage()

fun CoroutineScope.piActor() = actor<Work> {
    var total = 0.0

    for (msg in channel) {
        for (i in msg.start until msg.end) {
            if (i % 50_000_000 == 0L) print(msg.worker)
            total += 4.0 * (1 - (i % 2) * 2) / (2 * i + 1)
        }
        msg.channel.send(Result(total))
    }
}

fun CoroutineScope.workerActor() = actor<PiMessage> {
    lateinit var response: CompletableDeferred<Double>
    var total = 0.0
    var workers: Long = 0
    var finished: Long = 0

    val iterations = 2_000_000_000

    for (msg in channel) {
        when(msg) {
            is Start -> {
                response = msg.response
                workers = msg.workers
                val range: Long = iterations / workers
                for (i in (0 until workers)) {
                    val start = i * range
                    val end = ((i+1) * range) - 1
                    log("Range $start to $end")
                    piActor().send(Work(channel, start, end, i))
                }
            }
            is Result -> {
                finished++
                total += msg.result
                if (finished == workers) {
                    response.complete(total)
                }
            }
        }
    }
}

fun main() = runBlocking {
    val response = CompletableDeferred<Double>()

    val time = measureTimeMillis {
        workerActor().send(Start(response, 4))
        val result = response.await()
        println()
        print(result)
    }
    println(" in $time ms")
}


fun log(str: String) = println(str)