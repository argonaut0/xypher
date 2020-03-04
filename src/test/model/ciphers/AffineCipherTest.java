package model.ciphers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AffineCipherTest {
    private AffineCipher affineCipher;
    private static final int A = 5;
    private static final int B = 6;

    @BeforeEach
    void runBefore() {
        affineCipher = new AffineCipher(A, B);
    }

    @Test
    void nameTest() {
        assertEquals("AffineCipher-" + A + "-" + B, affineCipher.toString());
    }

    @Test
    void encodeTest() {
        assertEquals("VAFATV XPA AGSX MGJJ YF XPA QGSXJA",
                affineCipher.encode("DEFEND THE EAST WALL OF THE CASTLE"));
        assertEquals("PAJJY MYNJV", affineCipher.encode("HELLO WORLD"));
    }

    @Test
    void decodeTest() {
        assertEquals("DEFEND THE EAST WALL OF THE CASTLE",
                affineCipher.decode("VAFATV XPA AGSX MGJJ YF XPA QGSXJA"));
        assertEquals("HELLO WORLD", affineCipher.decode("PAJJY MYNJV"));
    }
}
