package org.example.instructions

import org.example.system.CPU

class DrawInstruction : Instruction() {
    override fun execute(cpu: CPU, op1: Byte, op2: Byte) {
        val rX = op1.toInt() and 0xF
        val rY = (op2.toInt() shr 4) and 0xF
        val rZ = op2.toInt() and 0xF
        val charByte = cpu.reg(rX)
        val charVal = charByte.toInt() and 0xFF
        if (charVal > 0x7F) error("DRAW: Value in r$rX is not a valid ASCII character (<= 0x7F): $charVal")
        cpu.draw(charByte, rY, rZ) // <-- Use rY and rZ as row and column indices
    }
}
