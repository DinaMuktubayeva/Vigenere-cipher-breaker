package vigenere;

/**
 * Vigenere cipher encrypts every kth letter with a Caesar cipher. This class
 * uses CaesarCipher class to encrypt the message. Caesar cipher can be thought
 * of as an instance of Vigenere, when the key consists of only one number.
 * 
 * @author dinamuktubayeva
 */

public class VigenereCipher {
    int[] key;
    private CaesarCipher[] ciphers;

    public VigenereCipher(int[] theKey) {
        key = theKey;
        ciphers = new CaesarCipher[key.length];
        
        for (int i = 0; i < key.length; i++)
            ciphers[i] = new CaesarCipher(key[i]);
    }

    public String encrypt(String message) {
        return transform(message);
    }

    private String transform(String message) {
        StringBuilder result = new StringBuilder(message);

        for (int i = 0; i < message.length(); ++i) {
            char c = message.charAt(i);
            CaesarCipher thisCipher = ciphers[i % ciphers.length];
            result.setCharAt(i, thisCipher.encryptLetter(c));
        }

        return result.toString();
    }

    public String decrypt(String message) {
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < message.length(); ++i) {
            char c = message.charAt(i);
            CaesarCipher thisCipher = ciphers[i % ciphers.length];
            answer.append(thisCipher.decryptLetter(c));
        }

        return answer.toString();
    }
}