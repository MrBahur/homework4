/*
pretty much straight forward...
3 strings, getters setters and constructors.
could have used Struct for that actually...
 */

public class Message {

    private String recipient;
    private String sender;
    private String text;


    private Message(String recipient,String sender, String text){
        this.recipient = recipient;
        this.sender = sender;
        this.text = text;
    }
    public Message(){
        this("","","");
    }

    /*
    Getters:
    ----------------------------------------
    */

    public String getRecipient() {
        return recipient;
    }

    public String getSender() {
        return sender;
    }

    public String getText() {
        return text;
    }

     /*
    Setters
    ----------------------------------------
    */

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setText(String text) {
        this.text = text;
    }
}

