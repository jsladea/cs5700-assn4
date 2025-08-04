package instructions

import org.example.instructions.StoreInstruction
import org.example.system.CPU
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class StoreInstructionTests {
    @Test
    fun `stores register value`() {
        val cpu = mock<CPU> {
            on { reg(1) } doReturn 42
            on { getAddress() } doReturn 100
        }
        StoreInstruction().execute(cpu, 0x01, 0x2A)
        verify(cpu).setReg(1, 0x2A.toByte())
    }

    @Test
    fun `halts when both op1 and op2 are zero`() {
        val cpu = mock<CPU>()
        StoreInstruction().execute(cpu, 0x00, 0x00)
        verify(cpu).halt()
        verify(cpu, never()).setReg(any(), any())
    }
}