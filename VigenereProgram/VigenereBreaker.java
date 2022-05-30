import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            char currChar = message.charAt(i);
            sb.append(currChar);
        }  
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for (int k = 0; k < klength; k++) {
            String slice = sliceString(encrypted, k, klength);
            CaesarCracker myCC = new CaesarCracker(mostCommon);
            int kkey = myCC.getKey(slice);
            key[k] = kkey;
        }
        System.out.println(key.toString());
        return key;
    }

    public HashSet<String> getDictionarySet(FileResource fr) {
        HashSet<String> myDictionary = new HashSet<String>();
        for (String line : fr.lines()) {
            myDictionary.add(line.toLowerCase());
        }
        return myDictionary;
    }
    
    public int countWords(String message, HashSet<String> dictionary)  {
        int count = 0;
        ArrayList<String> messageString = new ArrayList<String>(Arrays.asList(message.split("\\W")));
        for (int i = 0; i < messageString.size(); i++) {
            if (dictionary.contains(messageString.get(i).toLowerCase())) {
                count += 1;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int maxWordCount = 0;
        int[] key = new int[100];
        int maxKeyLength = 0;
        String decryptedString = new String();
        String bestAnswerString = new String();
        for(int k =1; k < 100 ; k++) {
            key = tryKeyLength(encrypted,  k, 'e');
            VigenereCipher myVC  = new VigenereCipher(key) ;
            decryptedString = myVC.decrypt(encrypted);
            int counts = countWords(decryptedString, dictionary);
            if(counts > maxWordCount){
                maxWordCount = counts;
                bestAnswerString = decryptedString;
                maxKeyLength = k;
            } 
        }
        System.out.println("Max Word Count: " + maxWordCount);        
        System.out.println("Key Length: "+ maxKeyLength); 
        
        return bestAnswerString;
    }
    
    public void breakVigenere() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        int[] answerKey = tryKeyLength(encrypted, 4, 'e');
        VigenereCipher myVC = new VigenereCipher(answerKey);
        String decrypted = myVC.decrypt(encrypted);
        System.out.println("Decrypted Message: \n" + decrypted);
    }

    public void testSliceString() {
        String answer1 = sliceString("abcdefghijklm",0,3);
        System.out.println(answer1);
    }
    
    public void testTryKeyLength() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        int[] answerKey = tryKeyLength(encrypted, 4, 'e');
        String answerKeyStringArray = Arrays.toString(answerKey);
        System.out.println(answerKeyStringArray);
    }
}
