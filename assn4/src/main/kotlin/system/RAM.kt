package org.example.system

class RAM(private val size: Int) {
    private val data = ByteArray(size)
    operator fun get(address: Int): Byte = data[address]
    operator fun set(address: Int, value: Byte) {
        data[address] = value
    }
    fun size() = size
}