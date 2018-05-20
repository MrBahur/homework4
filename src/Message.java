public class Message {

    private String recipient;
    private String sender;
    private String text;

    public Message(){
        this.recipient="";
        this.sender="";
        this.text="";
    }
    public Message(String recipient,String sender, String text){
        this.recipient = recipient;
        this.sender = sender;
        this.text = text;
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

