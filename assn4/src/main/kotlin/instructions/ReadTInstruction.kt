package org.example.instructions

import org.example.system.CPU

class ReadTInstruction : Instruction() {
    override fun execute(cpu: CPU, op1: Byte, op2: Byte) {
        val rX = op1.toInt() and 0xF
        cpu.setReg(rX, cpu.getTimerValue())
        cpu.setProgramCounter(cpu.getProgramCounter() + 2)
    }
}