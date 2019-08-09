package org.homemade.kotlin.fundamentals.nullability

import org.homemade.kotlin.utils.Meeting

fun main() {
    fun functionNonNull(m: Meeting): Boolean? {
        return if (m.canClose) m.close() else false
    }

    val m: Meeting? = null

    val funRes = m?.let {
        functionNonNull(m)
    }
    println(funRes)
}