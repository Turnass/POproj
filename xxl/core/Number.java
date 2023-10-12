package xxl.core;

import xxl.core.exception.UnrecognizedEntryException;

public class Number extends Literal{

    private int _value;
    public Number(int number){
        _value = number;
    }

    public int getValueAsInt(){
        return _value;
    }
    public String getValueAsString() throws UnrecognizedEntryException {
        throw new UnrecognizedEntryException("Not a String");
    }

    public String toString(){
        return "" + _value;
    }

    @Override
    public String printContent() {
        return toString();
    }
}
