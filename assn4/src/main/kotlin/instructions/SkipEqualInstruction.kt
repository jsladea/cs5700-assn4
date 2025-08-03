package org.example.instructions

class SkipEqualInstruction : Instruction() {
    override fun execute(cpu: CPU, op1: Byte, op2: Byte) {
        val rX = op1.toInt() and 0xF
        val rY = (op2.toInt() shr 4) and 0xF
        if (cpu.reg(rX) == cpu.reg(rY)) {
            cpu.setProgramCounter(cpu.getProgramCounter() + 4) // skip next
        } else {
            cpu.setProgramCounter(cpu.getProgramCounter() + 2)
        }
    }
}