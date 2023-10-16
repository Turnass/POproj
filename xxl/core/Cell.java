package xxl.core;

import xxl.core.exception.UnrecognizedEntryException;

import java.io.Serial;
import java.io.Serializable;

public class Cell implements Serializable {

    @Serial
    private static final long serialVersionUID = 202310102342L;
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

    public void setContent(Content content){
        _content = content;
    }

    public String printCell() {
        return toString() + "|" + _content.printContent();
    }
    @Override
    public String toString() {
        int line = _line + 1;
        int column = _column + 1;
        return line + ";" + column;
    }
}
