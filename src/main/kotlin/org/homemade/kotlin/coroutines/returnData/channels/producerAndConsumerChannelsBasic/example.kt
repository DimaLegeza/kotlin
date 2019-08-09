package org.homemade.kotlin.coroutines.returnData.channels.producerAndConsumerChannelsBasic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun produceNumbers(): Channel<Int> {
    val channel = Channel<Int>()

    GlobalScope.launch {
        for (x in 1..5) {
            println("send $x")
            channel.send(x)
        }
        channel.close()
    }
    return channel
}

fun main() = runBlocking {
    val channel = produceNumbers()

    for (y in channel) {
        println("received: $y")
    }
}

