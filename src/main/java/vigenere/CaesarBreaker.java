package vigenere;
/**
 * This class uses letter frequency analysis to break the cipher.
 * The most common letter in English is 'e'. Ceasar cipher does not 
 * change the letter frequencies, so the most common letter is an
 * encrypted 'e'. This is how the key is found.
 * 
 * Note that this does not work for every text, epecially short ones
 * 
 * @author dinamuktubayeva
 */

import java.util.HashMap;

public class CaesarBreaker {
    char mostCommon;

    public CaesarBreaker() {
        mostCommon = 'e';
    }

    public CaesarBreaker(char c) {
        mostCommon = c;
    }

    public String decrypt(String encrypted) {
        int key = getKey(encrypted);
        CaesarCipher cc = new CaesarCipher(key);
        return cc.decrypt(encrypted);
    }

    public int getKey(String encrypted) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int maxDex = alphabet.indexOf(mostCommonLetter(encrypted));
        int mostCommonPos = mostCommon - 'a';

        // Key is the distance between the most common letter
        // in the language and the most common letter in the message
        int key = maxDex - mostCommonPos;
        if (maxDex < mostCommonPos)
            key = 26 - (mostCommonPos - maxDex);

        return key;
    }

    public char mostCommonLetter(String message) {
        HashMap<Character, Integer> letters = new HashMap<>();

        // Putting every character into the map
        // If it is already there, increment its count
        for (char c : message.toLowerCase().toCharArray()) {
            if (c != ' ')
                letters.put(c, 1 + letters.getOrDefault(c, 0));
        }

        int maxFreq = 0;
        char mostFreqLetter = 'e';

        // Finding the most common character
        for (char letter : letters.keySet()) {
            if (letters.get(letter) > maxFreq) {
                maxFreq = letters.get(letter);
                mostFreqLetter = letter;
            }
        }
        return mostFreqLetter;
    }
}
