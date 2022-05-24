
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class TestCaesarCipherTwo {
    public String halfOfString(String message, int start) {
        String newString = new String();
        for (int i = start; i < message.length(); i += 2) {
            newString = newString + message.charAt(i);
        }
        return newString;
    }
    
    public int[] countLetters(String message) {
        int[] counts = new int [26];
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        message = message.toLowerCase();
        for (int i = 0; i < 26; i++) {
            counts[i] = 0;
        }
        for (int i = 0; i < message.length(); i++) {
            int ind = alphabet.indexOf(message.charAt(i));
            if (ind != -1) {
                counts[ind] += 1;
            }
        }
        return counts;
    }
    
    public int maxIndex(int [] counts) {
        int maxCount = 0;
        int maxInd = 0;
        for (int i = 0; i < counts.length; i++) {
            if (maxCount < counts[i]) {
                maxCount = counts[i];
                maxInd = i;
            }
        }
        return maxInd;
    }
    
    public int getKey(String message) {
        int[] frequencies = countLetters(message);
        int maxInd = maxIndex(frequencies);
        int key = maxInd - 4;
        if (maxInd < 4) {
            key = 26 - (4 - maxInd);
        }
        return key;
    }
    
    public String breakCaesarCipher(String input) {
        CaesarCipher cc1 = new CaesarCipher();
        CaesarCipher cc2 = new CaesarCipher();
        String message1 = halfOfString(input, 0);
        String message2 = halfOfString(input, 1);
        int foundKey1 = getKey(message1);
        int foundKey2 = getKey(message2);
        System.out.println("Key 1: " + foundKey1 + "\t Key 2: " + foundKey2);
        CaesarCipherTwo cc3 = new CaesarCipherTwo(foundKey1,foundKey2);
        String answer = cc3.decrypt(input);
        return answer.toString();
    }
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipherTwo cc = new CaesarCipherTwo(17,3);
        String encrypted = cc.encrypt(message);
        System.out.println("Encyrpted message: " + encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decrypted message: " + decrypted);
        
        
        String breakCC = breakCaesarCipher(encrypted);
        System.out.println("Code break answer is: " + breakCC);
    }
}
