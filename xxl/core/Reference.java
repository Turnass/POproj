package xxl.core;

public class Reference extends Content{

    private Cell _cell;

    public Reference(Cell cell){
        _cell = cell;
    }
    @Override
    public String getValueAsString() {
        return _cell.getContent().getValueAsString();
    }

    @Override
    public int getValueAsInt() {
        return _cell.getContent().getValueAsInt();
    }


    @Override
    public String toString() {
        return "=" + _cell.toString();
    }

    @Override
    public String printContent() {
        return "";
    }
}