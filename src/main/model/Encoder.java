package model;

/**
 * Represents an object that can encode and decode text
 */
public interface Encoder {
    /**
     * EFFECTS: encodes text
     */
    String encode(String text);

    /**
     * EFFECTS: decodes text
     */
    String decode(String text);
}
