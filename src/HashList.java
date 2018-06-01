/*
 *  not much to say about this HashList
 *  used simple DoublyLinkedList (that i implanted) to implement it
 *  pretty simple.
 *
 */


public class HashList {
    private DoublyLinkedList<HashListElement> list;

    //Constructors
    public HashList() {
        list = new DoublyLinkedList<>();
    }
    //Getters

    public DoublyLinkedList<HashListElement> getList() {
        return list;
    }

    //Methods
    public void insert(String toInsert) {
        HashListElement aux = search(toInsert);
        if(aux!=null){                      //if i already inserted this key then i just set NumberOfInstances to be +1
            aux.setNumberOfInstances(aux.getNumberOfInstances()+1);
        }
        else {                              //if i haven't then i insert it to the list.
            getList().addFirst(new HashListElement(toInsert));
        }
    }

    public HashListElement search(String toSearch) {
        Node<HashListElement> tmp = getList().getHead();
        while(tmp!=null){
            if(tmp.getData().getWord().equals(toSearch)){
                return tmp.getData();
            }
            else{
                tmp=tmp.getNext();
            }
        }
        return null;
    }
}
