import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int orderNum;
    private HashMap<String, ArrayList<String>> markovMap;

    public EfficientMarkovModel(int order) {
        orderNum = order;
    }

    public void buildMap(){
        markovMap = new HashMap<String, ArrayList<String>>();
        for(int i = 0; i < (myText.length() - orderNum); i++){
            String key = myText.substring(i, i + orderNum);
            String next = myText.substring(i + orderNum, i + orderNum + 1);
            ArrayList<String> following = new ArrayList<String>();
            following.add(next);
            if(!markovMap.containsKey(key)){
                markovMap.put(key, following);
            } else {
                ArrayList<String> newFollowing = markovMap.get(key);
                newFollowing.add(next);
                markovMap.put(key, newFollowing);
            }
        }
        String endSubString = myText.substring(myText.length() - orderNum);
        if(!markovMap.containsKey(endSubString)){
            markovMap.put(endSubString, new ArrayList<String>());
        }
    }

    public ArrayList<String> getFollows(String key){
        ArrayList<String> answer = markovMap.get(key);
        return answer;
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        buildMap();
        printHashMapInfo();
        int index = 0;
        String key = myText.substring(index, index + orderNum);
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        for (int k = 0; k < numChars; k++) {
            if (markovMap.containsKey(key)) {
                ArrayList<String> follows = getFollows(key);
                if(follows.size() < 1){
                    sb.append(" ");
                    key = key.substring(1) + " ";
                    continue;
                }
                index = myRandom.nextInt(follows.size());
                String next = follows.get(index);
                sb.append(next);
                key = key.substring(1) + next;
            } else break;
        }
        return sb.toString();
    }

    public void printHashMapInfo(){
        System.out.println("Markov map has " + markovMap.keySet().size() + " keys.");
        System.out.println("Markov Map");
        int maxSize = 0;
        ArrayList<String> maxKeys = new ArrayList<String>();
        for(String key : markovMap.keySet()){
            int currSize = markovMap.get(key).size();
            if (currSize > maxSize){
                maxSize = currSize;
                maxKeys.clear();
                maxKeys.add(key);
            } else if (currSize == maxSize && !maxKeys.contains(key)){
                System.out.println("Printing key: " + key);
                maxKeys.add(key);
            }
        }
        System.out.println("Key with max size of [" + maxSize + "]: " + maxKeys);
    }

    public String toString(){
        return "EFFICIENT Markov Model of order " + orderNum + ".";
    }
}
