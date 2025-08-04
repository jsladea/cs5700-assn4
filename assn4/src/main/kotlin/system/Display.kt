package org.example.system

class Display {
    private val frameBuffer = ByteArray(8 * 8) // 8x8 ASCII characters

    fun draw(char: Byte, row: Int, col: Int) {
        if (row in 0..7 && col in 0..7) {
            frameBuffer[row * 8 + col] = char
        }
        render()
    }
    fun clear() = frameBuffer.fill(' '.code.toByte())
    private fun render() {
        println("\n----- 8x8 ASCII Display -----")
        for (row in 0 until 8) {
            for (col in 0 until 8) {
                val c = frameBuffer[row * 8 + col].toInt().toChar()
                print(if (c.code in 32..126) c else '.')
            }
            println()
        }
        println("-----------------------------")
    }
}