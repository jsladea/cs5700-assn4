package org.example.instructions

class ConvertToBase10Instruction : Instruction() {
    override fun execute(cpu: CPU, op1: Byte, op2: Byte) {
        val rX = op1.toInt() and 0xF
        val value = cpu.reg(rX).toInt() and 0xFF
        val hundreds = value / 100
        val tens = (value % 100) / 10
        val ones = value % 10
        val addr = cpu.getAddress()
        cpu.writeMemory(addr, hundreds.toByte())
        cpu.writeMemory(addr + 1, tens.toByte())
        cpu.writeMemory(addr + 2, ones.toByte())
        cpu.setProgramCounter(cpu.getProgramCounter() + 2)
    }
}