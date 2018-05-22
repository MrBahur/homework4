import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Messages implements Iterable<Message> {

    private Message[] data;
    private int curr;

    public Messages(){
        this.data=null;
        this.curr=0;
    }

    //Getters:

    public Message[] getData() {
        return data;
    }

    public int getCurr() {
        return curr;
    }
    //Setters:


    public void setData(Message[] data) {
        this.data = data;
    }

    public void setCurr(int curr) {
        this.curr = curr;
    }
    //Iterator:

    @Override
    public Iterator<Message> iterator() {
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

        return null;
    }
    public void createHashTables(String M){
        int m = Integer.parseInt(M);

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
