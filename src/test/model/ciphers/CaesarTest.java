package model.ciphers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CaesarTest {
    private CaesarCipher caesarCipher1;
    private CaesarCipher caesarCipher6;
    private CaesarCipher caesarCipher26;

    @BeforeEach
    void runBefore() {
        caesarCipher1 = new CaesarCipher(1);
        caesarCipher6 = new CaesarCipher(6);
        caesarCipher26 = new CaesarCipher(26);
    }

    @Test
    void constructorTests() {
        assertEquals(1, caesarCipher1.getShift());
        assertEquals(6, caesarCipher6.getShift());
        assertEquals(26, caesarCipher26.getShift());
    }

    @Test
    void shiftPositionTest() {
        assertEquals(2, caesarCipher1.shiftPosition(1, caesarCipher1.getShift()));
        assertEquals(7, caesarCipher6.shiftPosition(1, caesarCipher6.getShift()));
        assertEquals(1, caesarCipher26.shiftPosition(1, caesarCipher26.getShift()));
        assertEquals(26, caesarCipher1.shiftPosition(1, -1));
    }

    @Test
    void encodeLetterTest() {
        assertEquals("B", caesarCipher1.encodeLetter("A"));
        assertEquals("G", caesarCipher6.encodeLetter("A"));
        assertEquals("F", caesarCipher26.encodeLetter("F"));
    }

    @Test
    void decodeLetterTest() {
        assertEquals("A", caesarCipher1.decodeLetter("B"));
        assertEquals("A", caesarCipher6.decodeLetter("G"));
        assertEquals("L", caesarCipher26.decodeLetter("L"));
    }

    @Test
    void encodeTest() {
        assertEquals("EFGFOE UIF FBTU XBMM PG UIF DBTUMF",
                caesarCipher1.encode("defend the east wall of the castle"));
    }

    @Test
    void decodeTest() {
        assertEquals("DEFEND THE EAST WALL OF THE CASTLE",
                caesarCipher1.decode("efgfoe uif fbtu xbmm pg uif dbtumf"));
    }
}
