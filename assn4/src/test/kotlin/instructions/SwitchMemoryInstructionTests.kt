package instructions

import org.example.instructions.SwitchMemoryInstruction
import org.example.system.CPU
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class SwitchMemoryInstructionTest {
    @Test
    fun `toggles memory flag from false to true`() {
        val cpu = mock<CPU> {
            on { getMemoryFlag() } doReturn false
        }
        SwitchMemoryInstruction().execute(cpu, 0x01, 0x01)
        verify(cpu).setMemoryFlag(true)
    }

    @Test
    fun `toggles memory flag from true to false`() {
        val cpu = mock<CPU> {
            on { getMemoryFlag() } doReturn true
        }
        SwitchMemoryInstruction().execute(cpu, 0x01, 0x00)
        verify(cpu).setMemoryFlag(false)
    }
}