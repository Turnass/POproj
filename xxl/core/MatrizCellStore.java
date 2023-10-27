package xxl.core;

import java.io.Serial;
import java.io.Serializable;

public class MatrizCellStore implements CellStoreStrategy, Serializable {

    @Serial
    private static final long serialVersionUID = 202310250302L;
    private Cell[][] _cells;
    private int _numLines;
    private int _numColumns;

    public MatrizCellStore(int numLines, int numColumns){
        _numLines = numLines;
        _numColumns = numColumns;
        _cells = new Cell[numLines][numColumns];
        for (int i = 0; i < numLines; i++) {
            for (int j = 0; j < numColumns; j++){
                _cells[i][j] = new Cell(i + 1,j + 1);
                _cells[i][j].setContent(new NullContent());
            }
        }
    }

    public Cell getCell(int line, int column){
        return _cells[line - 1][column - 1];
    }

    @Override
    public int getNumLines() {
        return _numLines;
    }

    @Override
    public int getNumColumns() {
        return _numColumns;
    }

    public void setCellContent(int line, int column, Content content){
        _cells[line][column].setContent(content);
    }


}
