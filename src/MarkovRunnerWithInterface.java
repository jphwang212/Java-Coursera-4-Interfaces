
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
	public void testHashMap(){
//		String st = "yes-this-is-a-thin-pretty-pink-thistle";
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		EfficientMarkovModel eMM = new EfficientMarkovModel(5);
		runModel(eMM, st, 1000, 615);
//		eMM.printHashMapInfo();
	}
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
		markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k = 0; k < 3; k++){
			String st = markov.getRandomText(size);
			printOut(st);
		}
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, 42);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, 42);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, 42);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, 42);

    }

	public void compareMethods(){
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovModel mm = new MarkovModel(2);
		EfficientMarkovModel eMM = new EfficientMarkovModel(2);
		int size = 1000;
		long startTime = System.nanoTime();
		runModel(mm, st, size, 42);
		long timeElapsed = System.nanoTime() - startTime;
		System.out.println("Markov Model time = " + timeElapsed);
		startTime = System.nanoTime();
		runModel(eMM, st, size, 42);
		timeElapsed = System.nanoTime() - startTime;
		System.out.println("Efficient Markov Model time = " + timeElapsed);
	}

	private void printOut(String s){
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}

	public static void main(String[] args) {
		MarkovRunnerWithInterface inst = new MarkovRunnerWithInterface();
//		inst.runMarkov();
		inst.testHashMap();
//		inst.compareMethods();
	}

}
// 851804542
// 125395209