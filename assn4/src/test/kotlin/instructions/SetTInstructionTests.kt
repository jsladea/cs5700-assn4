package instructions

import org.example.instructions.SetTInstruction
import org.example.system.CPU
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class SetTInstructionTest {
    @Test
    fun `sets timer value`() {
        val cpu = mock<CPU>()
        SetTInstruction().execute(cpu, 0x01, 0x23)
        verify(cpu).setTimerValue(0x12.toByte())
    }
}