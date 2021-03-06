import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key)+
        alphabet.substring(0,key);
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            if (Character.isLowerCase(currChar) && idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);
                newChar = Character.toLowerCase(newChar);
                encrypted.setCharAt(i, newChar);
            }    
            else if (Character.isUpperCase(currChar) && idx != -1){
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }    
        }
        return encrypted.toString();
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            char newChar;
            if (Character.isLowerCase(currChar) && idx != -1) {
                if (i % 2 == 0) {
                    newChar = shiftedAlphabet1.charAt(idx);
                }
                else {
                    newChar = shiftedAlphabet2.charAt(idx);
                }    
                newChar = Character.toLowerCase(newChar);
                encrypted.setCharAt(i, newChar);
            }    

            else if (Character.isUpperCase(currChar) && idx != -1){
                if (i % 2 == 0) {
                    newChar = shiftedAlphabet1.charAt(idx);
                }    
                else {
                    newChar = shiftedAlphabet2.charAt(idx);
                }
                encrypted.setCharAt(i, newChar);
            }    
        }
        return encrypted.toString();
    }
    
    
    public void testEncryptTwoKeys() {
        String encryptedTwoKeys = encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21);
        System.out.println(encryptedTwoKeys);
    }
    
    public void testCaesar() {
        int key = 17;
        String encrypted = encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!",15);
        System.out.println(encrypted);
        /*FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);*/
    }
}

