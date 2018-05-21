import java.util.Iterator;

public class Spams implements Iterable<Spam> {

    private Spam[] data;


    //Constructors:
    public Spams(){
        this.data=null;
    }

    //Getters:

    public Spam[] getData() {
        return data;
    }

    //Setters:

    public void setData(Spam[] data) {
        this.data = data;
    }

    @Override
    public Iterator<Spam> iterator() {
        return new Iterator<Spam>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Spam next() {
                return null;
            }
        };
    }
}
