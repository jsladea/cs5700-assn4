package org.example.instructions

import org.example.system.CPU

class ReadInstruction : Instruction() {
    override fun execute(cpu: CPU, op1: Byte, op2: Byte) {
        val rX = op1.toInt() and 0xF
        val address = cpu.getAddress()
        val value = cpu.readMemory(address)
        cpu.setReg(rX, value)
        //cpu.setProgramCounter(cpu.getProgramCounter() + 2)
    }
}