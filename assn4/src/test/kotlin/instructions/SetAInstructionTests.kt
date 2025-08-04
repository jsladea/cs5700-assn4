package instructions

import org.example.instructions.SetAInstruction
import org.example.system.CPU
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class SetAInstructionTests {
    @Test
    fun `sets address register`() {
        val cpu = mock<CPU>()
        SetAInstruction().execute(cpu, 0x01, 0x23)
        verify(cpu).setAddress(0x123)
    }
}