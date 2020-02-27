package model;

/**
 * Represents an object that can encode and decode text
 */
public interface Encoder {
    /**
     * EFFECTS: encodes text
     */
    public String encode(String text);

    /**
     * EFFECTS: decodes text
     */
    public String decode(String text);
}
