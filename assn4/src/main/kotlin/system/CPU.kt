package org.example.system

import org.example.instructions.InstructionFactory

class CPU(
    private val ram: RAM,
    private val rom: ROM,
    private val display: Display,
    private val keyboard: Keyboard,
    private val timer: Timer
) {
    // General purpose registers r0..r7
    private val r = ByteArray(8)
    private var p: Int = 0 // Program Counter (16-bit)
    private var a: Int = 0 // Address register (16-bit)
    private var m: Boolean = false // Memory flag (false = RAM, true = ROM)
    private var haltedFlag = false

    val halted: Boolean get() = haltedFlag

    fun reset() {
        r.fill(0)
        p = 0
        a = 0
        m = false
        haltedFlag = false
        timer.reset()
    }

    fun executeCycle() {
        val op1 = fetchByte()
        val op2 = fetchByte()

        val instruction = InstructionFactory.decode(op1, op2)
        instruction.execute(this, op1, op2)
        // Tick timer at 60hz (every 8th cycle at 500hz)
        if (p % 8 == 0) timer.tick()
    }

    private fun fetchByte(): Byte {
        val byte = rom.get(p)
        p = (p + 1) % rom.size()
        return byte
    }

    // --- Accessors for internal state (encapsulation) ---
    fun reg(idx: Int) = r[idx]
    fun setReg(idx: Int, value: Byte) { r[idx] = value }
    fun getProgramCounter() = p
    fun setProgramCounter(value: Int) { p = value }
    fun getAddress() = a
    fun setAddress(value: Int) { a = value }
    fun getMemoryFlag() = m
    fun setMemoryFlag(flag: Boolean) { m = flag }
    fun getTimerValue() = timer.t
    fun setTimerValue(value: Byte) { timer.t = value }
    fun readMemory(address: Int): Byte =
        if (m) rom.get(address) else ram.get(address)
    fun writeMemory(address: Int, value: Byte) {
        if (m) error("Write to ROM not allowed (future chips may support)")
        ram[address] = value
    }
    fun draw(char: Byte, row: Int, col: Int) = display.draw(char, row, col)
    fun readKeyboard(): Byte = keyboard.readByte()
    fun halt() { haltedFlag = true }
}
