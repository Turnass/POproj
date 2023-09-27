package xxl.core;

public class Reference extends Content{

    private Cell _cell;
    public boolean isLiteral(){
        return false;
    }

    public Literal getValue(){
        return _cell.getContent().getValue();
    }
}