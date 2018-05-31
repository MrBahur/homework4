import java.util.stream.Stream;
import static java.util.stream.Collectors.joining;

public class BTreeNode {
    private String[] keys;
    private BTreeNode [] kids;
    private int size;
    private boolean isLeaf;
    private final int t;
    private int height;

    //Constructors
    public BTreeNode(int t){
        this.t=t;
        isLeaf=true;
        size=0;
        keys = new String[2*t-1];
        kids = new BTreeNode[2*t];
        height =0;
    }

    //Getters

    public int getHeight(){
        return height;
    }
    private String[] getKeys(){
        return keys;
    }
    private String getKey(int i){
        return keys[i];
    }
    private BTreeNode[] getKids() {
        return kids;
    }
    public BTreeNode getKid(int i){
        return kids[i];
    }
    public int getSize(){
        return size;
    }
    public boolean getLeaf(){
        return isLeaf;
    }
    private int getT() {
        return t;
    }

    //Setters
    private void setSize(int size){
        this.size=size;
    }
    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }
    private void setKey(int i, String key)
    {
        keys[i]=key;
    }
    public void setKid(int i, BTreeNode kid)
    {
        kids[i]=kid;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    //Methods

    /**
     * boolean function that tells us if the node is full0
     * @return true if the node is full, false otherwise
     */
    public boolean isFull(){
        return getSize()==getT()*2-1;
    }

    /**
     * Searching in a node if not found, recursively search in the child (respectively)
     * @param key key to search
     * @return nul if not found, obj if this is he's key.
     */
    public String search(String key)
    {
        int i=0;
        while(i<getSize()&&isLarge(key,getKey(i))){
            i++;
        }
        if(i<getSize()&&key.equals(getKey(i))){//found
            return getKey(i);
        }
        else if(getLeaf()){     //not found
            return null;
        }
        else{
            return getKid(i).search(key);//might be in child
        }
    }

    /**
     * inserting a key to a node
     * @param key the node to insert
     */
    public void insert(String key){
        int i=getSize()-1;
        if(getLeaf())
            handleLeaf(i,key);//making room to insert(moving all the relevant keys right ). can't be full cuz of BTree
        else
            handleNotLeaf(i,key);//making room  to insert, might be full because it's not a leaf.
    }
    private void handleLeaf(int i,String key){
        while(i>=0&&isLess(key,getKey(i))){
            setKey(i+1,getKey(i));
            i--;
        }
        setKey(i+1,key);
        setSize(getSize()+1);
    }
    private void handleNotLeaf(int i, String key){
        while (i>=0&&isLess(key,getKey(i))){
            i--;
        }
        i++;
        BTreeNode tmp = getKid(i);
        if(tmp.isFull()){       //handling the case the child is full
            splitChild(i);
            if(isLarge(key,tmp.getKey(i)))
                i++;
        }
        tmp=getKid(i);
        tmp.insert(key);
    }

    /**
     * split child is one of the most impotent method in BTree and this is what keep it safe (all Nodes in the needed size)
     * @param i the index of the child we need to split
     */
    public void splitChild (int i)
    {
        BTreeNode leftLeaf = new BTreeNode(getT());         //creating the left Node
        BTreeNode rightLeaf = new BTreeNode(getT());        //creating the right Node
        BTreeNode tmp = this.getKid(i);                     //holding the child we want to split
        String tmpKey = tmp.getKey(getT()-1);
        setLeavesForSpilt(rightLeaf,leftLeaf,tmp);          //copying all the relevant items into right and leaf leave
        for(int j=getSize()-1;j>=i;j--)
            setKey(j+1,getKey(j));                      //moving all the keys from i right
        for(int j=getSize();j>=i;j--)
            setKid(j+1,getKid(j));                      //moving all the kids from i right
        setKey(i,tmpKey);                                   //putting the kid in the place
        setSize(getSize()+1);                               //setting the size
        setKid(i,leftLeaf);                                 //setting the relevant kids (left then right)
        setKid(i+1,rightLeaf);
    }

    /**
     * comparing between to Strings using isLess making the code more readable
     * @param s1 first string
     * @param s2 second string
     * @return s1 is less then s2
     */
    private boolean isLess(String s1, String s2){
        return s1.compareTo(s2)<0;
    }
    /**
     * comparing between to Strings using isLarge making the code more readable
     * @param s1 first string
     * @param s2 second string
     * @return s1 is more then s2
     */
    private boolean isLarge(String s1, String s2)
    {
        return s1.compareTo(s2)>0;
    }

    /**
     * the function does what her names says..
     * @param rightLeaf the left leaf
     * @param leftLeaf the right leaf
     * @param tmp the kid we want to split
     */
    private void setLeavesForSpilt(BTreeNode rightLeaf, BTreeNode leftLeaf, BTreeNode tmp)
    {
        for(int j=0;j<getT()-1;j++){        //copying keys
            leftLeaf.setKey(j,tmp.getKey(j));
            rightLeaf.setKey(j,tmp.getKey(j+getT()));
        }
        if(!tmp.getLeaf()){                 //copying kids
            for(int j=0;j<=getT()-1;j++){
                leftLeaf.setKid(j, tmp.getKid(j));
                rightLeaf.setKid(j, tmp.getKid(j + getT()));
            }
        }
        setValuesForLeavesInSplit(rightLeaf,leftLeaf,tmp);
    }

    /**
     * setting the child we split values (isLeaf, Height and Size)
     * @param rightLeaf the left leaf
     * @param leftLeaf the right leaf
     * @param tmp the kid we want to split
     */
    private void setValuesForLeavesInSplit(BTreeNode rightLeaf, BTreeNode leftLeaf, BTreeNode tmp){
        leftLeaf.setLeaf(tmp.getLeaf());
        rightLeaf.setLeaf(tmp.getLeaf());
        leftLeaf.setHeight(tmp.getHeight());
        rightLeaf.setHeight(tmp.getHeight());
        leftLeaf.setSize(getT()-1);
        rightLeaf.setSize(getT()-1);
    }

    public String stringifyNode() {                         //just making array to String with ',; between every key, without Nulls and empty cells.
        return  Stream.of(getKeys()).filter(s -> s != null && !s.isEmpty()).collect(joining(","));
    }
}
