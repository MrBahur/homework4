/*
 *well... a Queue
 * FIFO
 * didn't used pick() but its important to be able to know who is your first
 * element in the queue
 */

public class Queue {
    DoublyLinkedList<Object> data;

    //Constructors
    public Queue(){
        data = new DoublyLinkedList<>();
    }

    //Getters

    public DoublyLinkedList<Object> getData() {
        return data;
    }


    //methods
    public void enqueue(Object toInsert){
        if((!(toInsert instanceof BTreeNode))&(!(toInsert instanceof String)))
            throw new IllegalArgumentException();
        getData().addFirst(toInsert);
    }
    public Object dequeue(){
        return getData().removeLast();
    }
    public Object pick(){
        return getData().getLast();
    }
    boolean isEmpty(){
        return getData().getSize()==0;
    }
}
