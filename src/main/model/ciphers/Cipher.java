package model.ciphers;

/**
 * Represents an abstract elementary {@link Cipher} that performs transformations on text.
 * Ciphers are mutable..sigh.
 *
 * @author Jason Hsu
 */
public abstract class Cipher {

    private String cipherName;

    /**
     * REQUIRES: A text string comprised of characters defined in ASCII.
     * EFFECTS: Returns the ASCII representation of a string.
     * @param text The string to be converted.
     * @return An array representing ASCII codes.
     */
    public static int[] toAscii(String text) {
        int[] ascii = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            ascii[i] = text.charAt(i);
        }
        return ascii;
    }

    /**
     * REQUIRES: An int array comprised of ASCII codes.
     * EFFECTS: Returns the string represented by ASCII codes.
     * @param ascii ASCII codes to be converted.
     * @return A string converted from ASCII.
     */
    public static String fromAscii(int[] ascii) {
        String text = "";
        for (int c : ascii) {
            text = text + (char)c;
        }
        return text;
    }

    /**
     * MODIFIES: this
     * EFFECTS: Initializes the constant name of the {@link Cipher}
     *
     * @param name The name of the {@link Cipher}
     */
    public Cipher(String name) {
        cipherName = name;
    }

    /**
     * REQUIRES: Text should be unencoded
     * EFFECTS: Returns a copy of the input text, transformed.
     *
     * @param text The input text to be transformed.
     * @return The output text.
     */
    public abstract String encode(String text);

    /**
     * REQUIRES: Text should be previously encoded by this cipher at the same configuration.
     * EFFECTS: Returns a copy of the input text, inversely transformed.
     *
     * @param text The input text to be inversely transformed.
     * @return The output text.
     */
    public abstract String decode(String text);

    /**
     * @return The name of this {@link Cipher}.
     */
    @Override
    public String toString() {
        return cipherName;
    }

}