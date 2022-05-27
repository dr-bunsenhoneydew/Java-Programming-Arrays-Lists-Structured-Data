import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> myMap;
    
    public WordsInFiles() {
        myMap = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        String fName = f.getName();
        
        for (String word : fr.words()) {
            if (!myMap.containsKey(word)) {
                ArrayList<String> newWordList = new ArrayList<String>();
                newWordList.add(fName);
                myMap.put(word, newWordList);
            }
            else if (myMap.containsKey(word) && !myMap.get(word).contains(fName)) {
                myMap.get(word).add(fName);
            }
        }
    }
    
    public void buildWordFileMap() {
        myMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber() {
        int max = 0;
        for (String word : myMap.keySet()) {
            int curr = myMap.get(word).size();
            if (curr > max) {max = curr;}
        }
        return max;
    }
    
    public ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> answer = new ArrayList<String>();
        for (String word : myMap.keySet()) {
            if (myMap.get(word).size() == number) {answer.add(word);}
        }
        return answer;
    }
    
    public void printFilesIn(String word) {
        ArrayList<String> answerString = myMap.get(word);
        for (int i = 0; i < answerString.size(); i++) {
            System.out.println(answerString.get(i));
        }
    }
    
    public void tester() {
        buildWordFileMap();
        int maxNum = maxNumber();
        System.out.println("Max number: " + maxNum);
        ArrayList<String> wordsInFiles = wordsInNumFiles(maxNum);
        System.out.println("Total words in all files: " + wordsInFiles.size());
        wordsInFiles = wordsInNumFiles(3);
        System.out.println("Total words in four files: " + wordsInFiles.size());
        printFilesIn("cats");
    }
}
