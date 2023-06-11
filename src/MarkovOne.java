import java.util.ArrayList;
import java.util.Random;

public class MarkovOne extends AbstractMarkovModel {
    public MarkovOne() {}

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        int index = myRandom.nextInt(myText.length() - 1);
        String key = myText.substring(index, index + 1);
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        for(int k=0; k < numChars - 1; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = next;
        }

        return sb.toString();
    }

    public String toString(){
        return "Markov Model of order 1.";
    }
}
