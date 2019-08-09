package org.homemade.kotlin.fundamentals.nullability

fun main() {
    var a: String?
    var b: String

    a = "Value"
    println(a)
    a = null
    println(a)

    b = "Value"
    println(b)
//    following won't work
//    b = null
}