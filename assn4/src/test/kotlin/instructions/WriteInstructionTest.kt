package instructions

import org.example.instructions.WriteInstruction
import org.example.system.CPU
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class WriteInstructionTest {
    @Test
    fun `writes register value to memory`() {
        val cpu = mock<CPU> {
            on { reg(1) } doReturn 42
            on { getAddress() } doReturn 100
        }
        WriteInstruction().execute(cpu, 0x01, 0x00)
        verify(cpu).writeMemory(100, 42)
    }
}