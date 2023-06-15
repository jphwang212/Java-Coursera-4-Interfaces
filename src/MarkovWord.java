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
        String[] w = new String[myOrder];
        for (int i = 0; i < myOrder; i++) {
            w[i] = target.wordAt(start + i);
        }
        for(int i = start; i < words.length; i++) {
            if(target.equals(w)){
                return i;
            }
        }
        return -1;
    }

    public ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int idx = 0;
        while (idx < myText.length - 1){
            int currIdx = indexOf(myText, kGram, idx);
            if ((currIdx == -1) || (currIdx == (myText.length - 1))) {
                break;
            }
            follows.add(myText[currIdx + 1]);
            idx = currIdx;
            idx++;
        }
        return follows;
    }

    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder);
        String[] keys = new String[myOrder];
        for (int i = 0; i < myOrder; i++) {
            keys[i] = myText[index];
            sb.append(myText[index]);
            sb.append(" ");
            index++;
        }
        WordGram wg = new WordGram(keys, 0, myOrder);

        for (int k = 0; k < numWords - myOrder; k++) {
            ArrayList<String> follows = getFollows(wg);
//            System.out.println(follows);
            if (follows.size() < 1) {
//                wg.shiftAdd(" ");
                continue;
//                break;
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
