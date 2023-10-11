package xxl.core;

public class Add extends BinaryOperation{


    public Add(Content first, Content second){
        setOperationName(BinaryOperations.ADD);
        setArg(first, 1);
        setArg(second, 2);
    }

    @Override
    public int getValueAsInt() {
        return getArg(1).getValueAsInt() + getArg(2).getValueAsInt();
    }

}
