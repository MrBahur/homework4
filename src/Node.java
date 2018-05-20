public class Node<T> {
    private Node<T> next;
    private Node<T> prev;
    private T data;

    //constructors:

    public Node(Node next,Node prev, T data){
        this .next=next;
        this.prev=prev;
        this.data=data;
    }

    //getters:

    public T getData() {
        return data;
    }

    public Node<T> getNext() {
        return next;
    }
    public Node<T> getPrev(){
        return prev;
    }

    //setters:


    public void setNext(Node<T> next) {
        this.next = next;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }
}
