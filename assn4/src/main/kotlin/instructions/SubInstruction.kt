package org.example.instructions

import org.example.system.CPU

class SubInstruction : Instruction() {
    override fun execute(cpu: CPU, op1: Byte, op2: Byte) {
        val rX = op1.toInt() and 0xF
        val rY = (op2.toInt() shr 4) and 0xF
        val rZ = op2.toInt() and 0xF
        val result = (cpu.reg(rX) - cpu.reg(rY)).toByte()
        cpu.setReg(rZ, result)
        cpu.setProgramCounter(cpu.getProgramCounter() + 2)
    }
}