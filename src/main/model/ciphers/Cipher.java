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
     * todo
     */
    protected interface LetterTransformer {
        String transform(String inputLetter);
    }

    protected String transformString(String text, LetterTransformer le) {
        text = text.toUpperCase(); // temporary guard for uppercase
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.substring(i, i + 1).equals(" ")) {
                output.append(" ");
            } else {
                output.append(le.transform(text.substring(i, i + 1)));
            }
        }
        return output.toString();
    }

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
    public String encode(String text) {
        return transformString(text, this::encodeLetter);
    }

    /**
     * REQUIRES: Text should be previously encoded by this cipher at the same configuration.
     * EFFECTS: Returns a copy of the input text, inversely transformed.
     *
     * @param text The input text to be inversely transformed.
     * @return The output text.
     */
    public String decode(String text) {
        return transformString(text, this::decodeLetter);
    }

    protected abstract String encodeLetter(String letter);

    protected abstract String decodeLetter(String letter);

    /**
     * @return The name of this {@link Cipher}.
     */
    @Override
    public String toString() {
        return cipherName;
    }

}