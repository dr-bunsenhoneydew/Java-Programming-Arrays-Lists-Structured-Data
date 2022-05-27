public class WordPlay {
    
    
    public boolean isVowel(char ch) {
        String vowels = "aeiou";
        if (vowels.indexOf(ch) != -1) {
            return true;
        }
        else {return false;}
    }
    
    public StringBuilder replaceVowels(String phrase, char ch) {
        String vowels = "aeiou";
        StringBuilder sb = new StringBuilder(phrase);
        for (int i = 0; i < sb.length(); i++) {
            char currChar = sb.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = vowels.indexOf(currChar);
            //If currChar is in the alphabet
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = ch;
                //Replace the ith character of encrypted with newChar
                sb.setCharAt(i, newChar);
            }
        }
        return sb;
    }
    
    public StringBuilder emphasize(String phrase, char ch) {
        StringBuilder sb = new StringBuilder(phrase);
        for (int i = 0; i < sb.length(); i++) {
            char currChar = sb.charAt(i);
            if (Character.toLowerCase(currChar)==Character.toLowerCase(ch) && i % 2 == 1){
                sb.setCharAt(i, '+');
            }    
            else if (Character.toLowerCase(currChar)==Character.toLowerCase(ch) && i % 2 == 0) {
                sb.setCharAt(i, '*');
            }
            else {continue;}
        }    
        return sb;
    }
    
    
    public void testEmphasize() {
        System.out.println(emphasize("dna ctgaaactga",'a'));
        System.out.println(emphasize("Mary Bella Abracadabra",'a'));
    }    
    
    public void testReplaceVowels() {
        System.out.println(replaceVowels("Hello World",'*'));
    }    
    
    public void testIsVowel() {
        System.out.println(isVowel('F'));
        System.out.println(isVowel('a'));
    }
}
