package model.ciphers;

import com.google.common.collect.HashBiMap;

import java.util.HashMap;

/**
 * Represents an abstract elementary {@link Cipher} that performs transformations on text.
 * Ciphers are mutable..sigh.
 *
 * @author Jason Hsu
 */
public abstract class Cipher {
    /**
     * The name of the Cipher
     */
    private String cipherName;

    /**
     * A map that maps the alphabet to positions 0-25.
     */
    public static final HashBiMap<String, Integer> ALPHA_MAP = HashBiMap.create(new HashMap<String, Integer>() {
        {
            String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            for (int i = 0; i < 26; i++) {
                put(upper.substring(i, i + 1), i);
            }
        }
    });

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
     * REQUIRES: An alphabetic string of length 1, uppercase.
     * EFFECTS: Returns the encoded version of the letter
     *
     * @param letter The letter to encode
     * @return The encoded letter
     */
    protected abstract String encodeLetter(String letter);

    /**
     * REQUIRES: An alphabetic string of length 1, uppercase.
     * EFFECTS: Returns the unencoded version of the letter
     *
     * @param letter The letter to decode
     * @return The decoded letter
     */
    protected abstract String decodeLetter(String letter);

    /**
     * REQUIRES: Text should be unencoded.
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

    /**
     * Represents a Single Abstract Method that transforms a alphabetic string of length 1 to another.
     */
    protected interface LetterTransformer {
        String transform(String inputLetter);
    }

    /**
     * REQUIRES: Alphabetic text, and valid SAM
     * EFFECTS: Returns a new transformed version of some text.
     *
     * @param text The text to be transformed
     * @param le The functional method
     * @return The transformed text
     */
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
     * @return The name of this {@link Cipher}.
     */
    @Override
    public String toString() {
        return cipherName;
    }

}