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
        assertEquals(1, Cipher.ALPHA_MAP.get("A"));
        assertEquals(26, Cipher.ALPHA_MAP.get("Z"));
        assertEquals(4, Cipher.ALPHA_MAP.get("D"));
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
