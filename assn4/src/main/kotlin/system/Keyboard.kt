package org.example.system

class Keyboard {
    fun readByte(): Byte {
        print("Input (0-F): ")
        val input = readlnOrNull()?.trim()?.take(2)?.uppercase() ?: ""
        return input.toIntOrNull(16)?.toByte() ?: 0
    }
}