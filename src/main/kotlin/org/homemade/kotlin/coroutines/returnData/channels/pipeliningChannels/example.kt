package org.homemade.kotlin.coroutines.returnData.channels.pipeliningChannels

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
fun produceNumbers() = GlobalScope.produce {
    var x = 1

    while(true) {
       send(x++)
    }
    println("Done")
}

@ExperimentalCoroutinesApi
fun squareNumbers(numbers: ReceiveChannel<Int>) = GlobalScope.produce {
    numbers
        .map { it * it }
        .consumeEach {
            send(it)
        }
}

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    val channel = produceNumbers()
    val squareChannel = squareNumbers(channel)

    squareChannel.take(10).consumeEach { println(it) }

    println("Main done")
}

