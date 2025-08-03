package org.example

object ProgramLoader {
    fun loadROMFile(path: String): ByteArray {
        return java.io.File(path).readBytes()
    }
}
