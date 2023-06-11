import java.util.ArrayList;
import java.util.Random;

public class MarkovModel extends AbstractMarkovModel {
    private int orderNum;

    public MarkovModel(int order) {
        orderNum = order;
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        int index = myRandom.nextInt(myText.length() - orderNum);
        String key = myText.substring(index, index + orderNum);
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        for(int k=0; k < numChars - orderNum; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }

        return sb.toString();
    }

    public String toString(){
        return "Markov Model of order " + orderNum + " .";

    }

}
