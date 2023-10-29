package xxl.core;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a subset of cells (Gamma) within a spreadsheet.
 */
public class Gamma implements Serializable {

    @Serial
    private static final long serialVersionUID = 202310210001L;
    private Spreadsheet _spreadsheet;
    private int _firstRow;
    private int _lastRow;
    private int _firstColumn;
    private int _lastColumn;

    /**
     * Constructor for the Gamma class.
     *
     * @param firstRow     The first row of the gamma.
     * @param lastRow      The last row of the gamma.
     * @param firstColumn  The first column of the gamma.
     * @param lastColumn   The last column of the gamma.
     * @param sheet        The spreadsheet to which the gamma belongs.
     */
    public Gamma(int firstRow, int lastRow, int firstColumn, int lastColumn, Spreadsheet sheet){
        _firstRow = firstRow;
        _lastRow = lastRow;
        _firstColumn = firstColumn;
        _lastColumn = lastColumn;
        _spreadsheet = sheet;
    }

    /**
     * Calculates the total number of cells in the gamma.
     *
     * @return The total count of cells within the gamma.
     */
    public int getSize(){
        int res = 0;
        for (int i = _firstRow; i <= _lastRow; i++){
            for (int j = _firstColumn; j <= _lastColumn; j++){
                res++;
            }
        }
        return res;
    }

    /**
     * Retrieves the contents of all cells within the gamma.
     *
     * @return An array list containing the content of each cell in the gamma.
     */
    public ArrayList<Content> getContents(){
        ArrayList<Content> contents = new ArrayList<>();
        for (int i = _firstRow; i <= _lastRow; i++){
            for (int j = _firstColumn; j<= _lastColumn; j++){
                contents.add(_spreadsheet.getCell(i,j).getContent());
            }
        }
        return contents;
    }

    /**
     * Sets the content of cells within the gamma.
     *
     * @param contents An array list containing the contents to be set for each cell in the gamma.
     */
    public void setContents(ArrayList<Content> contents){
        int contentNumber = 0;
        for (int i = _firstRow; i <= _lastRow; i++){
            for (int j = _firstColumn; j<= _lastColumn; j++){
                _spreadsheet.getCell(i,j).setContent(contents.get(contentNumber));
                contentNumber++;
            }
        }
    }

    /**
     * Creates a clipboard
     * @return Gamma
     */
    public Gamma createClipboard(){
        int size = getSize();
        int firstLine, lastLine, firstColumn, lastColumn;
        firstLine = lastLine = firstColumn = lastColumn = 1;
        Spreadsheet sheet;
        if (isVertical()) {
            lastLine = size;
            sheet = new Spreadsheet(lastLine, lastColumn);
            for (int i = 1; i <= lastLine; i++){
                sheet.getCell(i, firstColumn).setContent(_spreadsheet.getCell(_firstRow + i - 1, _firstColumn).getContent());
            }
        }else {
            lastColumn = size;
            sheet = new Spreadsheet(lastLine, lastColumn);
            for (int i = 1; i <= lastColumn; i++){
                sheet.getCell(firstLine, i).setContent(_spreadsheet.getCell(_firstRow, _firstColumn + i - 1).getContent());
            }
        }
        return new Gamma(firstLine, lastLine, firstColumn, lastColumn, sheet);
    }

    /**
     * Prints the gamma
     * @return Arraylist of Strings
     */
    public ArrayList<String> printGamma() {
        ArrayList<String> _gamma = new ArrayList<>();
        for (int i = _firstRow; i <= _lastRow; i++){
            for (int j = _firstColumn; j <= _lastColumn; j++){
                _gamma.add(_spreadsheet.getCell(i,j).printCell());
            }
        }
        return _gamma;
    }

    /**
     * Insert the given content in the gamma
     * @param content
     */
    public void insertContent(Content content){
        for (int i = _firstRow; i <= _lastRow; i++){
            for (int j = _firstColumn; j<= _lastColumn; j++){
                _spreadsheet.getCell(i, j).setContent(content);
            }
        }
    }

    /**
     * Insert contents to the end of the spreadsheet
     * @param gamma
     */
    public void insertContentsUntilEnd(Gamma gamma){
        if (gamma.isVertical()){
            for (int i = gamma._firstRow; i <= gamma._lastRow; i++){
                if (i + _firstRow - 1 > _spreadsheet.getNumLines())
                    return;
                _spreadsheet.getCell(_firstRow + i - 1, _firstColumn).setContent(gamma._spreadsheet.getCell(i, gamma._firstColumn).getContent());
            }
        }else {
            for (int i = gamma._firstColumn; i <= gamma._lastColumn; i++) {
                if (i + _firstColumn - 1> _spreadsheet.getNumColumns())
                    return;
                _spreadsheet.getCell(_firstRow, _firstColumn + i - 1).setContent(gamma._spreadsheet.getCell(gamma._firstRow, i).getContent());
            }
        }
    }

    /**
     * @param gamma
     */
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

    /**
     * @param pos
     * @return
     */
    public int getPos(Position pos) {
       return switch (pos){
           case FIRSTROW -> _firstRow;
           case LASTROW -> _lastRow;
           case FIRSTCOLUMN -> _firstColumn;
           case LASTCOLUMN -> _lastColumn;
       };
    }

    /**
     * @return the spreadsheet
     */
    public Spreadsheet getSpreadsheet(){
        return _spreadsheet;
    }


    public void deleteContent(){
        for (int i = _firstRow; i <= _lastRow; i++){
            for (int j = _firstColumn; j<= _lastColumn; j++){
                _spreadsheet.getCell(i, j).setContent(new NullContent());
            }
        }
    }

    /**
     * Add observer to the cells
     * @param obs
     */
    public void addObserver(Observer obs){
        ObserverVisitor visitor = new ReferenceObserver();
        for (int i = _firstRow; i <= _lastRow; i++){
            for (int j = _firstColumn; j<= _lastColumn; j++){
                Cell cell = _spreadsheet.getCell(i,j);
                cell.add(obs);
                cell.getContent().accept(visitor, obs);
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
