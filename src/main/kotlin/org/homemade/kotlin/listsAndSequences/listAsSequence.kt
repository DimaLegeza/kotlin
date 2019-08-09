package org.homemade.kotlin.listsAndSequences

fun main() {
    val someListOfSomething = listOf(1, 2, 3, 3, 4, 5)

    someListOfSomething
        .asSequence()
        .filter { it < 3 }
        .map { it * it }

}