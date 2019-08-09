package org.homemade.kotlin.coroutines.returnData.select.greedySelect

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

fun producer1() = GlobalScope.produce {
    while(true) {
//        delay(200)
        send("from producer1")
    }
}

fun producer2() = GlobalScope.produce {
    while(true) {
//        delay(300)
        send("from producer2")
    }
}

suspend fun selector(message1: ReceiveChannel<String>, message2: ReceiveChannel<String>) {
    select<Unit> {
        message1.onReceive {
            println(it)
        }
        message2.onReceive {
            println(it)
        }
    }
}

fun main() = runBlocking {
    val m1 = producer1()
    val m2 = producer2()

    repeat(15) {
        selector(m1, m2)
    }
}