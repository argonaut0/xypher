package model.ciphers;

import java.util.HashMap;

/**
 * Represents an abstract elementary {@link Cipher} that performs transformations on text.
 * Ciphers are mutable..sigh.
 *
 * @author Jason Hsu
 */
public abstract class Cipher {
    /**
     * A map that maps the alphabet to positions 0-26
     */
    public static final HashMap<String, Integer> ALPHA_MAP = new HashMap<String, Integer>() {
        {
            String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            for (int i = 0; i < 26; i++) {
                put(upper.substring(i, i + 1), i);
            }
        }
    };

    public static final HashMap<Integer, String> POS_MAP = new HashMap<Integer, String>() {
        {
            String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            for (int i = 0; i < 26; i++) {
                put(i, upper.substring(i, i + 1));
            }
        }
    };

    /**
     * The name of the Cipher
     */
    private String cipherName;

    /**
     * REQUIRES: A text string comprised of characters defined in ASCII.
     * EFFECTS: Returns the ASCII representation of a string.
     * @param text The string to be converted.
     * @return An array representing ASCII codes.
     * @deprecated Use ALPHA_MAP instead
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
     * @deprecated Use POS_MAP instead
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
    protected Cipher(String name) {
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