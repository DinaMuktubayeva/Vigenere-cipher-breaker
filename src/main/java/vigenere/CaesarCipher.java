package vigenere;

/**
 * In Caesar cipher, every letter is encrypted according to the corresponding
 * position in the alphabet. In other words, the alphabet is shifted, and the
 * new letters on the same positions replace the old ones in the message. The
 * number of letters by which the alphabet has been shifted is the key]
 * 
 * @author dinamuktubayeva
 */

public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int key;

    public CaesarCipher(int theKey) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        key = theKey;
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        alphabet += alphabet.toLowerCase();
        shiftedAlphabet += shiftedAlphabet.toLowerCase();
    }

    public String encrypt(String message) {
        return transform(message, alphabet, shiftedAlphabet);
    }

    public String decrypt(String message) {
        return transform(message, shiftedAlphabet, alphabet);
    }

    private String transform(String message, String from, String to) {
        StringBuilder sb = new StringBuilder(message);

        // Encrypting letter by letter
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            c = transformLetter(c, from, to);
            sb.setCharAt(i, c);
        }

        return sb.toString();
    }

    public char encryptLetter(char c) {
        return transformLetter(c, alphabet, shiftedAlphabet);
    }

    public char decryptLetter(char c) {
        return transformLetter(c, shiftedAlphabet, alphabet);
    }

    private char transformLetter(char c, String from, String to) {
        // Position in the alphabet
        int index = from.indexOf(c);
        
        if (index != -1)
            // The letter is replaced by the one at the same position
            // in the shifted alphabet
            return to.charAt(index);
        
        return c;
    }
}
