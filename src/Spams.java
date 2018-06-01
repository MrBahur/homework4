import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Spams implements Iterable<Spam> {

    private Spam[] data;
    private int curr;

    //Constructors:
    public Spams(String location){
        this.data = null;
        this.curr = 0;
        initiateData(location);
    }

    //Getters:

    public Spam[] getData() {
        return data;
    }

    public int getCurr() {
        return curr;
    }
    //Setters:

    public void setData(Spam[] data) {
        this.data = data;
    }

    public void setCurr(int curr) {
        this.curr = curr;
    }


    //Iterator:
    @Override
    public Iterator<Spam> iterator() {
        setCurr(0);
        return new Iterator<Spam>() {
            @Override
            public boolean hasNext() {
                return getData()!=null&&getData().length>getCurr();
            }

            @Override
            public Spam next(){
                if(!hasNext()) {
                    throw new NoSuchElementException();
                }
                Spam aux = getData()[getCurr()];
                setCurr(getCurr()+1);
                return aux;
            }
        };
    }
    //Methods:

    /**
     * initiate the Spam DB
     * @param location  location of the spam file
     */
    private void initiateData(String location) {
        DoublyLinkedList<Spam> list = new DoublyLinkedList<>(); //create a list to hold the spams
        File spamWords = new File(location);
        Spam aux = new Spam();
        try {
            Scanner input = new Scanner(spamWords);
            while(input.hasNextLine()){
                String line = input.nextLine();
                if(line.length()==0){continue;}
                aux= handleLine(list,line,aux);//handling a line
            }
        }
        catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
        createArrayFromList(list);  //creating an array from the list
    }

    /**
     * Handaling a line for the Spam message
     * @param list the list we will insert the spam word into
     * @param line  the line we handling
     * @param aux   a spam message to be inserted into the list
     * @return  new spam message
     */
    private Spam handleLine(DoublyLinkedList<Spam> list, String line, Spam aux){
        int i;
        for(i=0;line.charAt(i)!=' ';i++);
        aux.setWord(line.substring(0,i));
        aux.setRatio(Float.parseFloat(line.substring(i+1)));
        list.addLast(aux);
        return new Spam();
    }

    /**
     * Creating array from the list we created of the spam messages
     * @param list  the list
     */
    private void createArrayFromList(DoublyLinkedList<Spam> list) {
        int size =list.getSize();
        Spam [] data = new Spam[size];
        for(int i=0;i<size;i++){
            data[i]=list.getHead().getData();
            list.setHead(list.getHead().getNext());
        }
        setData(data);
    }
}
