import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BTree {

    private BTreeNode root;
    private final int t;
    private int size;
    private BTree(int t) {
        this.t = t;
        this.root = new BTreeNode(t);
        this.size=0;
    }

    //Constructors
    public BTree(String s) {
        this(Integer.parseInt(s));
    }

   //Getters

    public int getSize() {
        return size;
    }

    private BTreeNode getRoot() {
        return root;
    }

    private int getT() {
        return t;
    }

   //Setters

    public void setSize(int size) {
        this.size = size;
    }

    private void setRoot(BTreeNode root) {
        this.root = root;
    }

    public void insert(String key) {
        BTreeNode tmp = getRoot();
        if (tmp.isFull()) {
            BTreeNode newRoot = new BTreeNode(getT());
            newRoot.setKid(0, tmp);
            newRoot.setLeaf(false);
            newRoot.setHeight(newRoot.getKid(0).getHeight()+1);
            setRoot(newRoot);
            newRoot.splitChild(0);
            newRoot.insert(key);
        } else {
            tmp.insert(key);
        }
        setSize(getSize()+1);
    }
    //Methods
    public String Search(String key) {
        return root.search(key);
    }

    public void createFullTree(String location) {
        File friendsList = new File(location);
        Scanner input = null;
        try {
            input = new Scanner(friendsList);
            String line;
            while (input.hasNextLine()) {
                line = input.nextLine();
                if (line.length() == 0) {continue;}
                this.insert(line);
            }
        } catch(FileNotFoundException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return BFS().toString();
    }

    private StringBuilder BFS(){
        StringBuilder toReturn = new StringBuilder();
        Queue queue = new Queue();
        queue.enqueue(getRoot());
        WrapInt currentHeight = new WrapInt(getRoot().getHeight()+1);
        while (!queue.isEmpty()){
            Object obj = queue.dequeue();
            toReturn = function(queue,toReturn, obj, currentHeight);
        }
        return toReturn;
    }
    private StringBuilder function(Queue queue,StringBuilder toReturn, Object obj, WrapInt currentHeight){
        if(obj instanceof String)
            toReturn.append((String)obj);
        else if(obj instanceof BTreeNode)
            function(queue,toReturn,(BTreeNode)obj,currentHeight);
        else
            throw new IllegalArgumentException();
        return toReturn;
    }
    private void function(Queue queue, StringBuilder toReturn, BTreeNode node, WrapInt currentHeight){
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

    //to test and remove
    public static void main(String[] args) {
        int t = 2;
        BTree test = new BTree(t);
        test.createFullTree(System.getProperty("user.dir")+"/friends.txt");
        System.out.println(test.BFS());
    }
}

