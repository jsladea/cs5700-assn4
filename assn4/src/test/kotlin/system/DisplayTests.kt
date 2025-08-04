package system

import org.example.system.Display
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class DisplayTests {
    private lateinit var display: Display
    private lateinit var outContent: ByteArrayOutputStream

    @BeforeEach
    fun setup() {
        display = Display()
        outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))
    }

    // Helper to extract only the 8x8 grid from the output
    private fun extractGrid(output: String): List<String> {
        val lines = output.lines()
        val start = lines.indexOfFirst { it.contains("8x8 ASCII Display") } + 1
        // The grid is the next 8 lines after the header
        return lines.drop(start).take(8)
    }

    @Test
    fun `draw inside bounds updates framebuffer and calls render`() {
        display.draw('A'.code.toByte(), 2, 3)
        val grid = extractGrid(outContent.toString())
        assertEquals(8, grid.size)
        assertEquals('A', grid[2][3])
    }

    @Test
    fun `draw outside bounds does not update framebuffer but still calls render`() {
        display.draw('B'.code.toByte(), -1, 0)
        display.draw('C'.code.toByte(), 0, 8)
        display.draw('D'.code.toByte(), 8, 8)
        val grid = extractGrid(outContent.toString())
        // Only check the grid, not the whole output
        assertTrue(grid.all { row -> !row.contains('B') && !row.contains('C') && !row.contains('D') })
    }

    @Test
    fun `clear resets framebuffer to spaces and calls render`() {
        display.draw('X'.code.toByte(), 1, 1)
        outContent.reset()
        display.clear()
        val grid = extractGrid(outContent.toString())
        assertTrue(grid.all { it.all { c -> c == ' ' } })
    }

    @Test
    fun `draw non-printable character shows dot in output`() {
        display.draw(0x07, 4, 4) // BEL, not printable
        val grid = extractGrid(outContent.toString())
        assertEquals('.', grid[4][4])
    }
}