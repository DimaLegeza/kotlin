package org.homemade.kotlin.coroutines.returnData.channels.producerAndConsumerChannelsImproved

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
fun produceNumbers() = GlobalScope.produce {
    for (x in 1..5) {
        println("send $x")
        send(x)
    }
    println("Done")
}

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    val channel = produceNumbers()

    channel.consumeEach { println(it) }

    println("Main done")
}

