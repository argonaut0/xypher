package ui;

import model.CipherSequence;
import model.ciphers.Cipher;

import java.util.ArrayList;

/**
 * Represents an instance of the App
 */
public class XypherApp {

    private static String ALL_CIPHERS;
    private ArrayList<CipherSequence> sequences;
    private ArrayList<Cipher> ciphers;

    public XypherApp() {
        sequences = new ArrayList<>();
        ciphers = new ArrayList<>();
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

    public String encode(String text, String cipher) {
        return null; //todo
    }

    public String decode(String text, Cipher cipher) {
        return null; //todo
    }

    public String encode(String text, CipherSequence sequence) {
        return null; //todo
    }

    public String decode(String text, CipherSequence sequence) {
        return null; //todo
    }


}
