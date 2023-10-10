package xxl.core;

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
    public String getValueAsString() {
        return null;
    }



}
