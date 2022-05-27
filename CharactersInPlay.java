import edu.duke.*;
import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> names = new ArrayList<String>();
    private ArrayList<Integer> counts = new ArrayList<Integer>();
    
    public void update(String person) {
        person = person.toLowerCase();
        int index = names.indexOf(person);
        if (index == -1) {
            names.add(person);
            counts.add(1);
        }
        else {
            int currCount = counts.get(index);
            counts.set(index,currCount+1);
        }
    }
    
    public void findAllCharacters() {
        names.clear();
        counts.clear();
        FileResource resource = new FileResource();
        for(String line : resource.lines()){
            int periodInd = line.indexOf(".");
            if (periodInd != -1) {
                String name = line.substring(0,periodInd);
                update(name);
            }
        }
    }
    
    public void charactersWithNumParts (int num1, int num2) {
        for (int i = 0; i < counts.size(); i++) {
            if (counts.get(i) >= num1 && counts.get(i) <= num2) {
                System.out.println(names.get(i) + "\t" + counts.get(i));
            }
        }
    }
    
    public int findMax() {
        int maxName = counts.get(0);
        int maxInd = 0;
        for (int i = 0; i < counts.size(); i++) {
            if (counts.get(i) > maxName) {
                maxName = counts.get(i);
                maxInd = i;
            }
        }
        return maxInd;
    }
    
    public void tester() {
        findAllCharacters();
        for (int i = 0; i < names.size(); i++) {
            System.out.println(names.get(i) + "\t" + counts.get(i));
        }
        charactersWithNumParts(10,1000);
        findMax();
    }
}
