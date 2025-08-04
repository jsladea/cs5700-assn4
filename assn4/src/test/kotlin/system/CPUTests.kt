package system

import org.example.system.CPU
import org.example.system.Display
import org.example.system.Keyboard
import org.example.system.RAM
import org.example.system.ROM
import org.example.system.Timer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class CPUTest {
    private lateinit var ram: RAM
    private lateinit var rom: ROM
    private lateinit var display: Display
    private lateinit var keyboard: Keyboard
    private lateinit var timer: Timer
    private lateinit var cpu: CPU

    @BeforeEach
    fun setup() {
        ram = RAM(16)
        rom = ROM(16)
        display = mock()
        keyboard = mock()
        timer = mock()
        whenever(timer.t).thenReturn(0)
        cpu = CPU(ram, rom, display, keyboard, timer)
    }

    @Test
    fun `reset clears registers, pc, address, memory flag, halted, timer, and cycleCount`() {
        cpu.setReg(0, 0x7F)
        cpu.setProgramCounter(5)
        cpu.setAddress(3)
        cpu.setMemoryFlag(true)
        cpu.halt()
        cpu.setTimerValue(0x12)
        cpu.reset()
        assertEquals(0, cpu.reg(0))
        assertEquals(0, cpu.getProgramCounter())
        assertEquals(0, cpu.getAddress())
        assertFalse(cpu.getMemoryFlag())
        assertFalse(cpu.halted)
        verify(timer).reset()
    }

    @Test
    fun `set and get register values`() {
        cpu.setReg(2, 0x55)
        assertEquals(0x55.toByte(), cpu.reg(2))
    }

    @Test
    fun `set and get program counter`() {
        cpu.setProgramCounter(10)
        assertEquals(10, cpu.getProgramCounter())
    }

    @Test
    fun `set and get address`() {
        cpu.setAddress(7)
        assertEquals(7, cpu.getAddress())
    }

    @Test
    fun `set and get memory flag`() {
        cpu.setMemoryFlag(true)
        assertTrue(cpu.getMemoryFlag())
        cpu.setMemoryFlag(false)
        assertFalse(cpu.getMemoryFlag())
    }

    @Test
    fun `set and get timer value`() {
        cpu.setTimerValue(0x42)
        verify(timer).t = 0x42
        whenever(timer.t).thenReturn(0x42)
        assertEquals(0x42, cpu.getTimerValue())
    }

    @Test
    fun `readMemory reads from RAM when memory flag is false`() {
        ram[3] = 0x12
        cpu.setMemoryFlag(false)
        assertEquals(0x12, cpu.readMemory(3))
    }

    @Test
    fun `readMemory reads from ROM when memory flag is true`() {
        rom.load(byteArrayOf(0x01, 0x02, 0x03, 0x04))
        cpu.setMemoryFlag(true)
        assertEquals(0x03, cpu.readMemory(2))
    }

    @Test
    fun `writeMemory writes to RAM when memory flag is false`() {
        cpu.setMemoryFlag(false)
        cpu.writeMemory(5, 0x7A)
        assertEquals(0x7A, ram[5])
    }

    @Test
    fun `writeMemory throws when memory flag is true (ROM)`() {
        cpu.setMemoryFlag(true)
        val ex = assertThrows(IllegalStateException::class.java) {
            cpu.writeMemory(1, 0x22)
        }
        assertTrue(ex.message!!.contains("Write to ROM not allowed"))
    }

    @Test
    fun `draw calls display draw`() {
        cpu.draw(0x41, 2, 3)
        verify(display).draw(0x41, 2, 3)
    }

    @Test
    fun `readKeyboard calls keyboard readByte`() {
        whenever(keyboard.readByte()).thenReturn(0x5A)
        assertEquals(0x5A, cpu.readKeyboard())
        verify(keyboard).readByte()
    }

    @Test
    fun `halt sets halted flag`() {
        assertFalse(cpu.halted)
        cpu.halt()
        assertTrue(cpu.halted)
    }

    @Test
    fun `executeCycle fetches, decodes, executes, and ticks timer every 8 cycles`() {
        // Prepare ROM with a StoreInstruction (op1=1, op2=2)
        val testRom = ByteArray(16) { 0 }
        testRom[0] = 0x01 // op1 (Store r1, 2)
        testRom[1] = 0x02 // op2
        rom.load(testRom)
        cpu.reset()
        repeat(7) { cpu.executeCycle() }
        verify(timer, never()).tick()
        cpu.executeCycle()
        verify(timer).tick()
        // Optionally, check that register 1 was set
        assertEquals(0x02.toByte(), cpu.reg(1))
    }

    @Test
    fun `executeCycle halts and stops executing when halted`() {
        val testRom = ByteArray(16) { 0 }
        testRom[0] = 0x00
        testRom[1] = 0x00
        rom.load(testRom)
        cpu.reset()
        // StoreInstruction with op1=0, op2=0 triggers halt
        cpu.executeCycle()
        assertTrue(cpu.halted)
    }
}