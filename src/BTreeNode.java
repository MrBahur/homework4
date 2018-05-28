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
    public boolean isFull(){
        return getSize()==getT()*2-1;
    }
    public String search(String key)
    {
        int i=0;
        while(i<getSize()&&isLarge(key,getKey(i))){
            i++;
        }
        if(i<getSize()&&key.equals(getKey(i))){
            return getKey(i);
        }
        else if(getLeaf()){
            return null;
        }
        else{
            return getKid(i).search(key);
        }
    }

    public void insert(String key){
        int i=getSize()-1;
        if(getLeaf())
            handleLeaf(i,key);
        else
            handleNotLeaf(i,key);
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
        if(tmp.isFull()){
            splitChild(i);
            if(isLarge(key,tmp.getKey(i)))
                i++;
        }
        tmp=getKid(i);
        tmp.insert(key);
    }
    public void splitChild (int i)
    {
        BTreeNode leftLeaf = new BTreeNode(getT());
        BTreeNode rightLeaf = new BTreeNode(getT());
        BTreeNode tmp = this.getKid(i);
        String tmpKey = tmp.getKey(getT()-1);
        setLeavesForSpilt(rightLeaf,leftLeaf,tmp);
        for(int j=getSize()-1;j>=i;j--)
            setKey(j+1,getKey(j));
        for(int j=getSize();j>=i;j--)
            setKid(j+1,getKid(j));
        setKey(i,tmpKey);
        setSize(getSize()+1);
        setKid(i,leftLeaf);
        setKid(i+1,rightLeaf);
    }
    private boolean isLess(String s1, String s2){
        return s1.compareTo(s2)<0;
    }
    private boolean isLarge(String s1, String s2)
    {
        return s1.compareTo(s2)>0;
    }
    private void setLeavesForSpilt(BTreeNode rightLeaf, BTreeNode leftLeaf, BTreeNode tmp)
    {
        for(int j=0;j<getT()-1;j++){
            leftLeaf.setKey(j,tmp.getKey(j));
            rightLeaf.setKey(j,tmp.getKey(j+getT()));
        }
        if(!tmp.getLeaf()){
            for(int j=0;j<=getT()-1;j++){
                leftLeaf.setKid(j, tmp.getKid(j));
                rightLeaf.setKid(j, tmp.getKid(j + getT()));
            }
        }
        setValuesForLeavesInSplit(rightLeaf,leftLeaf,tmp);
    }
    private void setValuesForLeavesInSplit(BTreeNode rightLeaf, BTreeNode leftLeaf, BTreeNode tmp){
        leftLeaf.setLeaf(tmp.getLeaf());
        rightLeaf.setLeaf(tmp.getLeaf());
        leftLeaf.setHeight(tmp.getHeight());
        rightLeaf.setHeight(tmp.getHeight());
        leftLeaf.setSize(getT()-1);
        rightLeaf.setSize(getT()-1);
    }

    public String stringifyNode() {
        String joined = Stream.of(getKeys()).filter(s -> s != null && !s.isEmpty()).collect(joining(","));
        return   joined;
    }
}
