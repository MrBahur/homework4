import java.util.Iterator;
import java.util.NoSuchElementException;

public class Messages implements Iterable<Message> {

    private DoublyLinkedList<Message> data;
    private Node<Message> curr;

    public Messages()
    {
        this.data = new DoublyLinkedList<>();
        this.curr=null;
    }

    //Getters:

    public Node<Message> getCurr() {
        return curr;
    }

    public DoublyLinkedList<Message> getData() {
        return data;
    }
    //Setters:

    public void setCurr(Node<Message> curr) {
        this.curr = curr;
    }

    //Iterator:

    @Override
    public Iterator<Message> iterator(){
        setCurr(getData().getHead());
        return new Iterator<Message>() {
            @Override
            public boolean hasNext() {
                return getCurr()!=null;
            }
            @Override
            public Message next() {
                if(!hasNext())
                    throw new NoSuchElementException();
                Node<Message> aux = getCurr();
                setCurr(aux.getNext());
                return (aux.getData());
            }
        };
    }


    //methods

    public String findSpams(String locaition, BTree friends)
    {

        return null;
    }
    public void createHashTables(String M){
        int m = Integer.parseInt(M);

    }

    public void generateMessages(String location){


    }

    private void Insert(Message toInsert){
        getData().addLast(toInsert);
    }
}
