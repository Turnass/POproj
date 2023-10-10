package xxl.core;

public class Gamma {
    private Spreadsheet _spreadsheet;
    private int _firstRow;
    private int _lastRow;
    private int _firstColumn;
    private int _lastColumn;
    public Gamma(int firstRow, int lastRow, int firstColumn, int lastColumn, Spreadsheet sheet){
        _firstRow = firstRow;
        _lastRow = lastRow;
        _firstColumn = firstColumn;
        _lastColumn = lastColumn;
    }
    public void printGamma(){}

    public void insertContent(Content content){
        for (int i = _firstRow; i <= _lastRow; i++){
            for (int j = _firstColumn; j<= _lastColumn; j++){
                _spreadsheet[i][j].setContent(content);
            }
        }
    }

    //public void getContent(){}

    public void deleteContent(){}

    @Override
    public String toString() {
        return _firstCell.toString() + ":" + _lastCell.toString();
    }
}
