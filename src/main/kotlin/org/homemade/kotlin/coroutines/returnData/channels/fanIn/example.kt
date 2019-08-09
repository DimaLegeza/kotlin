package org.homemade.kotlin.coroutines.returnData.channels.fanIn

import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun sendString(channel: Channel<String>, s: String, interval: Long) {
    while(true) {
        delay(interval)
        channel.send(s)
    }
}

fun main() = runBlocking {
    val channel = Channel<String>()

    launch {
        sendString(channel, "foo", 200L)
    }

    launch {
        sendString(channel, "bar", 500L)
    }

    repeat(6) {
        println(channel.receive())
    }

    coroutineContext.cancelChildren()
}