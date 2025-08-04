package org.example.instructions

import org.example.system.CPU

class ConvertByteToAsciiInstruction : Instruction() {
    override fun execute(cpu: CPU, op1: Byte, op2: Byte) {
        val rX = op1.toInt() and 0xF
        val rY = (op2.toInt() shr 4) and 0xF
        val value = cpu.reg(rX).toInt() and 0xFF
        if (value > 0xF) error("CONVERT_BYTE_TO_ASCII: Value in r$rX is not a single hex digit (0-F): $value")
        // Converts 0-F to ASCII '0'-'9', 'A'-'F'
        val ascii = if (value < 10) ('0'.code + value) else ('A'.code + value - 10)
        cpu.setReg(rY, ascii.toByte())
    }
}