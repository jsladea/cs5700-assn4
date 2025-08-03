package org.example.instructions

import org.example.system.CPU

class JumpInstruction : Instruction() {
    override fun execute(cpu: CPU, op1: Byte, op2: Byte) {
        val aaa = ((op1.toInt() and 0xF) shl 8) or (op2.toInt() and 0xFF)
        if (aaa % 2 != 0) error("Jump to unaligned address $aaa")
        cpu.setProgramCounter(aaa)
    }
}