package ui;

import model.CipherSequence;
import model.ciphers.AtbashCipher;
import model.ciphers.CaesarCipher;
import model.ciphers.Cipher;
import model.ciphers.Rot13Cipher;

import java.util.ArrayList;
import java.util.List;
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
    private static final String TRANSFORM_TEXT_PROMPT = "Enter your text to transform [A-Z] [a-z]:";
    private ArrayList<CipherSequence> sequences;
    private ArrayList<Cipher> ciphers;
    private Scanner scanner;
    private boolean running;


    /**
     * MODIFIES: this
     * EFFECTS: Sets up fields and runs app.
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
     * REQUIRES: Legal console input.
     * MODIFIES: this
     * EFFECTS: Runs a application loop.
     */
    private void runApp() {
        running = true;
        while (running) {
            printMenu();
            String input = scanner.nextLine().toLowerCase();
            processMainInput(input);
        }
    }

    /**
     * EFFECTS: Prints the main menu.
     */
    private void printMenu() {
        System.out.println(MENU_DIVIDER);
        System.out.println("Choose an action:");
        System.out.println("m : Manage Sequences");
        System.out.println("c : Configure a new Cipher");
        System.out.println("e : Encode a string");
        System.out.println("d : Decode a string");
        System.out.println("q : Quit");
        System.out.println(MENU_DIVIDER);
    }

    /**
     * MODIFIES: this
     * EFFECTS: Processes a given 1-char string input for the main menu.
     * @param input The input to process.
     */
    private void processMainInput(String input) {
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
     * REQUIRES: Legal console input.
     * EFFECTS: Processes input to manage the sequences.
     */
    private void manage() {
        System.out.println("Choose an action:");
        System.out.println("l : List sequences");
        System.out.println("n : New sequence");
        System.out.println("m : Modify sequence");
        switch (scanner.nextLine()) {
            case "l":
                printList(sequences);
                manage();
                break;
            case "n":
                newSequence();
                break;
            case "m":
                modifySequence();
                break;
            default:
                System.out.println("Command not recognized.");
        }
    }

    /**
     * REQUIRES: Legal console input.
     * MODIFIES: this
     * EFFECTS: Adds a cipher to the working ciphers list.
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
     * REQUIRES: Legal console input.
     * EFFECTS: Encodes some text with a chosen cipher.
     */
    private void encode() {
        Cipher cipher = chooseCipher();
        System.out.println(TRANSFORM_TEXT_PROMPT);
        System.out.println(cipher.encode(scanner.nextLine()));
    }

    /**
     * REQUIRES: Legal console input.
     * EFFECTS: Decodes some text with a chosen cipher.
     */
    private void decode() {
        Cipher cipher = chooseCipher();
        System.out.println(TRANSFORM_TEXT_PROMPT);
        System.out.println(cipher.decode(scanner.nextLine()));
    }

    /**
     * REQUIRES: Legal console input.
     * EFFECTS: Gets the cipher chosen by user.
     * @return The cipher from the working list chosen by user.
     */
    private Cipher chooseCipher() {
        System.out.println("Choose a cipher: ");
        printList(ciphers);
        return ciphers.get(Integer.parseInt(scanner.nextLine()));
    }

    /**
     * EFFECTS: Pretty-prints a given Arraylist of elements with indices.
     * @param list A list to be printed.
     */
    private void printList(List list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + " : " + list.get(i));
        }
    }

    /**
     * MODIFIES: this
     * EFFECTS: Creates a new sequence.
     */
    private void newSequence() {
        sequences.add(new CipherSequence());
        System.out.println("Added sequence: " + sequences.get(sequences.size() - 1));
    }

    /**
     * EFFECTS: Prints out menus for modifying a sequence.
     */
    private void modifySequence() {
        CipherSequence sequence = chooseSequence();
        System.out.println("Choose an action:");
        System.out.println("l : List Ciphers in Sequence");
        System.out.println("a : Add a cipher");
        System.out.println("r : Remove a cipher");
        System.out.println("e : Encode some text");
        System.out.println("d : Decode some text");
        processModifySequence(sequence);
    }

    /**
     * REQUIRES: Legal console input.
     * MODIFIES: sequence
     * EFFECTS: Processes user input and modifies a sequence.
     * @param sequence The sequence to be modified.
     */
    private void processModifySequence(CipherSequence sequence) {
        switch (scanner.nextLine()) {
            case "l":
                printList(sequence.getCipherList());
                break;
            case "a":
                addToSequence(sequence);
                break;
            case "r":
                removeFromSequence(sequence);
                break;
            case "e":
                System.out.println(TRANSFORM_TEXT_PROMPT);
                System.out.println(sequence.seriesEncode(scanner.nextLine()));
                break;
            case "d":
                System.out.println(TRANSFORM_TEXT_PROMPT);
                System.out.println(sequence.seriesDecode(scanner.nextLine()));
                break;
        }
    }

    /**
     * REQUIRES: Legal console input.
     * MODIFIES: sequence
     * EFFECTS: Adds a cipher to a sequence.
     * @param sequence The sequence to be added to.
     */
    private void addToSequence(CipherSequence sequence) {
        System.out.println(sequence.getCipherList());
        System.out.println("Enter the index at which to insert:");
        int index = Integer.parseInt(scanner.nextLine());
        System.out.println("Choose a Cipher to add:");
        System.out.println(ALL_CIPHERS);
        switch (Integer.parseInt(scanner.nextLine())) {
            case 0:
                sequence.addCipher(new AtbashCipher(), index);
                System.out.println("Cipher added.");
                break;
            case 1:
                sequence.addCipher(new Rot13Cipher(), index);
                System.out.println("Cipher added.");
                break;
            case 2:
                System.out.println("Enter a shift number greater than 0:");
                sequence.addCipher(new CaesarCipher(Integer.parseInt(scanner.nextLine())), index);
                System.out.println("Cipher added.");
                break;
            default:
                System.out.println("Unknown Cipher.");
        }
    }

    /**
     * REQUIRES: Legal console input.
     * MODIFIES: sequence
     * EFFECTS: Removes a cipher from the sequence.
     * @param sequence The sequence to be operated on.
     */
    private void removeFromSequence(CipherSequence sequence) {
        System.out.println("Enter the index of the cipher to remove:");
        printList(sequence.getCipherList());
        sequence.removeCipher(Integer.parseInt(scanner.nextLine()));
    }

    /**
     * REQUIRES: Legal console input.
     * EFFECTS: Gets the sequence chosen by user.
     * @return The sequence chosen by user.
     */
    private CipherSequence chooseSequence() {
        System.out.println("Choose a sequence:");
        printList(sequences);
        return sequences.get(Integer.parseInt(scanner.nextLine()));
    }
}
