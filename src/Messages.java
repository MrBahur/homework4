import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Messages implements Iterable<Message> {
    private Message[] data;
    private int curr;
    private HashTable[] hashTables;

    public Messages(){
        this.data=null;
        this.curr=0;
        this.hashTables=null;
    }

    //Getters:

    public Message[] getData() {
        return data;
    }

    public int getCurr() {
        return curr;
    }

    public HashTable[] getHashTables() {
        return hashTables;
    }

    //Setters:


    public void setData(Message[] data) {
        this.data = data;
    }

    public void setCurr(int curr) {
        this.curr = curr;
    }

    public void setHashTables(HashTable[] hashTables) {
        this.hashTables = hashTables;
    }

    //Iterator:

    @Override
    public Iterator<Message> iterator() {
        setCurr(0);
        return new Iterator<Message>() {
            @Override
            public boolean hasNext() {
                return getData()!=null && getData().length>getCurr();
            }

            @Override
            public Message next() {
                if(!hasNext())
                    throw new NoSuchElementException();
                Message tmp = getData()[getCurr()];
                setCurr(getCurr()+1);
                return tmp;
            }
        };
    }


    //methods

    /**
     * return all the messages that are spam messages
     * @param location the location of the file that hold the spam words + ratio
     * @param friends a BTree holding friends
     * @return a string with all the Spam messages separated by ','
     */
    public String findSpams(String location, BTree friends)
    {
        Spams spams = new Spams(location);      //create a spam DB from the Spam file
        HashTable[] hashTables = getHashTables();
        StringBuilder toReturn = new StringBuilder();       //create new StringBuilder to build the String toReturn
        helperForFindSpams(friends,spams,toReturn,hashTables);
        if(toReturn.length()!=0) toReturn.deleteCharAt(toReturn.length()-1);//deleting the last ','
        return toReturn.toString();
    }

    /**
     * helper function for findSpams that actually build the String
     * @param friends a BTree holding friends
     * @param spams Spam DB
     * @param toReturn the String (in a StringBuilder) that will eventually get returned
     * @param hashTables the HashTables for all the Messages
     */
    private void helperForFindSpams(BTree friends,Spams spams, StringBuilder toReturn,HashTable[] hashTables){
        boolean isSpam=false;
        int i=0;
        for (Message m:getData()) {
            if(!friends.search(m.getRecipient(),m.getSender())){//check if they sender and recipient are friends
                isSpam =checkSpam(spams,hashTables[i]);//if not then check if spam
            }
            if(isSpam) { //if its spam then ass the number to the StringBuilder
                toReturn.append(i);
                toReturn.append(',');
                isSpam=false;//drop the flag
            }
            i++;
        }
    }

    /**
     * a method that check if a message is a spam message assuming the sender and recipient aren't friends
     * @param spams the DB holds the spams and ratio
     * @param hashTable the HashTable holding the words in the message
     * @return true if the message is spam, false otherwise
     */
    private boolean checkSpam(Spams spams,HashTable hashTable){
        for (Spam s:spams) {
            String word = s.getWord();
            HashList list = hashTable.getList(hashTable.hashFunction(word));
            HashListElement element = list.search(word);
            if(element!=null&&(float)100*element.getNumberOfInstances()/hashTable.getSize()>=s.getRatio()){//this if is only long because
                return true;
            }
        }
        return false;
    }

    /**
     *a function that create all the HashTables for all the Messages
     * @param M the size of the HashTable
     */
    public void createHashTables(String M){
        HashTable table[] = new HashTable[getData().length];
        int i=0;
        for (Message m:getData()) {
            String text = m.getText();
            table[i] = new HashTable(M);
            handleText(text,table[i]);
            i++;
        }
        setHashTables(table);
    }

    /**
     * helper function for createHashTable() that handling the text
     * @param text  the text
     * @param table a table for the current message
     */
    private void handleText(String text,HashTable table){
        StringBuilder word = new StringBuilder();
        for(int i=0; i<=text.length();i++){
            if(i!=text.length()&&text.charAt(i)!=' '){
                word.append(text.charAt(i));
            }
            else{
                String toInsert = handleWord(word);
                table.insert(toInsert);
                word.delete(0,word.length());
            }
        }
    }

    /**
     * really does nothing, just here for after ill submit the assignment
     * @param word StringBuilder
     * @return  StringBuilder.toString
     */
    private String handleWord(StringBuilder word){
            //would have been cooler if we needed to make Loan and loan be the same
            //and also remove non letters char (such as .,- etc..)
            //did it and only then saw at the forum that its unnecessary
        return word.toString();

    }

    /**
     * create a DB of the messages from a file
     * @param location  the location of the file
     */
    public void generateMessages(String location){
        DoublyLinkedList<Message> list=new DoublyLinkedList<>();//create a list of all the Messages
        File messages = new File(location);
        Message aux = new Message();
        try {
            Scanner input = new Scanner(messages);
            while (input.hasNextLine()) {
                String line = input.nextLine();
                if (line.length() == 0) {continue;}
              aux = handleLine(list,line,aux);//taking care of all the different lines, while inserting if it sees '#'
            }
            insertToList(list,aux);// inserting one more time in the case the file didn't has a '#' at the end, if the message will be empty, it wont get inserted
        } catch(FileNotFoundException ex){
            ex.printStackTrace();
        }
        createArrayFromList(list);//create array from the list
    }

    /**
     * insert a message to the list
     * @param list the list
     * @param toInsert  the message
     */
    private void insertToList(DoublyLinkedList<Message> list, Message toInsert){
        if(!toInsert.getText().equals(""))
            list.addLast(toInsert);
    }

    /**
     * handle a line from the file
     * @param list  the list of the messages
     * @param line  a line to handle
     * @param aux   a message to put the line into
     * @return  the Message aux with the extra line or an empty message if the line was '#'
     */

    private Message handleLine(DoublyLinkedList<Message> list,String line, Message aux){
        if(line.equals("#")) {
            insertToList(list, aux);
            aux = new Message();
        }
        if(line.contains("From:")&&aux.getSender().equals("")){
            aux.setSender(line.substring(5));
        }
        else if(line.contains("To:")&&aux.getRecipient().equals("")) {
            aux.setRecipient(line.substring(3));
        }
        else if(!line.equals("#")){
            aux.setText(aux.getText()+line);
        }
        return aux;
    }

    /**
     * create an array from a list
     * @param list the list
     */
    private void createArrayFromList(DoublyLinkedList<Message> list)
    {
        int size =list.getSize();
        Message [] data = new Message[size];
        for(int i=0;i<size;i++){
            data[i]=list.getHead().getData();
            list.setHead(list.getHead().getNext());
        }
        setData(data);
    }
}
