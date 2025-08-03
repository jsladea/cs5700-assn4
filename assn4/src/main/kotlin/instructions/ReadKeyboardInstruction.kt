package org.example.instructions

class ReadKeyboardInstruction : Instruction() {
    override fun execute(cpu: CPU, op1: Byte, op2: Byte) {
        val rX = op1.toInt() and 0xF
        val value = cpu.readKeyboard()
        cpu.setReg(rX, value)
        cpu.setProgramCounter(cpu.getProgramCounter() + 2)
    }
}
