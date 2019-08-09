package org.homemade.kotlin.coroutines.returnData.channels.sideChannels

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

fun produceNumbers(side: SendChannel<Int>) = GlobalScope.produce<Int> {
    for (num in 1..10) {
        delay(100)
        select<Unit> {
            onSend(num) {}
            side.onSend(num) {}
        }
    }
    println("Done producing")
}

fun main() = runBlocking {
    val side = Channel<Int>()
    val producer = produceNumbers(side)

    launch {
        side.consumeEach { println("side $it") }
    }

    producer.consumeEach {
        println(it)
        delay(500)
    }
}