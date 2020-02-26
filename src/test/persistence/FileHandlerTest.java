package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileHandlerTest {
    FileHandler fileHandler;
    private static final String TEST_FOLDER = "./data/test/";

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
        //todo
    }

    @Test
    void loadSequenceTest() {
        //todo
    }

    @Test
    void saveCipherTest() {
        //todo
    }

    @Test
    void saveSequenceTest() {
        //todo
    }
}
