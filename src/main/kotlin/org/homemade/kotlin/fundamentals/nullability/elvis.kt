package org.homemade.kotlin.fundamentals.nullability

import org.homemade.kotlin.utils.Meeting

fun main() {
    val firstMeeting: Meeting? = null
    val secondMeeting: Meeting? = Meeting("base meeting")

    var newMeeting = firstMeeting ?: Meeting("important meeting")
    println(newMeeting)
    newMeeting = secondMeeting ?: Meeting("second important meeting")
    println(newMeeting)
}