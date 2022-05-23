
/**
 * Write a description of CaesarCipherOOP here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherOOP {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipherOOP(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }
    
    public String encrypt(String input) {
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            int idx = alphabet.indexOf(c);
            if (idx != -1) {
                c = shiftedAlphabet.charAt(idx);
                sb.setCharAt(i,c);
            }
        }
        return sb.toString();
    }
    
    public String decrypt(String input) {
        CaesarCipherOOP cc = new CaesarCipherOOP(26 - mainKey);
        return cc.encrypt(input);
    }

}
