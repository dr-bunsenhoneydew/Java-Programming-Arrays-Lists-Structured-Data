
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class Tester {
    public void testCaesarCipher () {
        CaesarCipher myCC = new CaesarCipher(4);
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println("Original Message: \n" + message);
        String encrypted = myCC.encrypt(message);
        System.out.println("Encrypted Message given key: \n" + encrypted);
        String decrypted = myCC.decrypt(encrypted);
        System.out.println("Decrypted Message given key: \n" + decrypted);
        
    }
    
    public void testCaesarCracker() {
        CaesarCracker myCC = new CaesarCracker('e');
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        System.out.println("Original Encrypted Message: \n"  + encrypted);
        String decrypted = myCC.decrypt(encrypted);
        System.out.println("Decrypted Message aAfter Finding Key: \n" + decrypted);
        int dkey = myCC.getKey(encrypted);
        System.out.println("Key Found: " + dkey);
    }
    
    public void testVigenereCipher() {
        int[]  myVCKey = {17,14,12,4};
        VigenereCipher myVC = new VigenereCipher(myVCKey);
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println("Original Message: \n" + message);
        String encrypted = myVC.encrypt(message);
        System.out.println("Encrypted Message with key: \n" + encrypted);
        
    }
}
