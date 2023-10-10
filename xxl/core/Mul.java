package xxl.core;

public class Mul extends BinaryOperation{
    public Mul(Content first, Content second){
        setOperationName(BinaryOperations.SUB);
        setArg(first, 1);
        setArg(second, 2);
    }

    @Override
    public int getValueAsInt() {
        return getArg(1).getValueAsInt() + getArg(2).getValueAsInt();
    }

    @Override
    public String printContent() {
        return getValueAsInt() + toString();
    }

    @Override
    public String toString() {
        return "=" + getOperationName() + "(" + getArg(1).toString() + "," + getArg(2).toString() + ")";// Fixme print args
    }
}