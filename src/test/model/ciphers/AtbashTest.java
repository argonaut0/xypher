package model.ciphers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtbashTest {
    private AtbashCipher atbashCipher;

    @BeforeEach
    void runBefore() {
        atbashCipher = new AtbashCipher();
    }

    @Test
    void nameTest() {
        assertEquals("AtbashCipher", atbashCipher.toString());
    }

    @Test
    void encodeTest() {
        assertEquals("ZYXW", atbashCipher.encode("ABCD"));
        assertEquals("SVOOL DLIOW", atbashCipher.encode("HELLO WORLD"));
        assertEquals("ZGGZXP ZG WZDM", atbashCipher.encode("ATTACK AT DAWN"));
    }

    @Test
    void decodeTest() {
        assertEquals("ABCD", atbashCipher.decode("ZYXW"));
        assertEquals("HELLO WORLD", atbashCipher.decode("SVOOL DLIOW"));
        assertEquals("ATTACK AT DAWN", atbashCipher.decode("ZGGZXP ZG WZDM"));
    }
}
