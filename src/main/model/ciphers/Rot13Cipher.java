package model.ciphers;

/**
 * Represents an implementation of the ROT13 Cipher, a Caesar Cipher with shift 13
 * Reference: http://practicalcryptography.com/ciphers/classical-era/rot13/
 *
 * @author Jason Hsu
 */
public class Rot13Cipher extends CaesarCipher {

    /**
     * MODIFIES: this
     * EFFECTS: Constructs a Rot13 Cipher
     */
    public Rot13Cipher() {
        super(13, "Rot13Cipher");
    }
}

