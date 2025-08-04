package org.example.system

class Timer {
    var t: Byte = 0

    fun tick() {

        val unsignedValue = t.toInt() and 0xFF
        if (unsignedValue > 0)
            t = (unsignedValue - 1).toByte()
    }
    fun reset() { t = 0 }
}