package instructions

import org.example.instructions.ReadTInstruction
import org.example.system.CPU
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class ReadTInstructionTests {
    @Test
    fun `reads timer value into register`() {
        val cpu = mock<CPU> { on { getTimerValue() } doReturn 42 }
        ReadTInstruction().execute(cpu, 0x01, 0x00)
        verify(cpu).setReg(1, 42)
    }
}