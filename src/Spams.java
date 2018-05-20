import java.util.Iterator;

public class Spams implements Iterable<Spam> {

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
