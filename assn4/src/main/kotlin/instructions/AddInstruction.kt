package org.example.instructions

import org.example.system.CPU

class AddInstruction : Instruction() {
    override fun execute(cpu: CPU, op1: Byte, op2: Byte) {
        val rX = op1.toInt() and 0xF
        val rY = (op2.toInt() shr 4) and 0xF
        val rZ = op2.toInt() and 0xF
        val sum = (cpu.reg(rX) + cpu.reg(rY)).toByte()
        cpu.setReg(rZ, sum)
    }
}