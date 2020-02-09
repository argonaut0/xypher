package model.ciphers;

/**
 * Represents an implementation of the Atbash Cipher, a substitution cipher that replaces every letter of position *n*
 * in the alphabet (starting from 1) with the letter of position *27 - n*
 *
 * This transformation is invertible.
 *
 * Reference: http://practicalcryptography.com/ciphers/classical-era/atbash-cipher/
 *
 * @author Jason Hsu
 * @version 1.0.0
 */
public class AtbashCipher extends Cipher {

    /**
     * Represents the sum of any two opposing ASCII letter codes.
     * Ex. A + Z, B + Y, C + X
     */
    private static final int UPPER_ASCII_SUM = 90 + 65;
    /**
     * The ASCII code for a space.
     */
    private static final int SPACE_CODE = 32;

    public AtbashCipher() {
        super("AtbashCipher");
    }

    /**
     * REQUIRES: Text must be comprised of [A-Z] and spaces, and unencoded.
     * EFFECTS: Returns a copy of the input text, transformed.
     *
     * @param text The input text to be transformed.
     * @return The output text.
     */
    @Override
    public String encode(String text) {
        int[] ascii = Cipher.toAscii(text.toUpperCase()); // temporary guard for uppercase
        for (int i = 0; i < ascii.length; i++) {
            if (ascii[i] != SPACE_CODE) {
                ascii[i] = UPPER_ASCII_SUM - ascii[i];
            }
        }
        return Cipher.fromAscii(ascii);
    }

    /**
     * REQUIRES: Text must be comprised of [A-Z] and spaces, and encoded.
     * EFFECTS: Returns a copy of the input text, inversely transformed.
     *
     * @param text The input text to be inversely transformed.
     * @return The output text.
     */
    @Override
    public String decode(String text) {
        return encode(text);
    }
}
