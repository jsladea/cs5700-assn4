package org.example.instructions

import org.example.system.CPU

class SetTInstruction : Instruction() {
    override fun execute(cpu: CPU, op1: Byte, op2: Byte) {
        val bb = (op2.toInt() shr 4) and 0xFF
        cpu.setTimerValue(bb.toByte())
        cpu.setProgramCounter(cpu.getProgramCounter() + 2)
    }
}