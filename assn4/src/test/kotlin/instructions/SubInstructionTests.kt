package instructions

import org.example.instructions.SubInstruction
import org.example.system.CPU
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class SubInstructionTests{
    @Test
    fun `subtracts two registers and stores result in rZ`() {
        val cpu = mock<CPU> {
            on { reg(1) } doReturn 20
            on { reg(2) } doReturn 10
        }
        SubInstruction().execute(cpu, 0x01, 0x22)
        verify(cpu).setReg(2, 10.toByte())
    }
}