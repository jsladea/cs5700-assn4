package instructions

import org.example.instructions.InstructionFactory
import org.example.system.CPU
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InstructionFactoryTests {
    @Test
    fun `returns correct instruction for each opcode`() {
        val opcodes = listOf(
            0x00 to "StoreInstruction",
            0x10 to "AddInstruction",
            0x20 to "SubInstruction",
            0x30 to "ReadInstruction",
            0x40 to "WriteInstruction",
            0x50 to "JumpInstruction",
            0x60 to "ReadKeyboardInstruction",
            0x70 to "SwitchMemoryInstruction",
            0x80 to "SkipEqualInstruction",
            0x90 to "SkipNotEqualInstruction",
            0xA0 to "SetAInstruction",
            0xB0 to "SetTInstruction",
            0xC0 to "ReadTInstruction",
            0xD0 to "ConvertToBase10Instruction",
            0xE0 to "ConvertByteToAsciiInstruction",
            0xF0 to "DrawInstruction"
        )
        for ((op1, className) in opcodes) {
            val inst = InstructionFactory.decode(op1.toByte(), 0)
            assert(inst::class.simpleName == className)
        }
    }

}