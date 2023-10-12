package xxl.core;

// FIXME import classes

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

import xxl.core.exception.InvalidGammaException;
import xxl.core.exception.UnrecognizedEntryException;

/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {

    @Serial
    private static final long serialVersionUID = 202308312359L;

    private int _numLines;
    private int _numColumns;
    private ArrayList<User> _users = new ArrayList<>();
    private Cell[][] _cells = null;
    private CutBuffer _cutBuffer;

    // FIXME define attributes
    // FIXME define contructor(s)
    public Spreadsheet(int numLines, int numColumns){
        _numLines = numLines;
        _numColumns = numColumns;
        _cells = new Cell[_numLines][_numColumns];
        for (int i = 0; i < _numLines; i++){
            for (int j = 0; j < _numColumns;j++){
                _cells[i][j] = new Cell(i,j);
                _cells[i][j].setContent(new NullContent());
            }
        }
    }
    public Spreadsheet(int numLines, int numColumns, User user){
        this(numLines, numColumns);
        addUser(user);
    }

    public int getNumLines(){
        return _numLines;
    }
    public int getNumColumns(){
        return _numColumns;
    }

    // FIXME define methods

    public Cell getCell(int line, int column){
        return _cells[line][column];
    }
    //public Gamma buildGamma(String gammaDescription){return new Gamma();}
    /**
   * Insert specified content in specified address.
   *
   * @param row the row of the cell to change
   * @param column the column of the cell to change
   * @param contentSpecification the specification of the content to put
   *        in the specified cell.
   */

  public void insertContent(int row, int column, Content contentSpecification) throws UnrecognizedEntryException /* FIXME maybe add exceptions */ {
      //FIXME implement method
      getCell(row - 1, column - 1).setContent(contentSpecification);

  }
  public void insertGammaContent(String range, Content content) throws InvalidGammaException {
      try {
          Gamma gamma = createGamma(range);
          gamma.insertContent(content);
      }catch (InvalidGammaException ex){
          throw ex;
      }

  }
  public void deleteGamaContent(String range) throws InvalidGammaException {
      try {
          Gamma gamma = createGamma(range);
          gamma.deleteContent();
      }catch (InvalidGammaException ex){
          throw ex;
      }
  }

  public void searchValue(String Value){}

  public void searchFunction(String operationName){}
  public void addUser(User user) {
        _users.add(user);
    }

 public Gamma createGamma(String range) throws InvalidGammaException /*throws ?*/{
        String[] gammaCoordinates;
        int firstRow, firstColumn, lastRow, lastColumn;


        if (range.indexOf(':') != -1){
            gammaCoordinates = range.split("[:;]");
            firstRow = Integer.parseInt(gammaCoordinates[0]);
            firstColumn = Integer.parseInt(gammaCoordinates[1]);
            lastRow = Integer.parseInt(gammaCoordinates[2]);
            lastColumn = Integer.parseInt(gammaCoordinates[3]);
        } else {
            gammaCoordinates = range.split(";");
            firstRow = lastRow = Integer.parseInt(gammaCoordinates[0]);
            firstColumn = lastColumn = Integer.parseInt(gammaCoordinates[1]);
        }
        if (lastRow > _numLines || lastColumn > _numColumns)
            throw new InvalidGammaException(range);
        return new Gamma(firstRow, lastRow, firstColumn, lastColumn, this);
  }
}
