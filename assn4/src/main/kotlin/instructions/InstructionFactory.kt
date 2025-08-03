package org.example.instructions

import org.example.system.CPU

object InstructionFactory {
    fun decode(op1: Byte, op2: Byte): Instruction {
        val n = (op1.toInt() shr 4) and 0xF
        return when (n) {
            0x0 -> StoreInstruction()
            0x1 -> AddInstruction()
            0x2 -> SubInstruction()
            0x3 -> ReadInstruction()
            0x4 -> WriteInstruction()
            0x5 -> JumpInstruction()
            0x6 -> ReadKeyboardInstruction()
            0x7 -> SwitchMemoryInstruction()
            0x8 -> SkipEqualInstruction()
            0x9 -> SkipNotEqualInstruction()
            0xA -> SetAInstruction()
            0xB -> SetTInstruction()
            0xC -> ReadTInstruction()
            0xD -> ConvertToBase10Instruction()
            0xE -> ConvertByteToAsciiInstruction()
            0xF -> DrawInstruction()
            else -> object : Instruction() { // Fallback
                override fun execute(cpu: CPU, op1: Byte, op2: Byte) {
                    error("Invalid instruction ${op1.toUByte().toString(16)} ${op2.toUByte().toString(16)}")
                }
            }
        }
    }
}
