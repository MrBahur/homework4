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
        for(int i=0;i<m;i++){
            setList(new HashList(),i);
        }
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
    public HashList getList(int i){
        return getLists()[i];
    }

    public int getSize() {
        return size;
    }
    //Setters

    public void setSize(int size) {
        this.size = size;
    }

    public int hashFunction(String toHash){
        int hash = 7;
        for(int i=0;i<toHash.length();i++){
            hash = hash*31+toHash.charAt(i);
        }
        return Math.abs(hash%getM());
    }

    public void setList(HashList list, int i) {
        HashList[] lists = getLists();
        lists[i]=list;
    }
    //Methods

    public void insert(String toInsert){
        HashList list = getList(hashFunction(toInsert));
        list.insert(toInsert);
        setSize(getSize()+1);
    }
}
