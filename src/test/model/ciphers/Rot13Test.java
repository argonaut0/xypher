package model.ciphers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Rot13Cipher}
 */
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Rot13Test {
    private Rot13Cipher rot13Cipher;

    @BeforeEach
    void runBefore() {
        rot13Cipher = new Rot13Cipher();
    }

    @Test
    void nameTest() {
        assertEquals("Rot13Cipher", rot13Cipher.toString());
    }

    @Test
    void encodeTest() {
        assertEquals("NOPQ", rot13Cipher.encode("ABCD"));
        assertEquals("URYYB JBEYQ", rot13Cipher.encode("HELLO WORLD"));
        assertEquals("NGGNPX NG QNJA", rot13Cipher.encode("ATTACK AT DAWN"));
    }

    @Test
    void decodeTest() {
        assertEquals("ABCD", rot13Cipher.decode("NOPQ"));
        assertEquals("HELLO WORLD", rot13Cipher.decode("URYYB JBEYQ"));
        assertEquals("ATTACK AT DAWN", rot13Cipher.decode("NGGNPX NG QNJA"));
    }
}
