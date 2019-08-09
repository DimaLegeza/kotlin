package org.homemade.kotlin.coroutines.returnData.channels

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consume
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

const val numberOfWorkers = 10
const val numberOfTasks = 20

data class Work(var x: Long = 0, var y: Long = 0, var z: Long = 0)

suspend fun worker(input: Channel<Work>, output: Channel<Work>) {
    for (w in input) {
        w.z = w.x * w.y
        delay(w.z)
        output.send(w)
    }
}

suspend fun submitWorkToWorkers(input: Channel<Work>) {
    repeat(numberOfTasks) {
        input.send(Work((0L..100L).random(), (0L..100L).random()))
    }
}

suspend fun receiveResults(channel: Channel<Work>) {
    for (work in channel) {
        println("${work.x}*${work.y} = ${work.z}")
    }
}

fun runIt() {
    val input = Channel<Work>()
    val output = Channel<Work>()

    repeat(numberOfWorkers) {
        GlobalScope.launch { worker(input, output) }
    }

    GlobalScope.launch { submitWorkToWorkers(input) }
    GlobalScope.launch { receiveResults(output) }
}

fun main() {
    runIt()

    runBlocking {
        delay(5000)
        println("Done")
    }
}




