package instructions

import org.example.instructions.ConvertToBase10Instruction
import org.example.system.CPU
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class ConvertToBase10InstructionTests {
    @Test
    fun `splits value into hundreds tens ones and writes to memory`() {
        val cpu = mock<CPU> {
            on { reg(1) } doReturn 123.toByte()
            on { getAddress() } doReturn 100
        }
        ConvertToBase10Instruction().execute(cpu, 0x01, 0x00)
        verify(cpu).writeMemory(100, 1)
        verify(cpu).writeMemory(101, 2)
        verify(cpu).writeMemory(102, 3)
    }
}