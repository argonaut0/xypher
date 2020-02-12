package ui;

import model.CipherSequence;
import model.ciphers.AtbashCipher;
import model.ciphers.CaesarCipher;
import model.ciphers.Cipher;
import model.ciphers.Rot13Cipher;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents an instance of a command line implementation of the app.
 * @author Jason Hsu
 */
public class CommandLineApp {
    private static final String MENU_DIVIDER = "------------------";
    private static final String ALL_CIPHERS =
            "0 : AtbashCipher\n"
                    + "1 : Rot13Cipher\n"
                    + "2 : CaesarCipher";
    private ArrayList<CipherSequence> sequences;
    private ArrayList<Cipher> ciphers;
    private Scanner scanner;
    private boolean running;


    /**
     * EFFECTS: Sets up fields and runs app
     */
    public CommandLineApp() {
        sequences = new ArrayList<>();
        scanner = new Scanner(System.in);
        ciphers = new ArrayList<>();
        ciphers.add(new AtbashCipher());
        ciphers.add(new Rot13Cipher());
        runApp();
    }

    /**
     * MODIFIES: this
     * EFFECTS: Runs a application loop
     */
    private void runApp() {
        running = true;
        while (running) {
            printMenu();
            String input = scanner.nextLine().toLowerCase();
            processInput(input);
        }
    }

    /**
     * EFFECTS: Prints the main menu
     */
    private void printMenu() {
        System.out.println(MENU_DIVIDER);
        System.out.println("Choose an action:");
        //System.out.println("m : Manage Sequences"); todo
        System.out.println("c : Configure a new Cipher");
        System.out.println("e : Encode a string");
        System.out.println("d : Decode a string");
        System.out.println("q : Quit");
        System.out.println(MENU_DIVIDER);
    }

    /**
     * MODIFIES: this
     * EFFECTS: Processes a given 1-char string input
     * @param input the input to process
     */
    private void processInput(String input) {
        switch (input) {
            case "m":
                manage();
                break;
            case "c":
                configure();
                break;
            case "e":
                encode();
                break;
            case "d":
                decode();
                break;
            case "q":
                running = false;
                break;
            default:
                printMenu();
                System.out.println("Command not recognized.");
        }
    }

    /**
     * todo
     */
    private void manage() {
        System.out.println("Sequences: " + sequences);
        System.out.println("WIP");
        //todo
    }

    /**
     * MODIFIES: this
     * EFFECTS: Adds a cipher to the working ciphers list
     */
    private void configure() {
        System.out.println("Choose a Cipher to add:");
        System.out.println(ALL_CIPHERS);
        switch (Integer.parseInt(scanner.nextLine())) {
            case 0:
                ciphers.add(new AtbashCipher());
                System.out.println("Cipher added.");
                break;
            case 1:
                ciphers.add(new Rot13Cipher());
                System.out.println("Cipher added.");
                break;
            case 2:
                System.out.println("Enter a shift number greater than 0:");
                ciphers.add(new CaesarCipher(Integer.parseInt(scanner.nextLine())));
                System.out.println("Cipher added.");
                break;
            default:
                System.out.println("Unknown Cipher.");
        }

    }

    /**
     * EFFECTS: Encodes some text with a chosen cipher
     */
    private void encode() {
        System.out.println("Choose a cipher: ");
        printCiphers(ciphers);
        Cipher cipher = chooseCipher();
        System.out.println("Enter your text to encode [A-Z] [a-z]:");
        System.out.println(cipher.encode(scanner.nextLine()));
    }

    /**
     * EFFECTS: Decodes some text with a chosen cipher
     */
    private void decode() {
        System.out.println("Choose a cipher: ");
        printCiphers(ciphers);
        Cipher cipher = chooseCipher();
        System.out.println("Enter your text to decode [A-Z] [a-z]:");
        System.out.println(cipher.decode(scanner.nextLine()));
    }

    /**
     * EFFECTS: Gets the cipher chosen by user
     * @return The cipher from the working list chosen by user
     */
    private Cipher chooseCipher() {
        return ciphers.get(Integer.parseInt(scanner.nextLine()));
    }

    /**
     * EFFECTS: Pretty-prints a given Arraylist of ciphers with indices
     * @param list A list to be printed
     */
    private void printCiphers(ArrayList<Cipher> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + " : " + list.get(i));
        }
    }

}
