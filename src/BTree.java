import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class BTree {

    public BTreeNode root;
    public final int t;
    public BTree(int t) {
        this.t = t;
        this.root = new BTreeNode(t);
    }

    public BTree(String s) {
        this(Integer.parseInt(s));
    }

    /*
    Getters:
    ----------------------------------------
    */

    public BTreeNode getRoot() {
        return root;
    }

    public int getT() {
        return t;
    }
    /*
    Setters
    ----------------------------------------
    */

    public void setRoot(BTreeNode root) {
        this.root = root;
    }

    public void insert(String key) {
        BTreeNode tmp = getRoot();
        if (tmp.isFull()) {
            BTreeNode newRoot = new BTreeNode(getT());
            newRoot.setKid(0, tmp);
            newRoot.setLeaf(false);
            setRoot(newRoot);
            newRoot.splitChild(0);
            newRoot.insert(key);
        } else {
            tmp.insert(key);
        }
    }

    public String Search(String key) {
        return root.search(key);
    }

    @Override
    public String toString() {
        return root.toString();
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
    /*
    *
    * */
    public static void main(String[] args) {
        int t = 2;
        BTree test = new BTree(t);
        test.createFullTree(System.getProperty("user.dir")+"/friends.txt");
    }
}

