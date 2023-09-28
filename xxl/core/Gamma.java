package xxl.core;

public class Gamma {
    private Cell _firstCell;
    private Cell _lastCell;

    public Gamma(){

    }
    public void printGamma(){}

    public void insertContent(Content content){
        /*for (Cell cell: _cells){
            cell.setContent(content);
        }*/
    }

    public void deleteContent(){}

    @Override
    public String toString() {
        return _firstCell.toString() + ":" + _lastCell.toString();
    }
}
