package org.example.system

class ROM(private val size: Int) {
    private val data = ByteArray(size)
    fun load(bytes: ByteArray) {
        if (bytes.size > size) error("ROM too large")
        bytes.copyInto(data, endIndex = bytes.size)
    }
    operator fun get(address: Int): Byte = data[address]
    fun size() = size
}