package xxl.core;

public class Number extends Content{

    private int _value;
    public Number(int number){
        _value = number;
    }

    public int getValueAsInt(){
        return _value;
    }
    public String getValueAsString(){
    //    throw new ...;
        return "";
    }

    public String toString(){
        return "" + _value;
    }

    @Override
    public String printContent() {
        return toString();
    }
}