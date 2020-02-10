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
 */
public class AtbashCipher extends Cipher {

    /**
     * Represents the sum of any two opposing letter positions.
     * Ex. A + Z, B + Y, C + X
     */
    private static final int LETTER_SUM = 1 + 26;
    /**
     * The ASCII code for a space.
     * @deprecated No longer using ascii
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
        String letters = text.toUpperCase(); // temporary guard for uppercase
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < letters.length(); i++) {
            if (letters.substring(i, i + 1).equals(" ")) {
                result.append(" ");
            } else {
                result.append(Cipher.POS_MAP.get(
                        LETTER_SUM - Cipher.ALPHA_MAP.get(letters.substring(i, i + 1))));
            }
        }
        return result.toString();
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
