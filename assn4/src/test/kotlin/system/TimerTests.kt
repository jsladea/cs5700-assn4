package system

import org.example.system.Timer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TimerTests {
    private lateinit var timer: Timer

    @BeforeEach
    fun setup() {
        timer = Timer()
    }

    @Test
    fun `tick does not decrement when t is zero`() {
        timer.t = 0
        timer.tick()
        assertEquals(0, timer.t)
    }

    @Test
    fun `tick decrements t when t is positive`() {
        timer.t = 5
        timer.tick()
        assertEquals(4, timer.t)
    }

    @Test
    fun `tick decrements t from 1 to 0`() {
        timer.t = 1
        timer.tick()
        assertEquals(0, timer.t)
    }

    @Test
    fun `tick with t as negative byte does not decrement`() {
        timer.t = (-1).toByte() // 0xFF, unsigned 255
        timer.tick()
        // Should decrement from 255 to 254
        assertEquals(254.toByte(), timer.t)
    }

    @Test
    fun `reset sets t to zero`() {
        timer.t = 42
        timer.reset()
        assertEquals(0, timer.t)
    }
}