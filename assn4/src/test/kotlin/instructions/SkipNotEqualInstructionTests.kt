package instructions

import org.example.instructions.SkipNotEqualInstruction
import org.example.system.CPU
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class SkipNotEqualInstructionTests {
    @Test
    fun `skips if registers not equal`() {
        val cpu = mock<CPU> {
            on { reg(1) } doReturn 5
            on { reg(2) } doReturn 6
            on { getProgramCounter() } doReturn 10
        }
        SkipNotEqualInstruction().execute(cpu, 0x01, 0x20)
        verify(cpu).setProgramCounter(12)
    }

    @Test
    fun `does not skip if registers equal`() {
        val cpu = mock<CPU> {
            on { reg(1) } doReturn 5
            on { reg(2) } doReturn 5
        }
        SkipNotEqualInstruction().execute(cpu, 0x01, 0x20)
        verify(cpu, never()).setProgramCounter(any())
    }
}