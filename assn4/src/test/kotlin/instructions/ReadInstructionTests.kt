package instructions

import org.example.instructions.ReadInstruction
import org.example.system.CPU
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class ReadInstructionTests {
    @Test
    fun `reads memory at address and stores in register`() {
        val cpu = mock<CPU> {
            on { getAddress() } doReturn 42
            on { readMemory(42) } doReturn 99
        }
        ReadInstruction().execute(cpu, 0x01, 0x00)
        verify(cpu).setReg(1, 99)
    }
}