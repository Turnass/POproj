package xxl.core;
public class Div extends BinaryOperation{
    public Div(Content first, Content second){
        setOperationName(BinaryOperations.SUB);
        setArg(first, 1);
        setArg(second, 2);
    }

    @Override
    public int getValueAsInt() {
        return getArg(1).getValueAsInt() / getArg(2).getValueAsInt();
    }

}