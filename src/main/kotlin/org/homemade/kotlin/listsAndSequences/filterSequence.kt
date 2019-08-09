package org.homemade.kotlin.listsAndSequences

import org.homemade.kotlin.utils.Meeting

fun main() {
    val meetings = listOf(Meeting("First meeting"), Meeting("Second meeting"), Meeting("Third"))

    meetings
        .asSequence()
        .filter { it.title.endsWith("g") }
        .map { it.title }
        .forEach { println(it) }
}