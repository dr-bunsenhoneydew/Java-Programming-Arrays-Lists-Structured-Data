
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int firstKey;
    private int secondKey;
    
    public CaesarCipherTwo(int key1, int key2){
         alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
         shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
         shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
         firstKey = key1;
         secondKey = key2;
    }
    
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet1 = alphabet.substring(firstKey) + alphabet.substring(0,firstKey);
        String shiftedAlphabet2 = alphabet.substring(secondKey) + alphabet.substring(0,secondKey);
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
    
    public String decrypt(String input) {
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - firstKey, 26 - secondKey);
        String decrypt = cc.encrypt(input);
        return decrypt;
    }
    
    public void testingMethods() {
        CaesarCipherTwo cc = new CaesarCipherTwo(12,5);
        String message = "Hello world, this is your commander speaking.";
        String encrypted = cc.encrypt(message);
        System.out.println("Encyrpted message: " + encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decrypted message: " + decrypted);
    }    
}
