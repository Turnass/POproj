package xxl.core;

import xxl.core.exception.InvalidDataTypeException;
import xxl.core.exception.NullContentException;
import xxl.core.exception.UnrecognizedEntryException;

public abstract class BinaryOperation extends Operation{

    private BinaryOperations _operationName;
    private Content _firstArg;
    private Content _secondArg;


    public BinaryOperations getOperationName(){
        return _operationName;
    }
    public void setOperationName(BinaryOperations op){
        _operationName = op;
    }

    public Content getArg(int arg){
        if (arg == 1)
            return _firstArg;
        else
            return _secondArg;
    }
    public void setArg(Content content, int arg){
        if (arg == 1)
            _firstArg = content;
        else
            _secondArg = content;
    }
    @Override
    public String getValueAsString() throws InvalidDataTypeException {
        throw new InvalidDataTypeException("Not a String");
    }


    @Override
    public String printContent(){
        try {
            return getValueAsInt() + toString();
        }catch (NullContentException | InvalidDataTypeException e){
            return NullContent.VALUE + toString();
        }
    }

    @Override
    public String toString() {
        return "=" + getOperationName() + "(" + getArg(1).toString() + "," + getArg(2).toString() + ")";// Fixme print args
    }
}



