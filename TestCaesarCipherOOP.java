import edu.duke.*;

public class TestCaesarCipherOOP {
    
    private int[] countLetters(String message) {
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
    
    public void breakCaesarCipher(String input) {
        int[] frequencies = countLetters(input);
        int maxInd = maxIndex(frequencies);
        int key = maxInd - 4;
        if (key < 0) {
            key = 26 - (4 - maxInd);
        }
        CaesarCipherOOP newCC = new CaesarCipherOOP(key);
        System.out.println("Decrypted using breakCaesarCipher: " + newCC.decrypt(input));
    }
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        CaesarCipherOOP ccTest = new CaesarCipherOOP(18);
        String message = fr.asString();
        String encrypted = ccTest.encrypt(message);
        System.out.println("Encrypted message: " + encrypted);
        String decryptedMessage = ccTest.decrypt(encrypted);
        System.out.println("Decrypted message: " + decryptedMessage);
        breakCaesarCipher(encrypted);
        
        
    }
    
    public void finalTests() {
        CaesarCipherOOP cc1 = new CaesarCipherOOP(15);
        String encrypted = cc1.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?");
        System.out.println("Answer 1: " + encrypted);
        //Returns: Rpc ndj xbpvxct axut LXIWDJI iwt xcitgcti PCS rdbejitgh xc ndjg edrzti?
        
        
    }
}
