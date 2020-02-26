package persistence;

import com.google.gson.Gson;
import model.CipherSequence;
import model.ciphers.Cipher;

import java.io.*;

/**
 * Represents the single instance file handler
 *
 * @author Jason Hsu
 */
public class FileHandler {
    /**
     * The file extension to write
     */
    public static final String FILE_EXT = ".json";

    /**
     * Singleton instance
     */
    private static FileHandler singleton;
    private Gson gson;

    /**
     * MODIFIES: this
     * EFFECTS: Initializes gson
     */
    private FileHandler() {
        gson = new Gson();
    }

    /**
     * MODIFIES: this
     * EFFECTS: Returns the FileHandler, or a new one if it does not already exist
     * @return The singleton instance of this
     */
    public static FileHandler getInstance() {
        if (singleton != null) {
            return singleton;
        }

        singleton = new FileHandler();
        return singleton;
    }

    /**
     * REQUIRES: A filepath
     * EFFECTS: Converts a file's contents to a Cipher
     * @param path The filepath.
     * @return The cipher loaded from file.
     * @throws IOException Problem loading cipher.
     */
    public Cipher loadCipher(String path) throws IOException {
        return gson.fromJson(readFile(path), Cipher.class);
    }

    /**
     * REQUIRES: A filepath
     * EFFECTS: Converts a file's contents to a CipherSequence
     * @param path The filepath.
     * @return The sequence loaded from file.
     * @throws IOException Problem loading sequence.
     */
    public CipherSequence loadSequence(String path) throws IOException {
        return gson.fromJson(readFile(path), CipherSequence.class);
    }

    /**
     * REQUIRES: The Cipher of the same name is not already saved.
     * MODIFIES: filesystem
     * EFFECTS: Saves a cipher to a new file.
     * @param cipher The cipher to be saved.
     * @throws IOException Problem saving cipher
     */
    public void saveCipher(Cipher cipher) throws IOException {
        writeFile(cipher.toString() + FILE_EXT, gson.toJson(cipher));
    }

    /**
     * REQUIRES: The sequence of the same name is not already saved.
     * MODIFIES: filesystem
     * EFFECTS: Saves a sequence to a new file.
     * @param sequence The sequence to be saved.
     * @throws IOException Problem saving cipher
     */
    public void saveSequence(CipherSequence sequence) throws IOException {
        writeFile(sequence.toString() + FILE_EXT, gson.toJson(sequence));
    }

    /**
     * REQUIRES: A valid new filename.
     * MODIFIES: filesystem
     * EFFECTS: Writes some text to a file.
     * @param fileName The filename.
     * @param text The text to save.
     * @throws IOException Problem writing to file.
     */
    private void writeFile(String fileName, String text) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(text);
        fileWriter.close();
    }

    /**
     * REQUIRES: A valid filename.
     * EFFECTS: Reads the contents of a file.
     * @param fileName The file to read.
     * @return The file's contents
     * @throws IOException Problem reading from file.
     */
    private String readFile(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        StringBuilder result = new StringBuilder();
        String line;
        do {
            line = bufferedReader.readLine();
            result.append(line);
        } while (line != null);

        return result.toString();
    }
}
