package xxl.core;

import xxl.core.exception.UnrecognizedEntryException;

public class Text extends Literal{
    private String _value;

    public Text(String value){
        _value = value;
    }

    @Override
    public String getValueAsString() {
        return _value;
    }

    @Override
    public int getValueAsInt() throws UnrecognizedEntryException {
        throw new UnrecognizedEntryException("Not an Integer");
    }

    public String toString(){
        return _value;
    }

    @Override
    public String printContent() {return toString();}
}
