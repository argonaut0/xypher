package model;

import model.ciphers.Cipher;
import persistence.FileHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Represents an instance of the App container, provides an API
 */
public class XypherApp {
    //todo SAVE FOLDER CONSTANT
    private static String ALL_CIPHERS;
    private HashMap<String, Encoder> encoders;
    private FileHandler fileHandler;

    public XypherApp() {
        encoders = new HashMap<>();
        fileHandler = FileHandler.getInstance();
    }

    public Cipher getCipher(String name) throws IllegalArgumentException {
        if (encoders.get(name) instanceof Cipher) {
            return (Cipher) encoders.get(name);
        } else {
            throw new IllegalArgumentException("Encoder is not a Cipher");
        }
    }

    public void saveCipher(String name) throws IllegalArgumentException, IOException {
        fileHandler.saveCipher(getCipher(name));
    }

    public void loadCipher(String name) throws IOException {
        try {
            encoders.put(name, fileHandler.loadCipher(name));
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Invalid File");
        }
    }

    public CipherSequence getSequence(String name) throws IllegalArgumentException {
        if (encoders.get(name) instanceof CipherSequence) {
            return (CipherSequence) encoders.get(name);
        } else {
            throw new IllegalArgumentException("Encoder is not a Sequence");
        }
    }

    public void saveSequence(String name) throws IllegalArgumentException, IOException {
        fileHandler.saveSequence(getSequence(name));
    }

    public void loadSequence(String name) throws IOException {
        encoders.put(name, fileHandler.loadSequence(name));
    }

    public void addEncoder(Encoder encoder) throws IllegalArgumentException {
        if (encoders.get(encoder.toString()) != null) {
            throw new IllegalArgumentException("Encoder already exists");
        } else {
            encoders.put(encoder.toString(), encoder);
        }
    }

    public void deleteEncoder(String name) throws IllegalArgumentException {
        if (encoders.get(name) != null) {
            throw new IllegalArgumentException("Encoder does not exist");
        } else {
            encoders.remove(name);
        }
    }

    /**
     * EFFECTS: Returns a linked list of all the encoder names.
     */
    public LinkedList<String> getEncoders() {
        return new LinkedList<>(encoders.keySet());
    }

    public String encode(String encoderName, String text) throws IllegalArgumentException {
        if (encoders.get(encoderName) != null) {
            return encoders.get(encoderName).encode(text);
        } else {
            throw new IllegalArgumentException("Encoder does not exist");
        }
    }

    public String decode(String encoderName, String text) throws IllegalArgumentException {
        if (encoders.get(encoderName) != null) {
            return encoders.get(encoderName).decode(text);
        } else {
            throw new IllegalArgumentException("Encoder does not exist");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Encoders: \n");
        encoders.forEach(
                (String key, Encoder encoder) -> {
                    String type = "Sequence";
                    if (encoder instanceof Cipher) {
                        type = "Cipher";
                    }
                    sb.append(key + " : " + type + "\n");
                }
        );

        return sb.toString();
    }
}
