import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MarkovWord implements  IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;

    public MarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text) {
        myText = text.split("\\s+");
    }

    public int indexOf(String[] words, WordGram target, int start) {
        for(int i = start; i < words.length; i++) {
            if (i >= words.length - myOrder) {
                break;
            }
            WordGram wg = new WordGram(myText, i, myOrder);
            if (wg.equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int idx = 0;
        while (idx < myText.length){
            int currIdx = indexOf(myText, kGram, idx);
            if ((currIdx == -1) || (currIdx == (myText.length - 1))) {
                break;
            }
            String next = myText[currIdx + myOrder];
            follows.add(next);
            idx = currIdx + myOrder;
        }
        return follows;
    }

    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder);
        WordGram wg = new WordGram(myText, index, myOrder);
        sb.append(wg);
        sb.append(" ");

        for (int k = 0; k < numWords - myOrder; k++) {
            ArrayList<String> follows = getFollows(wg);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            wg = wg.shiftAdd(next);
            sb.append(next);
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    public String toString(){
        return "Markov Word Model.";
    }
}
