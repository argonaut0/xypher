package ui;

import asg.cliche.Command;
import model.CipherSequence;
import model.ciphers.AtbashCipher;
import model.ciphers.CaesarCipher;
import model.ciphers.Cipher;
import model.ciphers.Rot13Cipher;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Represents a ClicheCLI user interface
 */
public class ClicheCLI {

    private XypherApp app;

    /**
     * MODIFIES: this
     * EFFECTS: Sets up fields
     */
    public ClicheCLI() {
        app = new XypherApp();
    }

    /**
     * REQUIRES: a cipher type followed by arguments if any
     * MODIFIES: this
     * EFFECTS: Adds a cipher to the worklist
     * @param args At least 1 argument
     */
    @Command
    public void addCipher(String... args) {
        try {
            switch (args[0]) {
                case "atbash":
                    app.addEncoder(new AtbashCipher());
                    break;
                case "rot13":
                    app.addEncoder(new Rot13Cipher());
                    break;
                case "caesar":
                    app.addEncoder(new CaesarCipher(Integer.parseInt(args[1])));
                    break;
                default:
                    System.out.println("Unrecognized Cipher Type");
                    break;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Incorrect arguments");
        }
    }

    /**
     * REQUIRES: an alphabetic name
     * mODIFIES: this
     * EFFECTS: adds a new sequence with a name
     * @param name the name
     */
    @Command
    public void addSequence(String name) {
        try {
            app.addEncoder(new CipherSequence(name));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * EFFECTS: Lists the encoders currently in worklist
     */
    @Command
    public void listEncoders() {
        System.out.println(app.toString());
    }

    /**
     * REQUIRES: a sequence name
     * EFFECTS: lists the encoders contained in the sequence
     * @param sequenceName sequence name
     */
    @Command
    public void listEncoders(String sequenceName) {
        try {
            CipherSequence sequence = app.getSequence(sequenceName);
            LinkedList<Cipher> ciphers = sequence.getCipherList();
            StringBuilder sb = new StringBuilder();
            sb.append(sequence.toString() + " : Sequence\n");
            ciphers.forEach(
                    (Cipher cipher) -> {
                        sb.append("- " + cipher.toString() + " : Cipher\n");
                    });
            System.out.println(sb.toString());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * REQUIRES: a valid encoder name, alphabetic text
     * EFFECTS: encodes and prints out text
     * @param encoder encoder to use
     * @param text text to encode
     */
    @Command
    public void encode(String encoder, String text) {
        try {
            System.out.println(app.encode(encoder, text));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * REQUIRES: a valid encoder name, alphabetic text
     * EFFECTS: decodes and prints out text
     * @param encoder encoder to use
     * @param text text to decode
     */
    @Command
    public void decode(String encoder, String text) {
        try {
            System.out.println(app.decode(encoder, text));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * REQUIRES: a valid cipher name
     * MODIFIES: filesystem
     * EFFECTS: saves a cipher to file
     * @param name cipher name
     */
    @Command
    public void saveCipher(String name) {
        try {
            app.saveCipher(name);
        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * REQUIRES: a valid file name
     * MODIFIES: this
     * EFFECTS: reads and adds a cipher from file
     * @param name cipher name
     */
    @Command
    public void loadCipher(String name) {
        try {
            app.loadCipher(name);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * REQUIRES: a valid file name
     * MODIFIES: filesystem
     * EFFECTS: saves a sequence to file
     * @param name sequence name
     */
    @Command
    public void saveSequence(String name) {
        try {
            app.saveSequence(name);
        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * REQUIRES: a valid file name
     * MODIFIES: filesystem
     * EFFECTS: reads and adds a sequence from file
     * @param name sequence name
     */
    @Command
    public void loadSequence(String name) {
        try {
            app.loadSequence(name);
        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * REQUIRES: valid encoder name
     * MODIFIES: this
     * EFFECTS: removes an encoder from app
     * @param name encoder name
     */
    @Command
    public void deleteEncoder(String name) {
        try {
            app.deleteEncoder(name);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * REQUIRES: valid arguments
     * MODIFIES: this
     * EFFECTS: adds/removes encoders from sequences
     * @param args sequenceName, add/remove/push, (ciphername), (index)
     */
    @Command
    public void modifySequence(String... args) {
        try {
            CipherSequence sequence = app.getSequence(args[0]);
            switch (args[1]) {
                case "add":
                    sequence.addCipher(app.getCipher(args[2]), Integer.parseInt(args[3]));
                    break;
                case "remove":
                    sequence.removeCipher(Integer.parseInt(args[2]));
                    break;
                case "push":
                    sequence.pushCipher(app.getCipher(args[2]));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid arguments");
            }
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
}
