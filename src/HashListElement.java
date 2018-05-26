public class HashListElement {
    private String word;
    private int numberOfInstances;

    //Constructors
    public HashListElement(String word){
        this.word=word;
        this.numberOfInstances=1;
    }

    //Getters

    public String getWord() {
        return word;
    }

    public int getNumberOfInstances() {
        return numberOfInstances;
    }
    //Setters

    public void setWord(String word) {
        this.word = word;
    }

    public void setNumberOfInstances(int numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }
}
