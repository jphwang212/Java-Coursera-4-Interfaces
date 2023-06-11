import java.util.ArrayList;
import java.util.Random;

public class MarkovFour extends AbstractMarkovModel {

    public MarkovFour() {}

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        int index = myRandom.nextInt(myText.length() - 4);
        String key = myText.substring(index, index + 4);
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        for(int k=0; k < numChars - 4; k++){
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
        return "Markov Model of order 4.";
    }

}
