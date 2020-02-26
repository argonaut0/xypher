package ui;

import asg.cliche.Command;
import model.CipherSequence;
import model.XypherApp;
import model.ciphers.AtbashCipher;
import model.ciphers.CaesarCipher;
import model.ciphers.Rot13Cipher;

import java.io.IOException;

public class ClicheCLI {

    private XypherApp app;
    //todo sequences

    /**
     * MODIFIES: this
     * EFFECTS: Sets up fields
     */
    public ClicheCLI() {
        app = new XypherApp();
    }

    /**
     *
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

    @Command
    public void addSequence(String name) {
        try {
            app.addEncoder(new CipherSequence(name));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Command
    public String listEncoders() {
        return app.toString();
    }

    @Command
    public void encode(String encoder, String text) {
        try {
            System.out.println(app.encode(encoder, text));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Command
    public void decode(String encoder, String text) {
        try {
            System.out.println(app.decode(encoder, text));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Command
    public void saveCipher(String name) {
        try {
            app.saveCipher(name);
        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Command
    public void loadCipher(String name) {
        try {
            app.loadCipher(name);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Command
    public void saveSequence(String name) {
        try {
            app.saveSequence(name);
        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Command
    public void loadSequence(String name) {
        try {
            app.loadSequence(name);
        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Command
    public void deleteEncoder(String name) {
        try {
            app.deleteEncoder(name);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
