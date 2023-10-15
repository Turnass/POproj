package xxl.core;

import xxl.core.exception.InvalidDataTypeException;
import xxl.core.exception.UnrecognizedEntryException;

public class Number extends Literal{

    private int _value;
    public Number(int number){
        _value = number;
    }

    /**
     *
     * @return Literal value - int
     */
    public int getValueAsInt(){
        return _value;
    }

    /**
     *
     * @throws InvalidDataTypeException
     */
    public String getValueAsString() throws InvalidDataTypeException {
        throw new InvalidDataTypeException("Not a String");
    }

    public String toString(){
        return "" + _value;
    }

    @Override
    public String printContent() {
        return toString();
    }
}
