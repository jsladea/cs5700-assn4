package instructions

import org.example.instructions.ConvertByteToAsciiInstruction
import org.example.system.CPU
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*

class ConvertByteToAsciiInstructionTests {
    @Test
    fun `converts 0-9 to ascii`() {
        val cpu = mock<CPU> { on { reg(1) } doReturn 5 }
        ConvertByteToAsciiInstruction().execute(cpu, 0x01, 0x20)
        verify(cpu).setReg(2, '5'.code.toByte())
    }

    @Test
    fun `converts 10-15 to ascii`() {
        val cpu = mock<CPU> { on { reg(1) } doReturn 0x0B }
        ConvertByteToAsciiInstruction().execute(cpu, 0x01, 0x20)
        verify(cpu).setReg(2, 'B'.code.toByte())
    }

    @Test
    fun `throws error if value is not hex digit`() {
        val cpu = mock<CPU> { on { reg(1) } doReturn 0x10 }
        assertThrows<IllegalStateException> {
            ConvertByteToAsciiInstruction().execute(cpu, 0x01, 0x20)
        }
    }
}