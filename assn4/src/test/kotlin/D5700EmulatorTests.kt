import org.example.D5700Emulator
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.PrintStream
import java.nio.file.Path

class D5700EmulatorTests {
    private val originalIn = System.`in`
    private val originalOut = System.out

    @AfterEach
    fun restoreIO() {
        System.setIn(originalIn)
        System.setOut(originalOut)
    }

    @Test
    fun `run with missing ROM file prints error`(@TempDir tempDir: Path) {
        val missingPath = tempDir.resolve("missing.bin").toString()
        val input = ByteArrayInputStream((missingPath + "\n").toByteArray())
        System.setIn(input)
        val out = ByteArrayOutputStream()
        System.setOut(PrintStream(out))

        assertThrows(FileNotFoundException::class.java) {
            D5700Emulator.run()
        }
        val output = out.toString()
        assertTrue(output.contains("D5700 Emulator v1.0"))
    }

    @Test
    fun `run with empty ROM file does not crash`(@TempDir tempDir: Path) {
        val romFile = tempDir.resolve("emptyrom.bin").toFile()
        romFile.writeBytes(byteArrayOf())
        val input = ByteArrayInputStream((romFile.absolutePath + "\n").toByteArray())
        System.setIn(input)
        val out = ByteArrayOutputStream()
        System.setOut(PrintStream(out))

        // Should not throw, but will likely halt immediately
        D5700Emulator.run()
        val output = out.toString()
        assertTrue(output.contains("D5700 Emulator v1.0"))
        assertTrue(output.contains("Program halted. Emulator exiting."))
    }
}