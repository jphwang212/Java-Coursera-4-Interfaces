import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;

    public AbstractMarkovModel(){
        myRandom = new Random();
    }

    // State and shared method
    abstract public String getRandomText(int numChars);

    public void setTraining(String s) {
        myText = s.trim();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    protected ArrayList<String> getFollows(String key){
        ArrayList<String> following = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length()){
            int start = myText.indexOf(key, pos);
            if(start == -1){
                break;
            }
            if(start + key.length() >= myText.length() - 1){
                break;
            }
            String next = myText.substring(start + key.length(), start + key.length() + 1);
            following.add(next);
            pos = start + key.length();
        }
        return following;
    }
}
