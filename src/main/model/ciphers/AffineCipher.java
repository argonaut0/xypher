package model.ciphers;

import com.google.common.math.IntMath;

/**
 * Represents an implementation of the Affine Cipher
 *
 * http://practicalcryptography.com/ciphers/classical-era/affine/
 *
 * @author Jason Hsu
 */
public class AffineCipher extends Cipher {

    public static final int[] PRIME_FACTORS = new int[] {13, 2};
    private int keyA;
    private int keyB;
    private int keyX;

    /**
     * REQUIRES: a is relatively prime to 26, a and b are > 0
     * MODIFIES: this
     * EFFECTS: initializes fields
     *
     * @param a Number a for the cipher
     * @param b Number b for the cipher
     */
    public AffineCipher(int a, int b) {
        super("AffineCipher", Integer.toString(a), Integer.toString(b));
        for (int i : PRIME_FACTORS) {
            if (a % i == 0) {
                throw new IllegalArgumentException("Integer 'a' should not be a relative prime of 26");
            }
        }
        keyA = a;
        keyB = b;
        keyX = 1;
        for (int i = 1; i < 26; i += 2) {
            if (IntMath.mod(a * i,26) == 1) {
                keyX = i;
                break;
            }
        }
    }

    /**
     * REQUIRES: An alphabetic string of length 1, uppercase.
     * EFFECTS: Returns the encoded version of the letter
     *
     * @param letter The letter to encode
     * @return The encoded letter
     */
    @Override
    protected String encodeLetter(String letter) {
        return ALPHA_MAP.inverse().get(IntMath.mod(keyA * ALPHA_MAP.get(letter) + keyB, 26));
    }

    /**
     * REQUIRES: An alphabetic string of length 1, uppercase.
     * EFFECTS: Returns the unencoded version of the letter
     *
     * @param letter The letter to decode
     * @return The decoded letter
     */
    @Override
    protected String decodeLetter(String letter) {
        return ALPHA_MAP.inverse().get(IntMath.mod(keyX * (ALPHA_MAP.get(letter) - keyB), 26));
    }
}
