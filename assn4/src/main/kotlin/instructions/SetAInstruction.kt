package org.example.instructions

import org.example.system.CPU

class SetAInstruction : Instruction() {
    override fun execute(cpu: CPU, op1: Byte, op2: Byte) {
        val aaa = ((op1.toInt() and 0xF) shl 8) or (op2.toInt() and 0xFF)
        cpu.setAddress(aaa)
        cpu.setProgramCounter(cpu.getProgramCounter() + 2)
    }
}