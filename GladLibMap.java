import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private ArrayList<String> usedWordList;
    private int usedWordCount = 0;
    private HashMap<String,ArrayList<String>> myMap = new HashMap<String, ArrayList<String>>();
    private Random myRandom;
    private ArrayList<String> usedCategories;
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "datalong";
    
    public GladLibMap() {
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        usedWordList = new ArrayList<String>();
        usedCategories = new ArrayList<String>();
    }
    
    public GladLibMap(String source) {
        initializeFromSource(source);
        myRandom = new Random();
        usedWordList = new ArrayList<String>();
        usedCategories = new ArrayList<String>();
    }
    
    public void initializeFromSource(String source) {
        String[] labels = {"adjective","animal","color","country","fruit","name","noun","timeframe","verb"};
        for(String label : labels){
            ArrayList<String> valueList = readIt(source+"/"+ label +".txt");
            myMap.put(label, valueList);
        }
    }
    
    public String randomFrom(ArrayList<String> source) {
          int index = myRandom.nextInt(source.size());
          return source.get(index);
        
    }
    
    public String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        if (usedCategories.indexOf(label) == -1) {
            usedCategories.add(label);
        }
        return randomFrom(myMap.get(label));
    }
    
    public String processWord(String w) {
        int first = w.indexOf("<");
        int last = w.indexOf(">", first);
        if (first == -1 || last == -1) {
            return w;
        }
        String prefix = w.substring(0, first);
        String suffix = w.substring(last + 1);
        String sub = getSubstitute(w.substring(first + 1, last));
        boolean wordAlreadyUsed = checkIfUsed(sub);
        while (wordAlreadyUsed) {
            sub = getSubstitute(w.substring(first + 1, last));
            wordAlreadyUsed = checkIfUsed(sub);
        }
        usedWordCount += 1;
        return prefix + sub + suffix;
    } 
     
    public boolean checkIfUsed(String word) {
        int index = usedWordList.indexOf(word);
        if (index == -1) {
            usedWordList.add(word);
            return false;
        } else {
            return true;
        }
    }
              
    public int totalWordsInMap() {
       int totalWordSum = 0;      
       for (String category : myMap.keySet()) {
           ArrayList<String> totalWords = myMap.get(category);
           System.out.println("Category:" +category+ "\tTotal words in category:"
            + totalWords.size());
           totalWordSum += totalWords.size();
        }
       System.out.println("Total word sum: " + totalWordSum);
       return totalWordSum;
       
    }
    
    public int totalWordsConsidered() {
        ArrayList<String> wordsInCat = new ArrayList<String>();
        int sum = 0;
        System.out.println("\nCategories used in this story:");
        for (int i = 0; i < usedCategories.size(); i++) {
            String category = usedCategories.get(i);
            wordsInCat = myMap.get(category);
            System.out.println("Category: " + category + "\t Words in category: " + wordsInCat.size());
            sum += wordsInCat.size();
        }
        System.out.println("Number of words considered: " + sum);
        return sum;
    }
    
    public void printOut(String s, int lineWidth) {
        int charsWritten = 0;
        for (String w : s.split("\\s+")) {
            if (charsWritten + w.length() > lineWidth) {
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w + " ");
            charsWritten += w.length() + 1;
        }
    }
    
    public String fromTemplate(String source) {
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    public ArrayList<String> readIt(String source) {
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()) {
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()) {
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory() {
        usedWordList.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        usedWordCount = 0;
        System.out.println("\n Total Words In Map: " + totalWordsInMap());
        System.out.println("\n Total Words Considered: " + totalWordsConsidered());
    }

}
