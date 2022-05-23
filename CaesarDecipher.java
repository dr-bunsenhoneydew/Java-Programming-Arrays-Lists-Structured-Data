import edu.duke.*;

public class CaesarBreaker {
    public int[] countLetters(String message) {
        int[] counts = new int [26];
        message = message.toLowerCase();
        for (int i = 0; i < 26; i++) {
            counts[i] = 0;
        }
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
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
    
    public String halfOfString(String message, int start) {
        String newString = new String();
        for (int i = start; i < message.length(); i += 2) {
            newString = newString + message.charAt(i);
        }
        return newString;
    }
    
    public int getKey(String s) {
        int[] frequencies = countLetters(s);
        int maxInd = maxIndex(frequencies);
        int key = maxInd - 4;
        if (key < 0) {
            key = 26 - (4 - maxInd);
        }
        return key;
    }
    
    public String decrypt(String message) {
        CaesarCipher cc = new CaesarCipher();
        int[] frequencies = countLetters(message);
        int maxInd = maxIndex(frequencies);
        int key = maxInd - 4;
        if (key < 0) {
            key = 26 - (4 - maxInd);
        }
        return cc.encrypt(message, 26 - key);
    }
    
    public String decryptTwoKeys(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        String firstHalf = halfOfString(encrypted, 0);
        String secondHalf = halfOfString(encrypted, 1);
        int key1 = getKey(firstHalf);
        int key2 = getKey(secondHalf);
        System.out.println("The first key is: " + key1 + ", and the second key is: " + key2);
        
        String newString1 = cc.encrypt(firstHalf, 26 - key1);
        String newString2 = cc.encrypt(secondHalf, 26 - key2);
        int newString1Length = newString1.length();
        int newString2Length = newString2.length();
        String finalString = new String();
        
        if (newString1Length > newString2Length) {
            for (int i = 0; i < newString2Length; i++) {
                finalString = finalString + newString1.substring(i, i+1);
                finalString = finalString + newString2.substring(i, i+1);
            }
            finalString = finalString + newString1.substring(newString1Length-1, newString1Length);
        }
        
        if (newString1Length < newString2Length) {
            for (int i = 0; i < newString1Length; i++) {
                finalString = finalString + newString1.substring(i, i+1);
                finalString = finalString + newString2.substring(i, i+1);
            }
            finalString = finalString + newString2.substring(newString2Length-1, newString2Length);
        }
        
        if (newString1Length == newString2Length) {
            for (int i = 0; i < newString2Length; i++) {
                finalString = finalString + newString1.substring(i, i+1);
                finalString = finalString + newString2.substring(i, i+1);
            }
        }
        return(finalString);
    }
    
    public void testdecryptTwoKeys() {
        CaesarCipher cc = new CaesarCipher();
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        System.out.println(decryptTwoKeys(encrypted));
    }
}
