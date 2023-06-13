import java.util.ArrayList;
import java.util.Arrays;

public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        // TODO: Complete this method
        return myWords.length;
    }

    public String toString(){
        // TODO: Complete this method
        StringBuilder st = new StringBuilder();
        for (String word : myWords) {
            st.append(word);
            st.append(" ");
        }
        String output = st.toString();
        return output.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        // TODO: Complete this method
        if (other.length() != this.length()) {
            return false;
        }
        for (int i = 0; i < other.length(); i++) {
            if (other.myWords[i].equals(this.myWords[i])) {
                return false;
            }
        }
        return true;

    }

    public WordGram shiftAdd(String word) {	
        WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        // TODO: Complete this method
        String[] shifted = new String[out.myWords.length];
        shifted[shifted.length - 1] = word;
        for (int i = 1; i < shifted.length; i++) {
            shifted[i - 1] = out.wordAt(i);
        }
        out.myWords = shifted;
        return out;
    }

}