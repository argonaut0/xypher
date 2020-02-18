package model.ciphers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link Cipher}
 */
public class CipherTest {
    private Cipher cipher;

    @BeforeEach
    void runBefore() {
        cipher = new Cipher("TestCipher") {

            @Override
            protected String encodeLetter(String letter) {
                return letter;
            }

            @Override
            protected String decodeLetter(String letter) {
                return letter;
            }
        };
    }

    @Test
    void mapTest() {
        assertEquals(0, Cipher.ALPHA_MAP.get("A"));
        assertEquals(25, Cipher.ALPHA_MAP.get("Z"));
        assertEquals(3, Cipher.ALPHA_MAP.get("D"));
        assertEquals("A", Cipher.ALPHA_MAP.inverse().get(0));
        assertEquals("Z", Cipher.ALPHA_MAP.inverse().get(25));
        assertEquals("D", Cipher.ALPHA_MAP.inverse().get(3));
    }

    @Test
    void nameTest() {
        assertEquals("TestCipher", cipher.toString());
    }
}
