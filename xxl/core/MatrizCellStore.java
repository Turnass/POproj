package xxl.core;

import java.io.Serial;
import java.io.Serializable;

public class MatrizCellStore implements CellStoreStrategy, Serializable {

    @Serial
    private static final long serialVersionUID = 202310250302L;


    // Cells representation
    private Cell[][] _cells;

    public MatrizCellStore(int numLines, int numColumns){
        _cells = new Cell[numLines][numColumns];
        for (int i = 0; i < numLines; i++) {
            for (int j = 0; j < numColumns; j++){
                _cells[i][j] = new Cell(i + 1,j + 1);
                _cells[i][j].setContent(new NullContent());
            }
        }
    }

    /**
     * @param line
     * @param column
     * @return the cell that is in the posicion (line, column)
     */
    public Cell getCell(int line, int column){
        return _cells[line - 1][column - 1];
    }
}
