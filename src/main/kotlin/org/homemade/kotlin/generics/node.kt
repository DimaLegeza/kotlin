package org.homemade.kotlin.generics

class Node<T : Number>(private val item: T) {
    fun value(): T {
        return item
    }
}

fun main() {
    val demoNode = Node(10)
    println(demoNode)
//    val failedNode = Node("String")
}