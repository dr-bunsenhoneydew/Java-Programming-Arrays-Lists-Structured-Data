
/**
 * Write a description of HashMapDNA here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class HashMapDNA {
    private HashMap<String, Integer> myMap = new HashMap<String, Integer>();
    
    public HashMapDNA() {
        myMap = new HashMap<String, Integer>();
    }
    
    public void buildCodonMap(int start, String dna) {
        myMap.clear();
        for (int i = start; dna.length() - i > 3; i += 3) {
            String currCodon = dna.substring(i, i + 3);
            if (!myMap.containsKey(currCodon)) {
                myMap.put(currCodon, 1);
            }
            else {
                myMap.put(currCodon, myMap.get(currCodon) + 1);
            }
        }
    }
    
    public String getMostCommonCodon() {
        int current = 0;
        int max = 0;
        String maxCodon = "";
        for (String codon : myMap.keySet()) {
           current = myMap.get(codon);
           if (current > max) {
               maxCodon = codon;
               max = current;
           }
        }
        return maxCodon;
    }
    
    public void printCodonCounts(int start, int end) {
        for (String codon : myMap.keySet()) {
            if (myMap.get(codon) >= start && myMap.get(codon) <= end) {
                System.out.println(myMap.get(codon) + ": Codon " + codon);
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        dna = dna.toUpperCase();
        for (int i = 0; i < 3; i++) {
            buildCodonMap(i, dna);
            System.out.println("Total Codons: " + myMap.size());
            
            System.out.println("Most Common Codon: " + getMostCommonCodon() + "\t found " + myMap.get(getMostCommonCodon()));
            printCodonCounts(1, 5);
        }
    }
}
