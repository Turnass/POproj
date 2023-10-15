package xxl.core;

import xxl.core.exception.InvalidDataTypeException;
import xxl.core.exception.UnrecognizedEntryException;

public class Text extends Literal{
    private String _value;

    public Text(String value){
        _value = value;
    }

    /**
     *
     * @return Literal Value - string
     */
    @Override
    public String getValueAsString() {
        return _value;
    }

    /**
     *
     * @throws InvalidDataTypeException
     */
    @Override
    public int getValueAsInt() throws InvalidDataTypeException {
        throw new InvalidDataTypeException("Not an Integer");
    }

    public String toString(){
        return _value;
    }

    @Override
    public String printContent() {return toString();}
}
