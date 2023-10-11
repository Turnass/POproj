package xxl.core;

public class Sub extends BinaryOperation{

    public Sub(Content first, Content second){
        setOperationName(BinaryOperations.SUB);
        setArg(first, 1);
        setArg(second, 2);
    }

    @Override
    public int getValueAsInt() {
        return getArg(1).getValueAsInt() - getArg(2).getValueAsInt();
    }

}