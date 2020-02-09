package model.ciphers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CipherTest {
    Cipher cipher;

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
    void nameTest() {
        assertEquals("TestCipher", cipher.toString());
    }
}
