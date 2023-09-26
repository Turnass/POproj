package xxl.core;

public class Cell {

    private int _line;
    private int _column;
    private Spreadsheet _spreadsheet;
    private Content _content;

    public Cell(int line, int column, Spreadsheet spreadsheet){
        _line = line;
        _column = column;
        _spreadsheet = spreadsheet;
    }

}
