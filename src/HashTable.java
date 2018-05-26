public class HashTable {
    private HashList[] list;
    final private int m;
    private int size;

    //Constructors:
    private HashTable(int m){
        if(m<=0)
            throw new IllegalArgumentException();
        this.m=m;
        this.size=0;
        this.list = new HashList[m];
    }
    public HashTable(String s){
        this(Integer.parseInt(s));
    }

    //Getters

    public int getM() {
        return m;
    }

    public HashList[] getLists() {
        return list;
    }
    private HashList getList(int i){
        return getLists()[i];
    }

    public int getSize() {
        return size;
    }
    //Setters

    public void setSize(int size) {
        this.size = size;
    }

    private int hashFunction(String toHash){

        return 0;
    }

    public void insert(String toInsert){
        HashList list = getList(hashFunction(toInsert));
        list.insert(toInsert);
        setSize(getSize()+1);
    }
}
