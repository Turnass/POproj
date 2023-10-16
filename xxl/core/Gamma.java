package xxl.core;

import xxl.core.exception.UnrecognizedEntryException;

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

    public int getSize(){
        int res = 0;
        for (int i = _firstRow; i <= _lastRow; i++){
            for (int j = _firstColumn; j <= _lastColumn; j++){
                res++;
            }
        }
        return res;
    }

    public ArrayList<Content> getContents(){
        ArrayList<Content> contents = new ArrayList<>();
        for (int i = _firstRow; i <= _lastRow; i++){
            for (int j = _firstColumn; j<= _lastColumn; j++){
                contents.add(_spreadsheet.getCell(i,j).getContent());
            }
        }
        return contents;
    }

    public void setContents(ArrayList<Content> contents){
        int contentNumber = 0;
        for (int i = _firstRow; i <= _lastRow; i++){
            for (int j = _firstColumn; j<= _lastColumn; j++){
                _spreadsheet.getCell(i,j).setContent(contents.get(contentNumber));
                contentNumber++;
            }
        }
    }
    public Gamma makeDeepCopy(){
        Spreadsheet sheet = new Spreadsheet(_lastRow + 1, _lastColumn + 1);
        for (int i = 0; i < sheet.getNumLines(); i++){
            for (int j = 0; j< sheet.getNumColumns(); j++){
                sheet.getCell(i,j).setContent(_spreadsheet.getCell(i,j).getContent());
            }
        }
        return new Gamma(_firstRow + 1, _lastRow + 1, _firstColumn + 1, _lastColumn + 1, sheet);
    }

    public ArrayList<String> printGamma() {
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

    public void insertGamma(Gamma gamma){
       if (gamma == null) {
           return;
       }else if (_firstRow == _lastRow && _firstColumn == _lastColumn){

       }else if (this.getSize() != gamma.getSize()){
           return;
       }else{
            setContents(gamma.getContents());
       }
    }

    public int getPos(Position pos) {
       return switch (pos){
           case FIRSTROW -> _firstRow;
           case LASTROW -> _lastRow;
           case FIRSTCOLUMN -> _firstColumn;
           case LASTCOLUMN -> _lastColumn;
       };
    }

    public Spreadsheet getSpreadsheet(){
        return _spreadsheet;
    }

    //public void getContent(){}

    public void deleteContent(){
        for (int i = _firstRow; i <= _lastRow; i++){
            for (int j = _firstColumn; j<= _lastColumn; j++){
                _spreadsheet.getCell(i, j).setContent(new NullContent());
            }
        }
    }

    @Override
    public String toString() {
        return  _spreadsheet.getCell(_firstRow, _firstColumn) + ":" + _spreadsheet.getCell(_lastRow, _lastColumn);
    }
}
