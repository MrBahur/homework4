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

    /**
     * add a Node to the head of the list
     * @param toInsert the T you want to enter
     */
    public void addFirst(T toInsert) {
        if(toInsert==null){
            throw new NullPointerException();
        }
        Node<T> aux = new Node<>(null,null,toInsert);
        if(getSize()==0)
            addFirstForAddLast(aux);        //case 1
        else if(getSize()==1)
            addSecondForAddFirst(aux);      //case 2
        else
            addElseForAddFirst(aux);        //case 3
    }
    private void addSecondForAddFirst(Node<T> toInsert){    //case 2
        toInsert.setNext(getHead());
        getHead().setPrev(toInsert);
        setHead(toInsert);
        setSize(2);
    }
    private void addElseForAddFirst(Node<T> toInsert){      //case 3
        toInsert.setNext(getHead());
        getHead().setPrev(toInsert);
        setHead(toInsert);
        setSize(getSize()+1);
    }

    /**
     * add a Node to the end of the list
     * @param toInsert the T you want to enter
     */
    public void addLast(T toInsert) {
        if(toInsert==null)
            throw new NullPointerException();
        Node<T> aux = new Node<>(null,null,toInsert);
        if(getSize()==0)
            addFirstForAddLast(aux);    //case 1
        else if(getSize()==1)
           addSecondForAddLast(aux);    //case 2
        else
            addElseForAddLast(aux);     //case 3
    }
    private void addFirstForAddLast(Node<T> aux){   // case 1 (both for addFirst and addLast)
        setHead(aux);
        setLast(aux);
        setSize(1);
    }
    private void addSecondForAddLast(Node<T> aux){  //case 2
        getHead().setNext(aux);
        setLast(aux);
        aux.setPrev(getHead());
        setSize(2);
    }
    private void addElseForAddLast(Node<T> aux){    //case 3
        Node<T> tmp = getLast();
        tmp.setNext(aux);
        setLast(aux);
        getLast().setPrev(tmp);
        setSize(getSize()+1);
    }

    /**
     * remove the last item in the least
     * @return the last item in the list
     */
    public T removeLast(){
        Node<T> aux = getLast();
        if(getSize()==0)        //list is empty
            throw new NoSuchElementException();
        if(getSize()==1){   //only 1 item in the list
            setLast(null);
            setHead(null);
        }
        else {
            setLast(aux.getPrev());     //more then 1 item in the list
            getLast().setNext(null);
            aux.setPrev(null);
        }
        setSize(getSize()-1);
        return aux.getData();
    }
}

