package org.example.instructions

class SwitchMemoryInstruction : Instruction() {
    override fun execute(cpu: CPU, op1: Byte, op2: Byte) {
        cpu.setMemoryFlag(!cpu.getMemoryFlag())
        cpu.setProgramCounter(cpu.getProgramCounter() + 2)
    }
}