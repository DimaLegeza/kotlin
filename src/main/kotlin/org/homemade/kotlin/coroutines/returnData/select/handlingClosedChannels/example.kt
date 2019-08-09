package org.homemade.kotlin.coroutines.returnData.select.handlingClosedChannels

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

fun producer1() = GlobalScope.produce {
    send("from producer1")
}

fun producer2() = GlobalScope.produce {
    send("from producer2")
}

suspend fun selector(message1: ReceiveChannel<String>, message2: ReceiveChannel<String>): String =
    select {
        message1.onReceiveOrNull {
            it ?: "Channel 1 is closed"
        }
        message2.onReceiveOrNull {
            it ?: "Channel 2 is closed"
        }
    }

fun main() = runBlocking {
    val m1 = producer1()
    val m2 = producer2()

    repeat(15) {
        println(selector(m1, m2))
    }
}