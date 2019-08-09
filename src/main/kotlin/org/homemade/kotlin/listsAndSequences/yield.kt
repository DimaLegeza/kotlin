package org.homemade.kotlin.listsAndSequences

fun main() {
    val sequence = sequence {
        val start = 0
        yield(start) // first return
        yieldAll(1..5 step 2) // if called again, will start from here
        yieldAll(generateSequence(8) { it * 3 }) // if called more that six times, start from here
    }

    val iterator = sequence.iterator()

    iterator
        .asSequence()
        .take(10)
        .forEach { println(it) }
}