package vigenere;

/**
 * Vigenere cipher changes the letter frequency, so the encrypted message
 * is divided into slices, and the letter frequency analysis is applied to
 * every slice, which is the same as breaking a Caesar cipher.
 * 
 * This class handles two cases: when the key lengthis known and when it's unknown.
 * 
 * It calls CaesarBreaker to decrypt the message.
 * 
 * As CaesarBreaker, this doesn't work for every text, especially short ones.
 * 
 @author dinamuktubayeva
 */ 

import java.util.HashSet;

public class VigenereBreaker {
    private int[] key;

    public String breakWithKnownLength(String encrypted, int klength) {
        key = new int[klength];
        key = tryKeyLength(encrypted, klength);
        VigenereCipher vc = new VigenereCipher(key);
        String decrypted = vc.decrypt(encrypted);
        return decrypted;
    }

    public String breakWithUnknownLength(String encrypted, HashSet<String> dict) {
        int numWords;
        int totalWords = encrypted.split("\\W++").length;

        // In case of failure, this will be printed
        String decrypted = "Could not decrypt";

        // Check possible values of key length
        for (int i = 1; i <= encrypted.length() / 2; i++) {
            int[] theKey = tryKeyLength(encrypted, i);
            VigenereCipher vc = new VigenereCipher(theKey);
            String curDecrypted = vc.decrypt(encrypted);

            // Counting valid words
            numWords = countWords(curDecrypted, dict);

            // Success, if at least half of the words are valid
            if (numWords > 0.5 * totalWords) {
                key = theKey;
                decrypted = curDecrypted;
                break;
            }
        }
        
        return decrypted;
    }

    private int[] tryKeyLength(String encrypted, int klength) {
        key = new int[klength];

        // Getting key for every slice
        for (int i = 0; i < klength; ++i) {
            String slice = sliceString(encrypted, i, klength);
            CaesarBreaker breaker = new CaesarBreaker();
            key[i] = breaker.getKey(slice);
        }

        return key;
    }

    private String sliceString(String message, int whichSlice, int numSlices) {
        StringBuilder sb = new StringBuilder();

        // Adding every ith character to the string
        for (int i = whichSlice; i < message.length(); i += numSlices)
            sb.append(message.charAt(i));
        
        return sb.toString();
    }

    private int countWords(String text, HashSet<String> dict) {
        int count = 0;

        // Counting the number of valid words
        for (String word : text.split("\\W+")) {
            if (dict.contains(word.toLowerCase()))
                count++;
        }

        return count;
    }
}
