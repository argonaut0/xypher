package model.ciphers;

/**
 * Represents an implementation of the ROT13 Cipher
 * Reference: http://practicalcryptography.com/ciphers/classical-era/rot13/
 *
 * @author Jason Hsu
 */
public class Rot13Cipher extends CaesarCipher {

    public Rot13Cipher() {
        super(13, "Rot13Cipher");
    }
}

