package model;

import model.ciphers.AtbashCipher;
import model.ciphers.CaesarCipher;
import model.ciphers.Cipher;
import model.ciphers.Rot13Cipher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class CipherSequenceTest {
    private CipherSequence sequence;
    private LinkedList<Cipher> refList;
    private static final AtbashCipher ATBASH = new AtbashCipher();
    private static final CaesarCipher CAESAR1 = new CaesarCipher(1);
    private static final CaesarCipher CAESAR5 = new CaesarCipher(5);
    private static final Rot13Cipher ROT13 = new Rot13Cipher();

    @BeforeEach
    void runBefore() {
        sequence = new CipherSequence();
        refList = new LinkedList<>();
    }

    @Test
    void getCipherListTest() {
        refList.addLast(ATBASH);
        refList.addLast(CAESAR1);
        refList.addLast(CAESAR5);
        refList.addLast(ROT13);

        sequence.pushCipher(ATBASH);
        sequence.pushCipher(CAESAR1);
        sequence.pushCipher(CAESAR5);
        sequence.pushCipher(ROT13);
        assertIterableEquals(refList, sequence.getCipherList());
    }

    @Test
    void getLengthTest() {
        sequence.pushCipher(ATBASH);
        sequence.pushCipher(CAESAR1);
        sequence.pushCipher(CAESAR5);
        sequence.pushCipher(ROT13);
        assertEquals(4, sequence.getSize());
    }

    @Test
    void pushCipherTest() {
        refList.addLast(ATBASH);
        sequence.pushCipher(ATBASH);
        assertIterableEquals(refList, sequence.getCipherList());
    }

    @Test
    void addCipherTest() {
        refList.addLast(ATBASH);
        refList.addLast(ROT13);
        refList.addLast(ATBASH);
        refList.addLast(ATBASH);

        sequence.pushCipher(ATBASH);
        sequence.pushCipher(ATBASH);
        sequence.pushCipher(ATBASH);
        sequence.addCipher(ROT13, 1);

        assertIterableEquals(refList, sequence.getCipherList());
    }

    @Test
    void removeCipherTest() {
        refList.addLast(ATBASH);
        refList.addLast(ROT13);
        refList.addLast(CAESAR5);

        sequence.pushCipher(ATBASH);
        sequence.pushCipher(ROT13);
        sequence.pushCipher(CAESAR1);
        sequence.pushCipher(CAESAR5);

        sequence.removeCipher(2);

        assertIterableEquals(refList, sequence.getCipherList());
    }

    @Test
    void seriesEncodeTest() {
        String testString = "THIS IS A TEST ATTACK";
        String refString =
                ROT13.encode(
                        CAESAR5.encode(
                                CAESAR1.encode(
                                        ATBASH.encode(testString))));
        sequence.pushCipher(ATBASH);
        sequence.pushCipher(CAESAR1);
        sequence.pushCipher(CAESAR5);
        sequence.pushCipher(ROT13);
        assertEquals(refString, sequence.seriesEncode(testString));
    }

    @Test
    void seriesDecodeTest() {
        String testString = "THIS IS A TEST DEFENCE";
        String refString =
                ATBASH.decode(
                        CAESAR1.decode(
                                CAESAR5.decode(
                                        ROT13.decode(testString))));
        sequence.pushCipher(ATBASH);
        sequence.pushCipher(CAESAR1);
        sequence.pushCipher(CAESAR5);
        sequence.pushCipher(ROT13);
        assertEquals(refString, sequence.seriesDecode(testString));
        assertEquals(testString, sequence.seriesDecode(sequence.seriesEncode(testString)));
    }
}
