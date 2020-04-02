package persistence;

import model.CipherSequence;
import model.ciphers.AtbashCipher;
import model.ciphers.CaesarCipher;
import model.ciphers.Cipher;
import model.ciphers.Rot13Cipher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class FileHandlerTest {
    FileHandler fileHandler;
    private static final String LOAD_TEST_CIPHER = "CaesarCipher-5";
    private static final CaesarCipher SAVE_TEST_CIPHER = new CaesarCipher(15);
    private static final String LOAD_TEST_SEQ = "TestSequence";

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
            CaesarCipher cipher = (CaesarCipher) fileHandler.loadEncoder(LOAD_TEST_CIPHER);
            assertEquals(5, cipher.getShift());
            assertEquals(LOAD_TEST_CIPHER, cipher.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            fail("Exception thrown");
        }
    }

    @Test
    void loadNonConfigCipherTest() {
        try {
            Cipher cipher = (AtbashCipher) fileHandler.loadEncoder("AtbashCipher");
            assertEquals("AtbashCipher", cipher.toString());
            assertEquals(AtbashCipher.class, cipher.getClass());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            fail("Exception thrown");
        }
    }

    @Test
    void loadSequenceFailTest() {
        try {
            CipherSequence sequence = (CipherSequence) fileHandler.loadEncoder("TestSequenceError");
            fail("Exception not thrown");
        } catch (Exception e) {
        }
    }

    @Test
    void saveCipherTest() {
        File testFile = new File("./data/" + SAVE_TEST_CIPHER.toString() + FileHandler.FILE_EXT);
        testFile.deleteOnExit();
        try {
            fileHandler.saveEncoder(SAVE_TEST_CIPHER);
            CaesarCipher readCipher = (CaesarCipher) fileHandler.loadEncoder(SAVE_TEST_CIPHER.toString());
            assertEquals(SAVE_TEST_CIPHER.getShift(), readCipher.getShift());
            assertEquals(SAVE_TEST_CIPHER.toString(), readCipher.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            fail("Exception thrown");
        }
    }

    @Test
    void loadSequenceTest() {
        try {
            CipherSequence sequence =  (CipherSequence) fileHandler.loadEncoder(LOAD_TEST_SEQ);
            assertEquals(LOAD_TEST_SEQ, sequence.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            fail("Exception thrown");
        }
    }

    @Test
    void saveSequenceTest() {
        File testFile = new File("./data/TestSaveSequence.json");
        testFile.deleteOnExit();

        CipherSequence sequence = new CipherSequence("TestSaveSequence");
        sequence.pushCipher(new AtbashCipher());
        sequence.pushCipher(new Rot13Cipher());
        try {
            fileHandler.saveEncoder(sequence);
            CipherSequence readSeq = (CipherSequence) fileHandler.loadEncoder("TestSaveSequence");
            assertEquals(sequence.toString(), readSeq.toString());
            assertEquals(sequence.encode("hello"), readSeq.encode("hello"));
        } catch (IOException | IllegalArgumentException | ClassNotFoundException e) {
            e.printStackTrace();
            fail("Exception thrown");
        }
    }
}
