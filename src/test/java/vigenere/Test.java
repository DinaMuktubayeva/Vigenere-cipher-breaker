package vigenere;

import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class Test {    
    public static void main(String[] args) {
        testVigenereWithUnknownLength();
    }

    public static void testCaesar(){
        try{
            CaesarCipher cc = new CaesarCipher(14);
            String message = new String(Files.readAllBytes(Paths.get("src/main/resources/Message4.txt")));
            String encrypted = cc.encrypt(message);
            CaesarBreaker cb = new CaesarBreaker();
            String decrypted = cb.decrypt(encrypted);

            System.out.println(encrypted);
            System.out.println("\n");
            System.out.println(decrypted);

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void testVigenereWithKnownLength() {
        try {
            int[] k = {1, 2};
            VigenereCipher cc = new VigenereCipher(k);
            String message = new String(Files.readAllBytes(Paths.get("src/main/resources/Message4.txt")));
            String encrypted = cc.encrypt(message);
            VigenereBreaker vb = new VigenereBreaker();

            int klength = k.length;
            String decrypted = vb.breakWithKnownLength(encrypted, klength);

            System.out.println(encrypted);
            System.out.println("\n");
            System.out.println(decrypted);

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void testVigenereWithUnknownLength(){
        try {
            String engDict = new String(Files.readAllBytes(Paths.get("src/main/resources/English")));
            HashSet<String> dictionary = new HashSet<String>(Arrays.asList(engDict.toLowerCase().split("\\W+")));
            
            int[] k = {12, 20, 4};
            VigenereCipher vc = new VigenereCipher(k);
            String message = new String(Files.readAllBytes(Paths.get("src/main/resources/Message4.txt")));
            String encrypted = vc.encrypt(message);
            VigenereBreaker vb = new VigenereBreaker();
            String decrypted = vb.breakWithUnknownLength(encrypted, dictionary);

            System.out.println(encrypted);
            System.out.println("\n");
            System.out.println(decrypted);

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
