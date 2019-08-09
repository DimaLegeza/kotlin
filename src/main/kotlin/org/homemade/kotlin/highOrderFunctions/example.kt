package org.homemade.kotlin.highOrderFunctions

val action = { println("Hello, World") }

fun doSomething(func: () -> Unit) {
    func()
}

fun main(args: Array<String>) {
    doSomething(action)
}