import org.example.ProgramLoader
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Path

class ProgramLoaderTests {

    @Test
    fun `loadROMFile returns file bytes correctly`(@TempDir tempDir: Path) {
        val file = tempDir.resolve("rom.bin").toFile()
        val bytes = byteArrayOf(1, 2, 3, 4, 5)
        file.writeBytes(bytes)
        val loaded = ProgramLoader.loadROMFile(file.absolutePath)
        assertArrayEquals(bytes, loaded)
    }

    @Test
    fun `loadROMFile returns empty array for empty file`(@TempDir tempDir: Path) {
        val file = tempDir.resolve("emptyrom.bin").toFile()
        file.writeBytes(byteArrayOf())
        val loaded = ProgramLoader.loadROMFile(file.absolutePath)
        assertArrayEquals(byteArrayOf(), loaded)
    }

    @Test
    fun `loadROMFile throws exception for missing file`() {
        val missingPath = "nonexistent_file.bin"
        val ex = assertThrows(java.io.FileNotFoundException::class.java) {
            ProgramLoader.loadROMFile(missingPath)
        }
        assertTrue(ex.message?.contains(missingPath) == true)
    }
}