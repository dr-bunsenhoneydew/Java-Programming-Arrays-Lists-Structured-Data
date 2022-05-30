import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreakerFinal {
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
        return key;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
        for (String s : dictionary) {
            String sLower = s.toLowerCase();
            for (char c : sLower.toCharArray()) {
                if (!charMap.containsKey(c)) {
                    charMap.put(c, 1);
                }
                else {
                    charMap.put(c, charMap.get(c) + 1);
                }
            }
        }
        int maxCharCount = 0;
        for (char c : charMap.keySet()) {
            if (charMap.get(c) > maxCharCount) {
                maxCharCount = charMap.get(c);
            }
        }
        char mostCommonChar = Character.MIN_VALUE;
        for (char c : charMap.keySet()) {
            if (charMap.get(c) == maxCharCount) {
                mostCommonChar = c;
            }
        }
        return mostCommonChar;
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
        char c = mostCommonCharIn(dictionary);
        for(int k =1; k < 100 ; k++) {
            key = tryKeyLength(encrypted,  k, c);
            VigenereCipher myVC  = new VigenereCipher(key) ;
            decryptedString = myVC.decrypt(encrypted);
            int counts = countWords(decryptedString, dictionary);
            if(counts > maxWordCount){
                maxWordCount = counts;
                bestAnswerString = decryptedString;
                maxKeyLength = k;
            } 
        }
        return bestAnswerString;
    }
    
    public void breakForAllLanguages(String encrypted, HashMap<String, HashSet<String>> languages) {
        int maxLanguageCount = 0;
        for (String language : languages.keySet()) {
            String thisLangString = breakForLanguage(encrypted, languages.get(language));
            int thisLangCount = countWords(thisLangString, languages.get(language));
            if (thisLangCount > maxLanguageCount) {
                maxLanguageCount = thisLangCount;
            }
        }
        for (String language : languages.keySet()) {
            String thisLangString = breakForLanguage(encrypted, languages.get(language));
            int thisLangCount = countWords(thisLangString, languages.get(language));
            if (thisLangCount == maxLanguageCount) {
                System.out.println(thisLangString + "\n" + language);
            }
        }
    }
    
    public void breakVigenere() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        int[] answerKey = tryKeyLength(encrypted, 4, 'e');
        VigenereCipher myVC = new VigenereCipher(answerKey);
        String decrypted = myVC.decrypt(encrypted);
        System.out.println("Decrypted Message: \n" + decrypted);
    }
    
    public void breakVigenereEnglish() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        FileResource dictFR = new FileResource("dictionaries/English");
        HashSet<String> dictHash = getDictionarySet(dictFR);
        String answerString = breakForLanguage(encrypted, dictHash);
        System.out.println(answerString);
    }
    
    public void breakVigenereUnknownLang() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        DirectoryResource dr = new DirectoryResource();
        HashMap<String, HashSet<String>> myMap = new HashMap<String, HashSet<String>>();
        for (File f : dr.selectedFiles()) {
            FileResource frLoop = new FileResource(f);
            myMap.put(f.getName(), getDictionarySet(frLoop));
        }
        breakForAllLanguages(message, myMap);
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

