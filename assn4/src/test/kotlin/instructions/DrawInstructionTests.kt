package instructions

import org.example.instructions.DrawInstruction
import org.example.system.CPU
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*

class DrawInstructionTests {
    @Test
    fun `draws ascii character if valid`() {
        val cpu = mock<CPU> { on { reg(1) } doReturn 65 }
        DrawInstruction().execute(cpu, 0x01, 0x23)
        verify(cpu).draw(65, 2, 3)
    }

    @Test
    fun `throws error if value is not valid ascii`() {
        val cpu = mock<CPU> { on { reg(1) } doReturn 0x80.toByte() }
        assertThrows<IllegalStateException> {
            DrawInstruction().execute(cpu, 0x01, 0x23)
        }
    }
}