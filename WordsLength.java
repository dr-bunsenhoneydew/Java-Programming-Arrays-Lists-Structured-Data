import java.lang.*;
import edu.duke.*;

public class WordLengths {
    public void countWordLengths(FileResource resource, int counts[]) {
        for(String word : resource.words()){
            int wordLength = word.length();
            for (int i = 0; i < word.length(); i++) {
                char currChar = word.charAt(i);
                if ((i==0 || i==word.length()-1) && (!Character.isLetter(currChar))) {wordLength -=1;}
                    if (!Character.isLetter(currChar)) {}
                }
            if (wordLength > counts.length) {counts[-1] += 1;}    
            else {counts[wordLength] += 1;}
            
            System.out.println(counts[wordLength] + " Words of length " + wordLength + " " + word);
	    }
    }   
    
    public void indexOfMax(int[] values) {
        int max = 0;
        int ind = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > max) {
                ind = i;
                max = values[i];
            }
        }
        System.out.println("The most common word is: " + ind);
    }    
    
    public void testCountWordLength() {
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        indexOfMax(counts);
    }
}
