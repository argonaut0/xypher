package model;

/**
 * Represents an object that can encode and decode text
 */
public interface Encoder {
    public String encode(String text);

    public String decode(String text);
}
