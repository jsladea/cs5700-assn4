package org.example.instructions

import org.example.system.CPU

class StoreInstruction : Instruction() {
    override fun execute(cpu: CPU, op1: Byte, op2: Byte) {
        // 0000 = HALT
        if (op1 == 0x00.toByte() && op2 == 0x00.toByte()) {
            cpu.halt()
            return
        }
        val rX = (op1.toInt() and 0xF)
        cpu.setReg(rX, op2)
        //cpu.setProgramCounter(cpu.getProgramCounter() + 2)
    }
}
