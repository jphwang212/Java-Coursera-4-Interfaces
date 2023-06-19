
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

import java.io.File;
import java.util.Date;

public class MarkovRunner {
    public void runMarkovZero() {
        MarkovZero mz = new MarkovZero();
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        runModel(mz, st, 100, 1024);
    }
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size);
            printOut(st);
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWordOne markovWord = new MarkovWordOne(); 
        runModel(markovWord, st, 120, 139);
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

    public void testGetRandomText () {
        FileResource fr = new FileResource();
        String st = fr.asString();
        MarkovWordOne mw = new MarkovWordOne();
        String testSt = "this is just a test yes this is a simple test";
        runModel(mw, st, 120, 175);
    }

    public void runMarkovTwo() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovWordTwo mw = new MarkovWordTwo();
        runModel(mw, st, 120, 832);
    }

    public void testMarkovWord() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovWord mw = new MarkovWord(5);
        runModel(mw, st, 120, 844);
    }

    public void testHashMap() {
        FileResource fr = new FileResource();
        String testSt = fr.asString();

//        String testSt = "this is a test yes this is really a test yes a test this is wow";
        testSt = testSt.replace('\n', ' ');
        EfficientMarkovWord emw = new EfficientMarkovWord(5);
        emw.setRandom(531);
        emw.setTraining(testSt);
        emw.buildMap();
        emw.printHashMapInfo();
//        runModel(emw, testSt, 50, 42);
    }

    public void compareMethods() {
        FileResource fr = new FileResource();
        String trainingText = fr.asString();
        trainingText = trainingText.replace('\n', ' ');
        MarkovWord mw = new MarkovWord(2);
        EfficientMarkovWord emw = new EfficientMarkovWord(2);
        long startTime = System.nanoTime();
        runModel(mw, trainingText, 100, 42);
        long elapsed = System.nanoTime() - startTime;
        System.out.println("MW: " + elapsed);
        startTime = System.nanoTime();
        runModel(emw, trainingText, 100, 42);
        elapsed = System.nanoTime() - startTime;
        System.out.println("Efficient MW: " + elapsed);
    }

    public static void main(String[] args) {
        MarkovRunner mr = new MarkovRunner();
//        mr.runMarkovZero();
//        mr.testGetRandomText();
//        mr.runMarkovTwo();
//        mr.runMarkov();
//        mr.testMarkovWord();
        mr.testHashMap();
//        mr.compareMethods();
    }

}
/**
 * agreement violates the law of the state applicable to this agreement,
 * ministers, and there was order below heaven. King Wu[80] said,
 *
 * Each
 * eeuefmespwhsfoyu, s giowhersa eell; bma s.7shni:.at.ttdr.w aknf
 * 10453
 * 3715
 *  y O wirs bloay Ger. fo. tifthy The, A My; st- ie d, s. bloulate,
 *  man in a green, for that haste, for a foot in her from Tybalt!
 *  e uncle Capulet's orchard. Enter an officer, and light- more
 *  33972, 45252
 *  18 1549
 */