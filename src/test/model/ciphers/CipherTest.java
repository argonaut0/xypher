package model.ciphers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CipherTest {
    private Cipher cipher;

    @BeforeEach
    void runBefore() {
        cipher = new Cipher("TestCipher") {
            @Override
            public String encode(String text) {
                return null;
            }

            @Override
            public String decode(String text) {
                return null;
            }
        };
    }

    @Test
    void mapTest() {
        assertEquals(0, Cipher.ALPHA_MAP.get("A"));
        assertEquals(25, Cipher.ALPHA_MAP.get("Z"));
        assertEquals(3, Cipher.ALPHA_MAP.get("D"));
        assertEquals("A", Cipher.POS_MAP.get(0));
        assertEquals("Z", Cipher.POS_MAP.get(25));
        assertEquals("D", Cipher.POS_MAP.get(3));
    }

    @Test
    void nameTest() {
        assertEquals("TestCipher", cipher.toString());
    }

    @Test
    void toAsciiTest() {
        assertArrayEquals(new int[]{65, 90, 66, 71, 69}, Cipher.toAscii("AZBGE"));
        assertArrayEquals(new int[]{97, 98, 99, 61, 49, 50, 51}, Cipher.toAscii("abc=123"));
    }

    @Test
    void fromAsciiTest() {
        int[] testCodes = {66, 90, 65, 71, 90};
        assertEquals("BZAGZ", Cipher.fromAscii(testCodes));
        assertEquals("9e?", Cipher.fromAscii(new int[]{57, 101, 63}));
    }
}
