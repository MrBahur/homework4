public class DoublyLinkedList<T> {

    private Node<T> head;
    private Node<T> last;
    private int size;

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
            setHead(aux);
            setLast(aux);
            setSize(1);
        }
        else if(getSize()==1)
        {
            getHead().setNext(aux);
            setLast(aux);
            aux.setPrev(getHead());
            setSize(2);
        }
        else
        {
            Node<T> tmp = getLast();
            tmp.setNext(aux);
            setLast(aux);
            last.setPrev(tmp);
            setSize(getSize()+1);
        }
    }
}

