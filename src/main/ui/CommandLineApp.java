package ui;

import model.CipherSequence;
import model.ciphers.AtbashCipher;
import model.ciphers.CaesarCipher;
import model.ciphers.Cipher;
import model.ciphers.Rot13Cipher;

import java.util.ArrayList;
import java.util.Scanner;

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

    public CommandLineApp() {
        sequences = new ArrayList<>();
        scanner = new Scanner(System.in);
        ciphers = new ArrayList<>();
        ciphers.add(new AtbashCipher());
        ciphers.add(new Rot13Cipher());
        runApp();
    }


    private void runApp() {
        running = true;
        while (running) {
            printMenu();
            String input = scanner.nextLine().toLowerCase();
            processInput(input);
        }
    }

    private void printMenu() {
        System.out.println(MENU_DIVIDER);
        System.out.println("Choose an action:");
        //System.out.println("m : Manage Sequences");
        System.out.println("c : Configure a new Cipher");
        System.out.println("e : Encode a string");
        System.out.println("d : Decode a string");
        System.out.println("q : Quit");
        System.out.println(MENU_DIVIDER);
    }

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

    private void manage() {
        System.out.println("Sequences: " + sequences);
        System.out.println("WIP");
        //todo
    }

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

    private void encode() {
        System.out.println("Choose a cipher: ");
        printCiphers(ciphers);
        Cipher cipher = chooseCipher();
        System.out.println("Enter your text to encode [A-Z] [a-z]:");
        System.out.println(cipher.encode(scanner.nextLine()));
    }

    private void decode() {
        System.out.println("Choose a cipher: ");
        printCiphers(ciphers);
        Cipher cipher = chooseCipher();
        System.out.println("Enter your text to decode [A-Z] [a-z]:");
        System.out.println(cipher.decode(scanner.nextLine()));
    }

    private Cipher chooseCipher() {
        return ciphers.get(Integer.parseInt(scanner.nextLine()));
    }

    private void printCiphers(ArrayList<Cipher> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + " : " + list.get(i));
        }
    }

}
