package org.homemade.kotlin.coroutines.returnData.flows

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
fun produceNumbersPipelining() = flow {
    var x = 1

    while(true) {
       emit(x++)
    }
    println("Done")
}

@ExperimentalCoroutinesApi
fun squareNumbersPipelining(numbers: Flow<Int>) = flow {
    numbers
        .map { it * it }
        .collect {
            emit(it)
        }
}

@ExperimentalCoroutinesApi
fun main() {
    val channel = produceNumbersPipelining()
    val squareChannel = squareNumbersPipelining(channel)

    runBlocking {
        squareChannel.take(10).collect { println(it) }
    }

    println("Main done")
}

