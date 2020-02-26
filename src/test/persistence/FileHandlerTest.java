package persistence;

import model.ciphers.CaesarCipher;
import model.ciphers.Cipher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class FileHandlerTest {
    FileHandler fileHandler;
    private static final String LOAD_TEST_CIPHER = "CaesarCipher5";
    private static final CaesarCipher SAVE_TEST_CIPHER = new CaesarCipher(15);

    @BeforeEach
    void runBefore() {
        fileHandler = FileHandler.getInstance();
    }

    @Test
    void singletonTest() {
        assertEquals(fileHandler, FileHandler.getInstance());
        assertEquals(FileHandler.getInstance(), FileHandler.getInstance());
    }

    @Test
    void loadCipherTest() {
        try {
            CaesarCipher cipher = (CaesarCipher) fileHandler.loadCipher(LOAD_TEST_CIPHER);
            assertEquals(5, cipher.getShift());
            assertEquals(LOAD_TEST_CIPHER, cipher.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            fail("Exception thrown");
        }
    }

    @Test
    void saveCipherTest() {
        try {
            File testFile = new File(SAVE_TEST_CIPHER.toString(), FileHandler.FILE_EXT);
            testFile.deleteOnExit();
            fileHandler.saveCipher(SAVE_TEST_CIPHER);
            CaesarCipher readCipher = (CaesarCipher) fileHandler.loadCipher(SAVE_TEST_CIPHER.toString());
            assertEquals(SAVE_TEST_CIPHER.getShift(), readCipher.getShift());
            assertEquals(SAVE_TEST_CIPHER.toString(), readCipher.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            fail("Exception thrown");
        }
    }



    @Test
    void loadSequenceTest() {
        //todo
    }

    @Test
    void saveSequenceTest() {
        //todo
    }
}
