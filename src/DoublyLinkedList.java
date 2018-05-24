import java.util.NoSuchElementException;

public class DoublyLinkedList<T> {

    private Node<T> head;
    private Node<T> last;
    private int size;

    //Constructors
    public DoublyLinkedList(){
        this.head=null;
        this.last=null;
        this.size=0;
    }

    //getters:

    public Node<T> getHead() {
        return head;
    }

    public int getSize() {
        return size;
    }

    public Node<T> getLast() {
        return last;
    }

    //setters:


    public void setHead(Node<T> head) {
        this.head = head;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setLast(Node<T> last) {
        this.last = last;
    }

    //methods:

    public void addFirst(T toInsert) {
        if(toInsert==null){
            throw new NullPointerException();
        }
        Node<T> aux = new Node<>(null,null,toInsert);
        if(getSize()==0)
            addFirstForAddLast(aux);
        else if(getSize()==1)
            addSecondForAddFirst(aux);
        else
            addElseForAddFirst(aux);
    }
    private void addSecondForAddFirst(Node<T> toInsert){
        toInsert.setNext(getHead());
        getHead().setPrev(toInsert);
        setHead(toInsert);
        setSize(2);
    }
    private void addElseForAddFirst(Node<T> toInsert){
        toInsert.setNext(getHead());
        getHead().setPrev(toInsert);
        setHead(toInsert);
        setSize(getSize()+1);
    }
    public void addLast(T toInsert) {
        if(toInsert==null)
            throw new NullPointerException();
        Node<T> aux = new Node<>(null,null,toInsert);
        if(getSize()==0)
            addFirstForAddLast(aux);
        else if(getSize()==1)
           addSecondForAddLast(aux);
        else
            addElseForAddLast(aux);
    }
    private void addFirstForAddLast(Node<T> aux){
        setHead(aux);
        setLast(aux);
        setSize(1);
    }
    private void addSecondForAddLast(Node<T> aux){
        getHead().setNext(aux);
        setLast(aux);
        aux.setPrev(getHead());
        setSize(2);
    }
    private void addElseForAddLast(Node<T> aux){
        Node<T> tmp = getLast();
        tmp.setNext(aux);
        setLast(aux);
        getLast().setPrev(tmp);
        setSize(getSize()+1);
    }

    public T removeLast(){
        if(getSize()==0)
            throw new NoSuchElementException();
        Node<T> aux = getLast();
        setLast(aux.getPrev());
        getLast().setNext(null);
        aux.setPrev(null);
        return aux.getData();
    }
}

