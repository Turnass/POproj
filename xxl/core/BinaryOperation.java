package xxl.core;

public abstract class BinaryOperation extends Operation{

    private BinaryOperations _operationName;
    private Cell _firstArg;
    private Cell _secondArg;


    public BinaryOperations getOperationName(){
        return _operationName;
    }
    public void setOperationName(BinaryOperations op){
        _operationName = op;
    }

    public Cell getArg(int arg){
        if (arg == 1)
            return _firstArg;
        else
            return _secondArg;
    }
    public void setArg(Cell cell, int arg){
        if (arg == 1)
            _firstArg = cell;
        else
            _secondArg = cell;
    }
    @Override
    public String getValueAsString() {
        return null;
    }



}
