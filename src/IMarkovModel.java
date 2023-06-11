public interface IMarkovModel {
    public void setTraining(String s);
    public String getRandomText(int numChars);
    public void setRandom(int seed);
}
