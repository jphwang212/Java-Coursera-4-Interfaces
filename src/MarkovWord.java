import java.util.ArrayList;
import java.util.Random;

public class MarkovWord implements  IMarkovModel{
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
            if(words[i].equals(target)){
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
        WordGram wg = new WordGram(myText, 0, numWords);
        StringBuilder sb = new StringBuilder();
        StringBuilder key = new StringBuilder();
        int index = 0;
        for (int i = 0; i < myOrder; i++) {
            index = myRandom.nextInt(myText.length - myOrder);
            String word = myText[index];
            key.append(word);
            key.append(" ");
        }
        for (int k = 0; k < numWords - myOrder; k++) {
            ArrayList<String> follows = getFollows(wg);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            key.append(next);
            key.append(" ");
            sb.append(key.toString().trim());
            wg.shiftAdd(key.toString().trim());
            key.delete(0, myOrder + 1);
        }

        return sb.toString().trim();
    }
}
