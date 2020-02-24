package model;

import com.google.common.collect.Lists;
import model.ciphers.Cipher;

import java.util.LinkedList;

/**
 * Represents a sequence of Ciphers. Stores a sequence of Ciphers that can be applied to text.
 */
public class CipherSequence {
    private LinkedList<Cipher> internalList;

    public CipherSequence() {
        internalList = new LinkedList<>();
    }

    /**
     * EFFECTS: Returns a copy of the internal list.
     * @return A copy of the internal list
     */
    public LinkedList<Cipher> getCipherList() {
        return new LinkedList<Cipher>(internalList);
    }

    /**
     * @return The number of elements in the sequence.
     */
    public int getSize() {
        return internalList.size();
    }

    /**
     * MODIFIES: this
     * EFFECTS: Adds a cipher to the end of the sequence.
     */
    public void pushCipher(Cipher cipher) {
        internalList.addLast(cipher);
    }

    /**
     * REQUIRES: Index within list size.
     * MODIFIES: this
     * EFFECTS: Inserts a cipher at given index of the sequence.
     *
     * @param cipher The cipher to insert
     * @param index The index at which to insert
     */
    public void addCipher(Cipher cipher, int index) {
        internalList.add(index, cipher);
    }

    /**
     * REQUIRES: Index within list size.
     * MODIFIES: this
     * EFFECTS: Removes the cipher at the given index.
     * @param index The index of cipher of which to remove
     */
    public void removeCipher(int index) {
        internalList.remove(index);
    }

    /**
     * REQUIRES: text must be comprised of [A-Z] [a-z]
     * EFFECTS: Encodes the text with each cipher in the sequence, in series.
     * @param text The text to be encoded
     * @return The encoded text
     */
    public String seriesEncode(String text) {
        String output = text;
        for (Cipher cipher : internalList) {
            output = cipher.encode(output);
        }
        return output;
    }

    /**
     * REQUIRES: text has been encoded by this a CipherSequence with equal internal lists.
     * EFFECTS: Decodes the text with the inverse of seriesEncode.
     * @param text The text to be decoded
     * @return The decoded text
     */
    public String seriesDecode(String text) {
        String output = text;
        for (Cipher cipher : Lists.reverse(internalList)) {
            output = cipher.decode(output);
        }
        return output;
    }
}
