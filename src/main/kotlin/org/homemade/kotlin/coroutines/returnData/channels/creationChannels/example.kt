package org.homemade.kotlin.coroutines.returnData.channels.creationChannels

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun creationChannels() = runBlocking {
    val channel = Channel<Int>()

    val job = launch {
        for (x in 1..5) {
            println("send $x")
            channel.send(x)
        }
    }

    println("received: ${channel.receive()}")

    repeat(4) {
        println("received: ${channel.receive()}")
    }
    job.join()

}

fun main() {
    creationChannels()
}