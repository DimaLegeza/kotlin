package org.homemade.kotlin.coroutines.returnData.channels.fanOut

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun produceNumbers() = GlobalScope.produce {
    var x = 1
    while(true) {
        send(x++)
        delay(100)
    }
}

fun consumer(id: Int, channel: ReceiveChannel<Int>) = GlobalScope.launch {
    channel.consumeEach {
        println("Processor #$id received $it in thread ${Thread.currentThread().name}")
    }
}

fun main() = runBlocking {
    val producer = produceNumbers()
    repeat(5) { consumer(it, producer) }

    println("launched")

    delay(950)
    producer.cancel()
}