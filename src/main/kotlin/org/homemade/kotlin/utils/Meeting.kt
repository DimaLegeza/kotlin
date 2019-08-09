package org.homemade.kotlin.utils

data class Meeting(val title: String) {
    var canClose: Boolean = false

    fun close(): Boolean {
        println("Meeting $title is closed")
        return true
    }
}