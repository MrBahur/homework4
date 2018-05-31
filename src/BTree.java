import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Constructors


public class BTree {

    private BTreeNode root;
    private final int t;
    private int size;
    private BTree(int t) {
        if(t<=1)
            throw new IllegalArgumentException();
        this.t = t;
        this.root = new BTreeNode(t);
        this.size=0;
    }


    public BTree(String s) {
        this(Integer.parseInt(s));
    }

   //Getters

    private int getSize() {
        return size;
    }

    private BTreeNode getRoot() {
        return root;
    }

    private int getT() {
        return t;
    }

   //Setters

    private void setSize(int size) {
        if(size<0)
            throw new IllegalArgumentException();
        this.size = size;
    }

    private void setRoot(BTreeNode root) {
        if(root==null)
            throw new IllegalArgumentException();
        this.root = root;
    }
    //Methods

    /**
     * insert a new key to the BTree
     * @param key to insert
     */
    public void insert(String key) {
        if(key==null) throw new NullPointerException();             //can't insert null to tree
        if(key.length()==0) throw new IllegalArgumentException();   //can't insert empty String as a key
        BTreeNode tmp = getRoot();
        if (tmp.isFull()) {                                         //handling the case that the root is full
            BTreeNode newRoot = new BTreeNode(getT());
            newRoot.setKid(0, tmp);
            newRoot.setLeaf(false);
            newRoot.setHeight(newRoot.getKid(0).getHeight()+1);
            setRoot(newRoot);
            newRoot.splitChild(0);
            newRoot.insert(key);
        } else{                                                     //handling the case the rood isn't fool
            tmp.insert(key);
        }
        setSize(getSize()+1);                                       //increasing size of total keys in tree
    }

    /**
     * this function search the tree
     * @param key key to search
     * @return the object if found, null else
     */
    public String search(String key) {                              //this is public because the normal BTree need to support Search(key)
        if(key==null)                                               //no nulls in my tree
            throw new NullPointerException();
        if(key.length()==0)                                         //no empty Strings as keys in my tree
            throw new IllegalArgumentException();
        return root.search(key);                                    //passing the mission to the root
    }

    /**
     * the real search i am using this wotk
     * @param key1 first friend to look for
     * @param key2 second friend to look for
     * @return true if those two are friends, false otherwise
     */
    public boolean search(String key1, String key2){
        if(key1==null|key2==null)                                   //in this work, allays search for two keys separated by " & "
            throw new NullPointerException();
        if((search(key1+" & "+key2)==null)&& search(key2+" & "+key1)==null){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * create key from file
     * @param location the locaition of the file
     */
    public void createFullTree(String location) {
        if(location==null)
            throw new NullPointerException();
        File friendsList = new File(location);
        Scanner input = null;
        try {
            input = new Scanner(friendsList);       //creating scanner to use for reading the file
            String line;
            while (input.hasNextLine()) {
                line = input.nextLine();        //moving line by line and adding it to the tree
                if (line.length() == 0) {continue;}     //could happen (like in line 1)
                this.insert(line);
            }
        } catch(FileNotFoundException ex){
            ex.printStackTrace();
        }
    }


    /**
     * returning a BFS String that made from the tree
     */
    @Override
    public String toString() {
        return BFS().toString();
    }

    /**
     *don't really have something to say about it
     * using the normal algorithm for BFS printing a tree using a queue
     * @return a StringBuilder that holds the String in BFS order
     */
    private StringBuilder BFS(){
        StringBuilder toReturn = new StringBuilder();
        Queue queue = new Queue();              //creating the queue
        queue.enqueue(getRoot());               //adding the root to the queue
        WrapInt currentHeight = new WrapInt(getRoot().getHeight()+1);       //adding the height of the tree to WrapInt obj inorder to send it by reference to the function
        while (!queue.isEmpty()){                   //while the Queue isn't empty we still have node to take care of
            Object obj = queue.dequeue();
            toReturn = helperForBfs(queue,toReturn, obj, currentHeight);            //this function is spited to 2, in case object is String, and in case its a node
        }
        return toReturn;
    }
    private StringBuilder helperForBfs(Queue queue,StringBuilder toReturn, Object obj, WrapInt currentHeight){
        if(obj instanceof String)
            toReturn.append((String)obj);                                       //dealing with the String case
        else if(obj instanceof BTreeNode)
            helperForBfs(queue,toReturn,(BTreeNode)obj,currentHeight);          //dealing with the Node case
        else
            throw new IllegalArgumentException();
        return toReturn;
    }

    private void helperForBfs(Queue queue, StringBuilder toReturn, BTreeNode node, WrapInt currentHeight){      //this function deals with the Node case
         if((node.getHeight()==currentHeight.getValue())&!node.getLeaf()){
            queue.enqueue("^");
        }
        else if(!node.getLeaf()){
            queue.enqueue("#");
        }
        if(!node.getLeaf()){
            for(int i=0;i<=node.getSize();i++){
                queue.enqueue(node.getKid(i));
                if(i<node.getSize())
                    queue.enqueue("|");
            }
        }
        currentHeight.setValue(node.getHeight());
        toReturn.append(node.stringifyNode());
    }
}

