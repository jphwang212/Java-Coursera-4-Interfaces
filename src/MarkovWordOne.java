
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}

	private int indexOf(String[] words, String target, int start) {
		for(int i = start; i < words.length; i++) {
			if(words[i].equals(target)){
				return i;
			}
		}
		return -1;
	}

	public void testIndexOf() {
		setTraining("this is just a test yes this is a simple test");
		// 1.
		int idx = indexOf(myText, "this", 0);
		System.out.println("#1) " + idx);
		// 2.
		idx = indexOf(myText, "this", 3);
		System.out.println("#2) " + idx);
		// 3.
		idx = indexOf(myText, "frog", 0);
		System.out.println("#3) " + idx);
		// 4.
		idx = indexOf(myText, "frog", 5);
		System.out.println("#4) " + idx);
		// 5.
		idx = indexOf(myText, "simple", 2);
		System.out.println("#5) " + idx);
		// 6.
		idx = indexOf(myText, "test", 5);
		System.out.println("#6) " + idx);
	}

	public String getRandomText (int numWords) {
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-1);  // random word to start with
		String key = myText[index];
		sb.append(key);
		sb.append(" ");
		for (int k=0; k < numWords-1; k++) {
			ArrayList<String> follows = getFollows(key);
			System.out.println(key + ": " + follows);
			if (follows.size() == 0) {
				break;
			}
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key = next;
		}

		return sb.toString().trim();
	}

	private ArrayList<String> getFollows (String key) {
		ArrayList<String> follows = new ArrayList<String>();
		int idx = 0;
		while (idx < myText.length - 1){
			int currIdx = indexOf(myText, key, idx);
			if ((currIdx == -1) || (currIdx == (myText.length - 1))) {
				break;
			}
			follows.add(myText[currIdx + 1]);
			idx = currIdx;
			idx++;
		}
//		for (int i = 0; i < myText.length; i++) {
//			int idx = indexOf(myText, key, i);
//			if (idx == -1 || (idx == myText.length - 1)) {
//				break;
//			}
//			follows.add(myText[idx + 1]);
//		}
		return follows;
    }

	public String toString(){
		return "Markov Word Model of order 1.";
	}

	public static void main(String[] args) {
		MarkovWordOne mw = new MarkovWordOne();
		mw.testIndexOf();
	}

}
