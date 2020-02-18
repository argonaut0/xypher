package model.ciphers;

/**
 * Represents an implementation of the Atbash Cipher, a substitution cipher that replaces every letter of position *n*
 * in the alphabet (starting from 0) with the letter of position *26 - n*
 *
 * This transformation is invertible.
 *
 * Reference: http://practicalcryptography.com/ciphers/classical-era/atbash-cipher/
 *
 * @author Jason Hsu
 */
public class AtbashCipher extends Cipher {

    /**
     * Represents the sum of any two opposing letter positions.
     * Ex. A + Z, B + Y, C + X
     */
    private static final int LETTER_SUM = 0 + 25;

    public AtbashCipher() {
        super("AtbashCipher");
    }

    @Override
    protected String encodeLetter(String letter) {
        return Cipher.POS_MAP.get(
                LETTER_SUM - Cipher.ALPHA_MAP.get(letter));
    }

    @Override
    protected String decodeLetter(String letter) {
        return encodeLetter(letter);
    }
}
