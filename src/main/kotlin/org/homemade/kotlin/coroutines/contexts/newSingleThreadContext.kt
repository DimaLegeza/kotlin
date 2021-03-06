package org.homemade.kotlin.coroutines.contexts

import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

@ObsoleteCoroutinesApi
fun main() = runBlocking {
    newSingleThreadContext("new bla").use { ctx ->
        val job = launch(ctx) {
            println("i'm working in thread ${Thread.currentThread().name}")
        }
        job.join()
    }
}