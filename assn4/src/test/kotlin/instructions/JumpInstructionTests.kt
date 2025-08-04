package instructions

import org.example.instructions.JumpInstruction
import org.example.system.CPU
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*

class JumpInstructionTests {
    @Test
    fun `jumps to aligned address`() {
        val cpu = mock<CPU>()
        JumpInstruction().execute(cpu, 0x01, 0x00)
        verify(cpu).setProgramCounter(0x100)
    }

    @Test
    fun `throws error for unaligned address`() {
        val cpu = mock<CPU>()
        assertThrows<IllegalStateException> {
            JumpInstruction().execute(cpu, 0x01, 0x01)
        }
    }
}