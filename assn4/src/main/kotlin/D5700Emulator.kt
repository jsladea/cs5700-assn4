package org.example

import org.example.system.CPU
import org.example.system.Display
import org.example.system.Keyboard
import org.example.system.RAM
import org.example.system.ROM
import org.example.system.Timer
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.Future

object D5700Emulator {
    private val ram = RAM(4096)
    private val rom = ROM(4096)
    private val display = Display()
    private val keyboard = Keyboard()
    private val timer = Timer()
    private val cpu = CPU(ram, rom, display, keyboard, timer)
    private val executor = Executors.newSingleThreadScheduledExecutor()
    private var cpuFuture: Future<*>? = null

    fun run() {
        println("D5700 Emulator v1.0")
        print("Enter the path to the program ROM file: ")
        val filePath = readln().trim()
        val programBytes = ProgramLoader.loadROMFile(filePath)
        rom.load(programBytes)

        cpu.reset()
        display.clear()
        timer.reset()

        val cpuRunnable = Runnable {
            if (!cpu.halted) {
                cpu.executeCycle()
                display.render()
            } else {
                cpuFuture?.cancel(true)
                executor.shutdown()
            }
        }

        // Run at 500 Hz (every 2 ms)
        cpuFuture = executor.scheduleAtFixedRate(
            cpuRunnable,
            0,
            2,
            TimeUnit.MILLISECONDS
        )

        // Wait for emulator to finish or be halted
        try {
            cpuFuture?.get() // Blocks until finished/canceled
        } catch (_: Exception) {
            // If cancelled or interrupted
        }
        println("Program halted. Emulator exiting.")
    }
}