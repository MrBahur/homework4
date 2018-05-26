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
        if(aux!=null){
            aux.setNumberOfInstances(aux.getNumberOfInstances()+1);
        }
        else {
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
