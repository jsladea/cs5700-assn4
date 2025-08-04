package org.example.instructions

import org.example.system.CPU

class WriteInstruction : Instruction() {
    override fun execute(cpu: CPU, op1: Byte, op2: Byte) {
        val rX = op1.toInt() and 0xF
        val address = cpu.getAddress()
        val value = cpu.reg(rX)
        try {
            cpu.writeMemory(address, value)
        } catch (e: Exception) {
            error("Write failed: ${e.message}")
        }
    }
}