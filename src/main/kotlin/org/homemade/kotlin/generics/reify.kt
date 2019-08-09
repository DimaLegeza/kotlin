package org.homemade.kotlin.generics

inline fun <reified T> foo(value: Any) = value is T

fun main() {
    val demo = 15
    val typeCheck = foo<Int>(demo)
    val typeCheck2 = foo<String>(demo)
    println(typeCheck)
    println(typeCheck2)
}
