package system

import org.example.system.RAM
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RAMTests {
    @Test
    fun `get returns default zero for unset address`() {
        val ram = RAM(16)
        assertEquals(0.toByte(), ram[0])
        assertEquals(0.toByte(), ram[15])
    }

    @Test
    fun `set and get work for all valid addresses`() {
        val ram = RAM(8)
        for (i in 0 until 8) {
            ram[i] = (i * 2).toByte()
            assertEquals((i * 2).toByte(), ram[i])
        }
    }

    @Test
    fun `size returns correct value`() {
        val ram = RAM(42)
        assertEquals(42, ram.size())
    }

    @Test
    fun `set and get do not affect other addresses`() {
        val ram = RAM(4)
        ram[2] = 0x7F
        assertEquals(0x7F.toByte(), ram[2])
        assertEquals(0.toByte(), ram[0])
        assertEquals(0.toByte(), ram[1])
        assertEquals(0.toByte(), ram[3])
    }

    @Test
    fun `set and get with negative or out of bounds index throws exception`() {
        val ram = RAM(2)
        assertThrows(ArrayIndexOutOfBoundsException::class.java) { ram[-1] = 1 }
        assertThrows(ArrayIndexOutOfBoundsException::class.java) { ram[2] = 1 }
        assertThrows(ArrayIndexOutOfBoundsException::class.java) { ram[-1] }
        assertThrows(ArrayIndexOutOfBoundsException::class.java) { ram[2] }
    }
}