package org.example.system

class Timer {
    var t: Byte = 0
    fun tick() {
        if (t > 0) t = (t - 1).toByte()
    }
    fun reset() { t = 0 }
}