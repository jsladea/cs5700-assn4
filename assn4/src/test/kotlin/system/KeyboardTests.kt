package system

import org.example.system.Keyboard
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream

class KeyboardTests {
    private lateinit var keyboard: Keyboard

    @BeforeEach
    fun setup() {
        keyboard = Keyboard()
    }

    private fun setInput(input: String) {
        System.setIn(ByteArrayInputStream(input.toByteArray()))
    }

    @Test
    fun `readByte returns correct value for valid 2-digit hex`() {
        setInput("7F\n")
        val result = keyboard.readByte()
        assertEquals(0x7F.toByte(), result)
    }

    @Test
    fun `readByte returns correct value for valid 1-digit hex`() {
        setInput("A\n")
        val result = keyboard.readByte()
        assertEquals(0x0A.toByte(), result)
    }

    @Test
    fun `readByte returns 0 for invalid hex input`() {
        setInput("ZZ\n")
        val result = keyboard.readByte()
        assertEquals(0x00.toByte(), result)
    }

    @Test
    fun `readByte returns 0 for empty input`() {
        setInput("\n")
        val result = keyboard.readByte()
        assertEquals(0x00.toByte(), result)
    }

    @Test
    fun `readByte returns 0 for input longer than 2 chars but valid first 2 chars`() {
        setInput("1FAB\n")
        val result = keyboard.readByte()
        assertEquals(0x1F.toByte(), result)
    }

    @Test
    fun `readByte returns 0 for input with whitespace`() {
        setInput("  \n")
        val result = keyboard.readByte()
        assertEquals(0x00.toByte(), result)
    }

    @Test
    fun `readByte returns 0 for null input`() {
        // Simulate EOF (no input)
        System.setIn(ByteArrayInputStream(ByteArray(0)))
        val result = keyboard.readByte()
        assertEquals(0x00.toByte(), result)
    }

    @Test
    fun `readByte returns correct value for lowercase hex input`() {
        setInput("1a\n")
        val result = keyboard.readByte()
        assertEquals(0x1A.toByte(), result)
    }

    @Test
    fun `readByte returns correct value for input with leading and trailing whitespace`() {
        setInput(" 0F \n")
        val result = keyboard.readByte()
        assertEquals(0x0F.toByte(), result)
    }

    @Test
    fun `readByte returns correct value for single character input`() {
        setInput("F\n")
        val result = keyboard.readByte()
        assertEquals(0x0F.toByte(), result)
    }

    @Test
    fun `readByte returns 0 for non-hex but valid length input`() {
        setInput("G1\n")
        val result = keyboard.readByte()
        assertEquals(0x00.toByte(), result)
    }
}