public class HashTable {
    private HashList[] list;
    final private int m;
    private int size;

    //Constructors:
    private HashTable(int m){
        if(m<1)
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



    public void setList(HashList list, int i) {
        HashList[] lists = getLists();
        lists[i]=list;
    }
    //Methods

    public int hashFunction(String toHash){
        int hash = 7;           //prime number
        for(int i=0;i<toHash.length();i++){
            hash = hash*31+toHash.charAt(i);        //31 is another prime number.
                                                    //we learned that this is a good way to hash a string, normally i would have choose better hashFunction from a Universal HashFunction
                                                    //collection in a random way, but you didn't let me so...
        }
        return Math.abs(hash%getM());
    }

    /**
     * pretty straight forward, using a HashFunction, i choose the correct list to insert the item to
     * and i insert it to it's head.
     * @param toInsert the key i want to insert
     */
    public void insert(String toInsert){
        if(toInsert==null)
            throw new NullPointerException();
        if(toInsert.length()==0)
            return;
        HashList list = getList(hashFunction(toInsert));
        list.insert(toInsert);
        setSize(getSize()+1);
    }
}
