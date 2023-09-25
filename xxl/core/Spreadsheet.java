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
    private Cell[] _cells = null;

    // FIXME define attributes
    // FIXME define contructor(s)
    public Spreadsheet(int numLines, int numColumns, User user){
        _numLines = numLines;
        _numColumns = numColumns;
        _cells = new Cell[_numLines *  _numColumns];
        addUser(user);
    }

    // FIXME define methods

    /**
     * Insert specified content in specified range.
     *
     * @param rangeSpecification
     * @param contentSpecification
     */
    public void insertContents(String rangeSpecification, String contentSpecification) throws UnrecognizedEntryException /* FIXME maybe add exceptions */ {
        //FIXME implement method
    }

    public void addUser(User user) {
        _users.add(user);
    }
}
