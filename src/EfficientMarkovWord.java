import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<Integer, ArrayList<String>> textFollowing;
    private HashMap<Integer, WordGram> wgMap;

    public EfficientMarkovWord(int order) {
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
        for(int i = start; i < words.length - 1; i++) {
            WordGram wg = new WordGram(myText, i, myOrder);
            if (wg.equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
//        int hashed = kGram.hashCode();
        if (textFollowing.containsKey(kGram.hashCode())) {
            follows = textFollowing.get(kGram.hashCode());
        }
        return follows;
    }

    public String getRandomText(int numWords) {
        buildMap();
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

    // "this is test yes this is really a test"
    public void buildMap() {
        textFollowing = new HashMap<Integer, ArrayList<String>>();
        wgMap = new HashMap<Integer, WordGram>();
        for (int i = 0; i <= myText.length - myOrder; i++) {
            WordGram kGram = new WordGram(myText, i, myOrder);
            int hashed = kGram.hashCode();
            ArrayList<String> following = new ArrayList<String>();
            if (!textFollowing.containsKey(hashed)) {
                wgMap.put(hashed, kGram);
                if (i == myText.length - myOrder) {
                    textFollowing.put(hashed, following);
                    break;
                }
                int currIdx = i;
                while (currIdx < myText.length - myOrder && currIdx != -1) {
                    String next = myText[currIdx + myOrder];
                    following.add(next);
                    currIdx = indexOf(myText, kGram, currIdx + 1);
                }
                textFollowing.put(hashed, following);
            }
        }
    }

    public String toString(){
        return "Efficient Markov Word Model.";
    }

    public void printHashMapInfo() {
        System.out.println("There are " + textFollowing.size() + " keys.");
        int largestSize = 0;
        ArrayList<WordGram> largest = new ArrayList<WordGram>();
        for (Integer hash : textFollowing.keySet()){
            int hashSize = textFollowing.get(hash).size();
            WordGram wg = wgMap.get(hash);
            if (hashSize > largestSize) {
                largestSize = hashSize;
                largest = new ArrayList<>();
                largest.add(wg);
            } else if (hashSize == largestSize) {
                largest.add(wg);
            }
        }
        System.out.println("Highest frequency key: " + largestSize + " " + largest);
        for (WordGram currWg : largest) {
            System.out.println("Values = " + getFollows(currWg));
        }
    }

}
