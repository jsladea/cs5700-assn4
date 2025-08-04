package instructions

import org.example.instructions.AddInstruction
import org.example.system.CPU
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class AddInstructionTests {
    @Test
    fun `adds two registers and stores result in rZ`() {
        val cpu = mock<CPU> {
            on { reg(1) } doReturn 10
            on { reg(2) } doReturn 20
        }
        AddInstruction().execute(cpu, 0x01, 0x22)
        verify(cpu).setReg(2, 30.toByte())
    }
}