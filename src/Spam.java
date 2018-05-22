public class Spam {

    private String word;
    private float ratio;

    //Constructors:

    private Spam(String word, float ratio) {
        if (ratio<0.0|ratio>100.0)
            throw new IllegalArgumentException();
        this.word=word;
        this.ratio=ratio;
    }

    public Spam(){
        this("",0);
    }
    //Getters:

    public float getRatio() {
        return ratio;
    }

    public String getWord() {
        return word;
    }

    //Setters:

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    public void setWord(String word) {
        this.word = word;
    }

    //Methods:

}
