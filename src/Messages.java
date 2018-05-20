import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Messages implements Iterable<Message> {

    private DoublyLinkedList<Message> data;
    private Node<Message> curr;

    public Messages()
    {
        this.data = new DoublyLinkedList<>();
        this.curr=null;
    }

    //Getters:

    public Node<Message> getCurr() {
        return curr;
    }

    public DoublyLinkedList<Message> getData() {
        return data;
    }
    //Setters:

    public void setCurr(Node<Message> curr) {
        this.curr = curr;
    }

    //Iterator:

    @Override
    public Iterator<Message> iterator(){
        setCurr(getData().getHead());
        return new Iterator<Message>() {
            @Override
            public boolean hasNext() {
                return getCurr()!=null;
            }
            @Override
            public Message next() {
                if(!hasNext())
                    throw new NoSuchElementException();
                Node<Message> aux = getCurr();
                setCurr(aux.getNext());
                return (aux.getData());
            }
        };
    }


    //methods

    public String findSpams(String locaition, BTree friends)
    {

        return null;
    }
    public void createHashTables(String M){
        int m = Integer.parseInt(M);

    }

    public void generateMessages(String location){
        File messages = new File(location);
        Scanner input;
        Message aux = new Message();
        try {
            input = new Scanner(messages);
            String line;
            while (input.hasNextLine()) {
                line = input.nextLine();
                if (line.length() == 0) {continue;}
              handleLine(line,aux);
            }
        } catch(FileNotFoundException ex){
            ex.printStackTrace();
        }
    }

    private void insert(Message toInsert){
        getData().addLast(toInsert);
    }
    private void handleLine(String line, Message aux)
    {
        if(line.equals("#")) {
            insert(aux);
            aux = new Message();
        }
        if(line.contains("From:")){
            aux.setSender(line.substring(5));
        }
        else if(line.contains("To:")) {
            aux.setRecipient(line.substring(3));
        }
        else{
            aux.setText(aux.getText()+line);
        }
    }
}
