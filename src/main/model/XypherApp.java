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
    private HashMap<String, Encoder> encoders;
    private FileHandler fileHandler;

    /**
     * MODIFIES: this
     * EFFECTS: initializes fields
     */
    public XypherApp() {
        encoders = new HashMap<>();
        fileHandler = FileHandler.getInstance();
    }

    /**
     * REQUIRES: The name of a valid Cipher
     * EFFECTS: Returns a reference to a stored cipher by its name
     * @param name The name of the cipher
     * @return The cipher of that name
     * @throws IllegalArgumentException Encoder is not a Cipher
     */
    public Cipher getCipher(String name) throws IllegalArgumentException {
        if (encoders.get(name) instanceof Cipher) {
            return (Cipher) encoders.get(name);
        } else {
            throw new IllegalArgumentException("Encoder is not a Cipher");
        }
    }

    /**
     * REQUIRES: The name of a valid cipher
     * MODIFIES: filesystem
     * EFFECTS: Saves a cipher to file
     * @param name The name of the cipher
     * @throws IllegalArgumentException Name is invalid
     * @throws IOException Error writing to file
     */
    public void saveCipher(String name) throws IllegalArgumentException, IOException {
        fileHandler.saveCipher(getCipher(name));
    }

    /**
     * REQUIRES: The name of a valid cipher file
     * MODIFIES: this
     * EFFECTS: Loads a cipher from a file and adds it
     * @param name The name of the cipher
     */
    public void loadCipher(String name) throws IOException {
        try {
            encoders.put(name, fileHandler.loadCipher(name));
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Invalid File");
        }
    }

    /**
     * REQUIRES: The name of a valid sequence
     * EFFECTS: Returns a reference to a sequence by name
     * @param name sequence name
     * @return the sequence
     * @throws IllegalArgumentException Invalid name
     */
    public CipherSequence getSequence(String name) throws IllegalArgumentException {
        if (encoders.get(name) instanceof CipherSequence) {
            return (CipherSequence) encoders.get(name);
        } else {
            throw new IllegalArgumentException("Encoder is not a Sequence");
        }
    }

    /**
     * REQUIRES: The name of a valid sequence
     * EFFECTS: Saves a sequence to file
     * @param name The name of the sequence to save
     * @throws IllegalArgumentException Invalid name
     * @throws IOException Error writing file
     */
    public void saveSequence(String name) throws IllegalArgumentException, IOException {
        fileHandler.saveSequence(getSequence(name));
    }

    /**
     * REQUIRES: The name of a valid sequence file
     * MODIFIES: this
     * EFFECTS: Loads a sequence from file and adds it
     * @param name sequence name
     * @throws IOException Error Reading file
     */
    public void loadSequence(String name) throws IOException {
        encoders.put(name, fileHandler.loadSequence(name));
    }

    /**
     * REQUIRES: An Encoder with a unique name
     * MODIFIES: this
     * EFFECTS: Adds an Encoder to the app
     * @param encoder The encoder to add
     * @throws IllegalArgumentException Encoder cannot be added
     */
    public void addEncoder(Encoder encoder) throws IllegalArgumentException {
        if (encoders.get(encoder.toString()) != null) {
            throw new IllegalArgumentException("Encoder already exists");
        } else {
            encoders.put(encoder.toString(), encoder);
        }
    }

    /**
     * REQUIRES: A valid encoder name
     * MODIFIES: this
     * EFFECTS: Deletes an encoder from the worklist
     * @param name encoder name
     * @throws IllegalArgumentException Encoder not in worklist
     */
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

    /**
     * REQUIRES: The name of a valid encoder, alphabetic text
     * EFFECTS: Encodes some text
     * @param encoderName the encoder to use
     * @param text the text to encode
     * @return the encoded text
     * @throws IllegalArgumentException Invalid Encoder
     */
    public String encode(String encoderName, String text) throws IllegalArgumentException {
        if (encoders.get(encoderName) != null) {
            return encoders.get(encoderName).encode(text);
        } else {
            throw new IllegalArgumentException("Encoder does not exist");
        }
    }

    /**
     * REQUIRES: The name of a valid encoder, alphabetic text
     * EFFECTS: Decodes some text
     * @param encoderName the encoder to use
     * @param text the text to decode
     * @return the decoded text
     * @throws IllegalArgumentException Invalid Encoder
     */
    public String decode(String encoderName, String text) throws IllegalArgumentException {
        if (encoders.get(encoderName) != null) {
            return encoders.get(encoderName).decode(text);
        } else {
            throw new IllegalArgumentException("Encoder does not exist");
        }
    }

    /**
     * EFFECTS: Returns Pretty printed Cipher and Sequences list
     * @return string representation of app
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Encoders: \n");
        encoders.forEach(
                (String key, Encoder encoder) -> {
                    if (encoder instanceof Cipher) {
                        sb.append(encoder.toString() + " : Cipher\n");
                    } else {
                        sb.append(encoder.toString() + " : Sequence\n");
                    }
                }
        );
        return sb.toString();
    }
}
