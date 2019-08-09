package org.homemade.kotlin.coroutines.returnData.channels.closingChannels

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun closingChannels() = runBlocking {
    val channel = Channel<Int>()

    val job = launch {
        for (x in 1..5) {
            println("send $x")
            channel.send(x)
        }
        channel.close()
    }

    for (y in channel) {
        println("received: $y")
    }

    job.join()

}

fun main() {
    closingChannels()
}