package model.ciphers;

import com.google.common.math.IntMath;

/**
 * Represents an implementation of the Caesar Cipher.
 *
 * Reference: http://practicalcryptography.com/ciphers/classical-era/caesar/
 *
 * @author Jason Hsu
 */
public class CaesarCipher extends Cipher {
    private int letterShift;

    /**
     * EFFECTS: Constructs a new CaesarCipher with a letter shift amount.
     * @param shift The number of letters down the alphabet to substitute with.
     */
    public CaesarCipher(int shift) {
        super("CaesarCipher", Integer.toString(shift));
        this.letterShift = shift;
    }

    /**
     * EFFECTS: Constructs a new CaesarCipher with a letter shift amount and name.
     * @param shift The number of letters down the alphabet to substitute with.
     * @param name The name of the Cipher.
     */
    protected CaesarCipher(int shift, String name) {
        super(name);
        this.letterShift = shift;
    }

    /**
     * REQUIRES: letterPosition [0-25], shift (x | xeZ)
     * EFFECTS: Returns the position of the shifted letter.
     *
     * @param letterPosition The position of the initial letter.
     * @param shift The amount to shift.
     * @return The position of the shifted letter
     */
    protected int shiftPosition(int letterPosition, int shift) {
        return IntMath.mod(letterPosition + shift, 26);
    }

    /**
     * REQUIRES: letterPosition [0-25], shift (x | xeZ)
     * EFFECTS: Returns the position of the unshifted letter.
     *
     * @param letterPosition The position of the shifted letter.
     * @param shift The amount shifted.
     * @return The position of the initial letter.
     */
    protected int unshiftPosition(int letterPosition, int shift) {
        return  IntMath.mod(letterPosition + (26 - IntMath.mod(shift, 26)), 26);
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
        int newPosition = shiftPosition(ALPHA_MAP.get(letter.toUpperCase()), letterShift);
        return ALPHA_MAP.inverse().get(newPosition);
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
        int newPosition = unshiftPosition(ALPHA_MAP.get(letter.toUpperCase()), letterShift);
        return ALPHA_MAP.inverse().get(newPosition);
    }

    public int getShift() {
        return letterShift;
    }
}
