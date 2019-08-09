package org.homemade.kotlin.coroutines.fundamentals

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    launch {
        delay(1000)
        println("World!!")
    }

    print("Hello, ")
    doWork()
}


suspend fun doWork() {
    delay(2000)
}
