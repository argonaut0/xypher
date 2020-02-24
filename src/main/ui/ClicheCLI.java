package ui;

import asg.cliche.Command;
import model.CipherSequence;
import model.ciphers.AtbashCipher;
import model.ciphers.Cipher;
import model.ciphers.Rot13Cipher;

import java.util.ArrayList;

public class ClicheCLI {
    private enum CipherList {
        ATBASH,
        ROT13,
        CAESAR
    }

    private ArrayList<CipherSequence> sequences;
    private ArrayList<Cipher> ciphers;

    /**
     * MODIFIES: this
     * EFFECTS: Sets up fields
     */
    public ClicheCLI() {
        sequences = new ArrayList<>();
        ciphers = new ArrayList<>();
        ciphers.add(new AtbashCipher());
        ciphers.add(new Rot13Cipher());
    }

    @Command
    public void addCipher(CipherList cipher) {

    }
}
