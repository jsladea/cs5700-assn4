package system

import org.example.system.ROM
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ROMTests {
    @Test
    fun `get returns default zero for unset address`() {
        val rom = ROM(8)
        assertEquals(0.toByte(), rom[0])
        assertEquals(0.toByte(), rom[7])
    }

    @Test
    fun `load copies bytes into ROM`() {
        val rom = ROM(4)
        val data = byteArrayOf(1, 2, 3, 4)
        rom.load(data)
        for (i in data.indices) {
            assertEquals(data[i], rom[i])
        }
    }

    @Test
    fun `load with fewer bytes leaves rest zero`() {
        val rom = ROM(4)
        val data = byteArrayOf(5, 6)
        rom.load(data)
        assertEquals(5, rom[0])
        assertEquals(6, rom[1])
        assertEquals(0, rom[2])
        assertEquals(0, rom[3])
    }

    @Test
    fun `load with too many bytes throws error`() {
        val rom = ROM(2)
        val data = byteArrayOf(1, 2, 3)
        val ex = assertThrows(IllegalStateException::class.java) { rom.load(data) }
        assertTrue(ex.message?.contains("ROM too large") == true)
    }

    @Test
    fun `size returns correct value`() {
        val rom = ROM(123)
        assertEquals(123, rom.size())
    }

    @Test
    fun `get with negative or out of bounds index throws exception`() {
        val rom = ROM(2)
        assertThrows(ArrayIndexOutOfBoundsException::class.java) { rom[-1] }
        assertThrows(ArrayIndexOutOfBoundsException::class.java) { rom[2] }
    }
}