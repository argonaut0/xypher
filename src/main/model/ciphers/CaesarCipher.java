package model.ciphers;

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
     * Constructs a new CaesarCipher with a letter shift amount.
     * @param shift The number of letters down the alphabet to substitute with.
     */
    public CaesarCipher(int shift) {
        super("CaesarCipher");
        this.letterShift = shift;
    }

    public CaesarCipher(int shift, String name) {
        super(name);
        this.letterShift = shift;
    }

    /**
     * REQUIRES: Text should be unencoded.
     * EFFECTS: Returns a copy of the input text, transformed.
     *
     * @param text The input text to be transformed.
     * @return The output text.
     */
    @Override
    public String encode(String text) {
        return encodeString(text, this::encodeLetter);
    }

    /**
     * REQUIRES: Text should be previously encoded by this cipher at the same configuration.
     * EFFECTS: Returns a copy of the input text, inversely transformed.
     *
     * @param text The input text to be inversely transformed.
     * @return The output text.
     */
    @Override
    public String decode(String text) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.substring(i, i + 1).equals(" ")) {
                output.append(" ");
            } else {
                output.append(decodeLetter(text.substring(i, i + 1)));
            }
        }
        return output.toString();
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
        return (letterPosition + shift) % 26;
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
        return  (letterPosition + (26 - (shift % 26))) % 26;
    }

    /**
     * REQUIRES: letter [A-Z] | [a-z]
     * EFFECTS: Returns the corresponding letter shifted by letterShift amount
     *
     * @param letter The letter to be encoded
     * @return The output letter
     */
    protected String encodeLetter(String letter) {
        int newPosition = shiftPosition(ALPHA_MAP.get(letter.toUpperCase()), letterShift);
        return POS_MAP.get(newPosition);
    }

    /**
     * REQUIRES: letter [A-Z] | [a-z]
     * EFFECTS: Returns the corresponding letter unshifted by letterShift amount
     *
     * @param letter The letter to be decoded
     * @return The output letter
     */
    protected String decodeLetter(String letter) {
        int newPosition = unshiftPosition(ALPHA_MAP.get(letter.toUpperCase()), letterShift);
        return POS_MAP.get(newPosition);
    }

    public int getShift() {
        return letterShift;
    }
}
