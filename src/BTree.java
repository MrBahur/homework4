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


    public String BFS(){
        String toReturn = "";
        Queue queue = new Queue();
        queue.enqueue(getRoot());
        while(!queue.isEmpty()){
            toReturn= function(toReturn,queue,queue.dequeue());
        }
        return toReturn;
    }

    private String function(String tmp,Queue queue ,Object obj){
        if(obj instanceof String)
            return function(tmp,(String)obj);
        if(obj instanceof BTreeNode)
            return function(tmp,queue,(BTreeNode)obj);
        throw new IllegalArgumentException();
    }
    private String function(String tmp, String toPush){
        tmp+=toPush;
        return tmp;
    }

    private String function(String tmp, Queue queue, BTreeNode node){
        for(int i=0;i<=node.getSize();i++){
            queue.enqueue(node.getKid(i));
            if(i<node.getSize())
                queue.enqueue("|");
        }
        return tmp+node.stringifyNode();
    }

    //to test and remove
    public static void main(String[] args) {
        int t = 2;
        BTree test = new BTree(t);
        test.createFullTree(System.getProperty("user.dir")+"/friends.txt");
        System.out.print(test.BFS());
    }
}

