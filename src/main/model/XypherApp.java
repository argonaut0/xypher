package model;

import model.CipherSequence;
import model.ciphers.Cipher;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents an instance of the App
 */
public class XypherApp {

    private static String ALL_CIPHERS;
    private HashMap<String, Encoder> encoders;

    public XypherApp() {
        encoders = new HashMap<>();
        //todo: populate default ciphers
    }

    public void addCipher() {
        //todo
    }

    public void deleteCipher() {
        //todo
    }

    public void saveCipher() {
        //todo
    }

    public void loadCipher() {
        //todo
    }

    public void addSequence() {
        //todo
    }

    public void deleteSequence() {
        //todo
    }

    public void saveSequence() {
        //todo
    }

    public void loadSequence() {
        //todo
    }

    public void getEncoders() {
        //todo
    }

    public String encode(String text, String encoderName) {
        return null; //todo
    }

    public String decode(String text, String encoderName) {
        return null; //todo
    }

    @Override
    public String toString() {
        return null; //todo
    }
}
