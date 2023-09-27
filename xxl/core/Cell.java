package xxl.core;

public class Cell {

    private int _line;
    private int _column;
    private Content _content;

    public Cell(int line, int column){
        _line = line;
        _column = column;
    }

    public Content getContent(){
        return _content;
    }

}
