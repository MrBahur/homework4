
public class BTree {

    public BTreeNode root;
    public final int t;

    public BTree(int t){
        this.t=t;
        this.root= new BTreeNode(t);
    }
    public BTree(String s){
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

    public void insert(String key)
    {
        BTreeNode tmp =getRoot();
        if(tmp.isFull())
        {
            BTreeNode newRoot = new BTreeNode(getT());
            newRoot.setKid(0,tmp);
            newRoot.setLeaf(false);
            setRoot(newRoot);
            newRoot.splitChild(0);
            newRoot.insert(key);
        }
        else
        {
            tmp.insert(key);
        }
    }
    public String Search(String key)
    {
        return root.search(key);
    }
    public void createFullTree(String location)
    {
        String curr;

    }
/*
*
* */
        public static void main(String [] args)
        {
            int t=5;
            BTree test = new BTree(t);
            for(char a='a';a<='z';a++)
            {
                test.insert(a+"");
            }
            System.out.println(test.getRoot().search("a").compareTo("a")==0);
            System.out.println(test.getRoot().search("1")==null);
        }
}

