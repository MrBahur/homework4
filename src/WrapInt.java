/*
 *  a wraper for int
 *  so ill be able to send it to function by Reference and not by Value
 */
public class WrapInt {
    private int value;

    //Constructors
    public WrapInt(int value){
        this.value=value;
    }

    //Getters

    public int getValue() {
        return value;
    }

    //Setters

    public void setValue(int value) {
        this.value = value;
    }

}
