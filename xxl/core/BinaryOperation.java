package xxl.core;

public abstract class BinaryOperation extends Operation{

    private BinaryOperations _operationName;
    public enum BinaryOperations{
        ADD,
        SUB,
        MUL,
        DIV;
    }



}
