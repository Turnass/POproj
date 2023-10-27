package xxl.core;


import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

import xxl.core.exception.*;

/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {

    @Serial
    private static final long serialVersionUID = 202308312359L;

    private CellStoreStrategy _cellStoreStrategy;
    private int _numLines;
    private int _numColumns;
    private ArrayList<User> _users = new ArrayList<>();
    private CutBuffer _cutBuffer;
    private boolean _saved = true;

    public Spreadsheet(int numLines, int numColumns){
        _cellStoreStrategy = new MatrizCellStore(numLines, numColumns);
        _numLines = numLines;
        _numColumns = numColumns;
        _cutBuffer = new CutBuffer();
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


    /**
     *
     * @param line
     * @param column
     * @return Cell
     */
    public Cell getCell(int line, int column){
        return _cellStoreStrategy.getCell(line, column);
    }

    public boolean isSaved(){
        return _saved;
    }
    public void setSaved(boolean bool){
        _saved = bool;
    }
    /**
   * Insert specified content in specified address.
   *
   * @param row the row of the cell to change
   * @param column the column of the cell to change
   * @param contentSpecification the specification of the content to put
   *        in the specified cell.
   */

  public void insertContent(int row, int column, Content contentSpecification) throws UnrecognizedEntryException /* FIXME maybe add exceptions */ {
      getCell(row, column).setContent(contentSpecification);
  }

    /**
     *
     * @param range
     * @param content
     * @throws InvalidGammaException
     */
  public void insertGammaContent(String range, String content) throws InvalidGammaException, UnrecognizedEntryException, UnknownFunctionException {
      try {
          Parser parser = new Parser(this);
          Content parsedContent = parser.parseContent(content);
          Gamma gamma = createGamma(range);
          gamma.insertContent(parsedContent);
          setSaved(false);
      }catch (InvalidGammaException | UnknownFunctionException | UnrecognizedEntryException ex){
          throw ex;
      }
  }

    /**
     *
     * @param range
     * @throws InvalidGammaException
     */
  public void deleteGamaContent(String range) throws InvalidGammaException {
      try {
          Gamma gamma = createGamma(range);
          gamma.deleteContent();
          setSaved(false);
      }catch (InvalidGammaException ex){
          throw ex;
      }
  }
    public void copy(String range) throws InvalidGammaException {
      try {
          Gamma gamma = createGamma(range).createClipboard();
          _cutBuffer.setClipboard(gamma);
      }catch (InvalidGammaException e){
          throw e;
      }
    }
    public void paste(String range) throws InvalidGammaException {
      if (_cutBuffer.getClipboard() == null)
          return;
      try {
          Gamma gamma = createGamma(range);
          Gamma clipboard = _cutBuffer.getClipboard();
          gamma.insertGamma(clipboard);
          setSaved(false);
      } catch (InvalidGammaException e) {
          throw e;
      }
    }

    public void cut(String range) throws InvalidGammaException {
      try {
          Gamma gamma = createGamma(range);
          Gamma gammaCopy = gamma.createClipboard();
          _cutBuffer.setClipboard(gammaCopy);
          gamma.deleteContent();
          setSaved(false);
      } catch (InvalidGammaException e) {
          throw e;
      }
    }

    public ArrayList<String> showClipboard(){
      Gamma gamma = _cutBuffer.getClipboard();
      if (gamma == null)
          return null;
      return gamma.printGamma();
    }

  public void addUser(User user) {
      _users.add(user);
  }
/*
  public ArrayList<String> search(SearchVisitor visitor){
      visitor.visit(_cellStoreStrategy);
      return visitor.getResult();
  }*/

  public ArrayList<String> search(SearchVisitor visitor){
      ArrayList<String> res = new ArrayList<>();
      for (int i = 1; i <= _numLines; i++){
          for (int j = 1; j <= _numColumns; j++){
              Cell cell = getCell(i,j);
              if(cell.getContent().accept(visitor, cell))
                  res.add(cell.printCell());
          }
      }
      return visitor.getResult();
      //return res;
  }
    /**
     * creates a gamma by parsing the string range
     * @param range
     * @return
     * @throws InvalidGammaException
     */
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
        if (firstRow < 1 || firstColumn < 1 || lastRow > _numLines || lastColumn > _numColumns || notLinear(firstRow, lastRow, firstColumn, lastColumn))
            throw new InvalidGammaException(range);
        return new Gamma(firstRow, lastRow, firstColumn, lastColumn, this);
  }

  private boolean notLinear(int firstLine, int lastLine, int firstColumn, int lastColumn){
        if (firstLine != lastLine && firstColumn != lastColumn)
            return true;
        else
            return false;

  }
}
