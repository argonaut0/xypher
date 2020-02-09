package model.ciphers;

/**
 * Represents an abstract elementary {@link Cipher} that performs transformations on text.
 * Ciphers are mutable..sigh.
 *
 * @author Jason Hsu
 * @version 1.0.0
 */
public abstract class Cipher {

    private String cipherName;

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
     * REQUIRES: Text must be comprised of [A-Z][0-9], and unencoded
     * EFFECTS: Returns a copy of the input text, transformed.
     *
     * @param text The input text to be transformed.
     * @return The output text.
     */
    public abstract String encode(String text);

    /**
     * REQUIRES: Text must be comprised of [A-Z][0-9], and previously encoded by this cipher at the same configuration.
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