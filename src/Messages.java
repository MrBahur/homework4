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

    public String findSpams(String location, BTree friends)
    {
        Spams spams = new Spams(location);
        HashTable[] hashTables = getHashTables();
        StringBuilder toReturn = new StringBuilder();
        boolean areFriends=false;
        boolean isSpam=false;
        int i=0;
        for (Message m:getData()) {
            areFriends = friends.Search(m.getRecipient(),m.getSender());
            if(!areFriends){
                isSpam =checkSpam(spams,hashTables[i]);
            }
            if(isSpam) {
                toReturn.append(i);
                toReturn.append(',');
                isSpam=false;
            }
            i++;
        }
        toReturn.deleteCharAt(toReturn.length()-1);
        return toReturn.toString();
    }

    private boolean checkSpam(Spams spams,HashTable hashTable){
        for (Spam s:spams) {
            String word = s.getWord();
            HashList list = hashTable.getList(hashTable.hashFunction(word));
            HashListElement element = list.search(word);
            if(element!=null&&(float)100*element.getNumberOfInstances()/hashTable.getSize()>=s.getRatio()){
                return true;
            }
        }
        return false;
    }
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
    private void handleText(String text,HashTable table){
        StringBuilder word = new StringBuilder();
        for(int i=0; i<text.length();i++){
            if(text.charAt(i)!=' '){
                word.append(text.charAt(i));
            }
            else{
                String toInsert = handleWord(word);
                table.insert(toInsert);
                word.delete(0,word.length());
            }
        }
    }
    private String handleWord(StringBuilder word){
            //would have been cooler if we needed to make Loan and loan be the same
            //and also remove non letters char (such as .,- etc..)
            //did it and only then saw at the forum that its unnecessary
        return word.toString();

    }

    public void generateMessages(String location){
        DoublyLinkedList<Message> list=new DoublyLinkedList<>();
        File messages = new File(location);
        Message aux = new Message();
        try {
            Scanner input = new Scanner(messages);
            while (input.hasNextLine()) {
                String line = input.nextLine();
                if (line.length() == 0) {continue;}
              aux = handleLine(list,line,aux);
            }
            insertToList(list,aux);
        } catch(FileNotFoundException ex){
            ex.printStackTrace();
        }
        createArrayFromList(list);
    }

    private void insertToList(DoublyLinkedList<Message> list, Message toInsert){
        list.addLast(toInsert);
    }
    private Message handleLine(DoublyLinkedList<Message> list,String line, Message aux){
        if(line.equals("#")) {
            insertToList(list, aux);
            aux = new Message();
        }
        if(line.contains("From:")){
            aux.setSender(line.substring(5));
        }
        else if(line.contains("To:")) {
            aux.setRecipient(line.substring(3));
        }
        else if(!line.equals("#")){
            aux.setText(aux.getText()+line);
        }
        return aux;
    }
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
    public static void main(String[] args){
        String location =System.getProperty("user.dir")+"/messages.txt";
        Messages test = new Messages();
        test.generateMessages(location);

    }
}
