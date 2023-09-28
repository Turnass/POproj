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

    public void setContent(Content content){
        _content = content;
    }

    public String printCell(){
        return _line + ";" + _column + "|" + getContent().printContent();
    }
    @Override
    public String toString() {
        return _line + ";" + _column + "|" + getContent().toString();
    }
}
