package xxl.core;

import xxl.core.exception.UnrecognizedEntryException;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Gamma implements Serializable {

    @Serial
    private static final long serialVersionUID = 202310210001L;
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

    public Gamma createClipboard(){
        int size = getSize();
        int firstLine, lastLine, firstColumn, lastColumn;
        firstLine = lastLine = firstColumn = lastColumn = 1;
        Spreadsheet sheet;
        if (isVertical()) {
            lastLine = size;
            sheet = new Spreadsheet(lastLine, lastColumn);
            for (int i = 0; i < lastLine; i++){
                sheet.getCell(i, firstColumn - 1).setContent(_spreadsheet.getCell(_firstRow + i, _firstColumn).getContent());
            }
        }else {
            lastColumn = size;
            sheet = new Spreadsheet(lastLine, lastColumn);
            for (int i = 0; i < lastColumn; i++){
                sheet.getCell(firstLine - 1, i).setContent(_spreadsheet.getCell(_firstRow, _firstColumn + i).getContent());
            }
        }
        return new Gamma(firstLine, lastLine, firstColumn, lastColumn, sheet);
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
    public void insertContentsUntilEnd(Gamma gamma){
        if (gamma.isVertical()){
            for (int i = gamma._firstRow; i <= gamma._lastRow; i++){
                if (i + _firstRow >= _spreadsheet.getNumLines())
                    return;
                _spreadsheet.getCell(_firstRow + i, _firstColumn).setContent(gamma._spreadsheet.getCell(i, gamma._firstColumn).getContent());
            }
        }else {
            for (int i = gamma._firstColumn; i <= gamma._lastColumn; i++) {
                if (i + _firstColumn >= _spreadsheet.getNumColumns())
                    return;
                _spreadsheet.getCell(_firstRow, _firstColumn + i).setContent(gamma._spreadsheet.getCell(gamma._firstRow, i).getContent());
            }
        }
    }

    public void insertGamma(Gamma gamma){
       if (gamma == null) {
           return;
       }else if (_firstRow == _lastRow && _firstColumn == _lastColumn){
           insertContentsUntilEnd(gamma);
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

    public void addObserver(Observer obs){
        for (int i = _firstRow; i <= _lastRow; i++){
            for (int j = _firstColumn; j<= _lastColumn; j++){
                _spreadsheet.getCell(i,j).add(obs);
            }
        }
    }

    @Override
    public String toString() {
        return  _spreadsheet.getCell(_firstRow, _firstColumn) + ":" + _spreadsheet.getCell(_lastRow, _lastColumn);
    }

    private boolean isVertical(){
        if (_firstRow != _lastRow)
            return true;
        return false;
    }
}
