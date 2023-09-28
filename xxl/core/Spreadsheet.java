package xxl.core;

// FIXME import classes

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

import xxl.core.exceptions.UnrecognizedEntryException;

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
    public Spreadsheet(int numLines, int numColumns, User user){
        _numLines = numLines;
        _numColumns = numColumns;
        _cells = new Cell[_numLines][_numColumns];
        addUser(user);

        for (int i = 0; i < _numLines; i++){
            for (int j = 0; j < _numColumns;j++){
                _cells[i][j] = null;
            }
        }
    }


    // FIXME define methods

    /**
   * Insert specified content in specified address.
   *
   * @param row the row of the cell to change
   * @param column the column of the cell to change
   * @param contentSpecification the specification in a string format of the content to put
   *        in the specified cell.
   */
  public void insertContent(int row, int column, String contentSpecification) throws UnrecognizedEntryException /* FIXME maybe add exceptions */ {
    //FIXME implement method
  }
  public void insertGamaContent(Gamma gamma, Content content){
      gamma.insertContent(content);
  }
  public void deleteGamaContent(Gamma gamma){
      gamma.deleteContent();
  }

    public void addUser(User user) {
        _users.add(user);
    }
}
