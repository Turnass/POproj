package xxl.core;

import java.util.ArrayList;

public class Gamma {
    private Spreadsheet _spreadsheet;
    private int _firstRow;
    private int _lastRow;
    private int _firstColumn;
    private int _lastColumn;
    public Gamma(int firstRow, int lastRow, int firstColumn, int lastColumn, Spreadsheet sheet){
        _firstRow = firstRow - 1;
        _lastRow = lastRow - 1;
        _firstColumn = firstColumn - 1;
        _lastColumn = lastColumn - 1;
        _spreadsheet = sheet;
    }
    public ArrayList<String> getGamma(){
        ArrayList<String> _gamma = new ArrayList<>();
        for (int i = _firstRow; i <= _lastRow; i++){
            for (int j = _firstColumn; j <= _lastColumn; j++){
                _gamma.add(_spreadsheet.getCell(i,j).printCell());
            }
        }
        return _gamma;
    }

    public void insertContent(Content content){
        for (int i = _firstRow; i <= _lastRow; i++){
            for (int j = _firstColumn; j<= _lastColumn; j++){
                _spreadsheet.getCell(i, j).setContent(content);
            }
        }
    }

    //public void getContent(){}

    public void deleteContent(){}

    @Override
    public String toString() {
        return  _spreadsheet.getCell(_firstRow, _firstColumn) + ":" + _spreadsheet.getCell(_lastRow, _lastColumn);
    }
}
