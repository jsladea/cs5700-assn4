package instructions

import org.example.instructions.ReadKeyboardInstruction
import org.example.system.CPU
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class ReadKeyboardInstructionTests {
    @Test
    fun `reads keyboard and stores in register`() {
        val cpu = mock<CPU> { on { readKeyboard() } doReturn 0x7F }
        ReadKeyboardInstruction().execute(cpu, 0x01, 0x00)
        verify(cpu).setReg(1, 0x7F)
    }
}