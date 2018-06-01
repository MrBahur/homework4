/*
 * didn't really need that one...
 * could have used Node of T when T is a pair <int,String>...
 * but this works to i guess.
 * actually, this is exactly what it is.
 */

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
