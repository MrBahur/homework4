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

    public void addLast(T toInsert) {
        if(toInsert==null)
            throw new NullPointerException();
        Node<T> aux = new Node<>(null,null,toInsert);
        if(getSize()==0){
            addFirst(aux);
        }
        else if(getSize()==1)        {
           addSecond(aux);
        }
        else{
            addElse(aux);
        }
    }
    private void addFirst(Node<T> aux){
        setHead(aux);
        setLast(aux);
        setSize(1);
    }
    private void addSecond(Node<T> aux){
        getHead().setNext(aux);
        setLast(aux);
        aux.setPrev(getHead());
        setSize(2);
    }
    private void addElse(Node<T> aux){
        Node<T> tmp = getLast();
        tmp.setNext(aux);
        setLast(aux);
        last.setPrev(tmp);
        setSize(getSize()+1);
    }
}

