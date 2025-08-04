package org.example.instructions

import org.example.system.CPU

class SetTInstruction : Instruction() {
    override fun execute(cpu: CPU, op1: Byte, op2: Byte) {
        // bb = (lower 4 bits of op1 << 4) | (upper 4 bits of op2)
        val bb = ((op1.toInt() and 0xF) shl 4) or ((op2.toInt() shr 4) and 0xF)
        cpu.setTimerValue(bb.toByte())
    }
}
