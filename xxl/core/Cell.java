package xxl.core;

        import java.io.Serial;
        import java.io.Serializable;
        import java.util.HashSet;
        import java.util.Set;

/**
 * Represents a Cell within a spreadsheet.
 */
public class Cell implements Serializable {

    @Serial
    private static final long serialVersionUID = 202310102342L;

    private int _line;
    private int _column;
    private Content _content;
    private Set<Observer> _observers = new HashSet<>();

    /**
     * Constructor for the Cell class.
     *
     * @param line   The line number of the cell.
     * @param column The column number of the cell.
     */
    public Cell(int line, int column) {
        _line = line;
        _column = column;
    }

    /**
     * Adds an observer to the cell.
     *
     * @param obs The observer to add.
     * @return True if the observer is added successfully, false otherwise.
     */
    public boolean add(Observer obs) {
        return _observers.add(obs);
    }

    /**
     * Removes an observer from the cell.
     *
     * @param obs The observer to remove.
     * @return True if the observer is removed successfully, false otherwise.
     */
    public boolean remove(Observer obs) {
        return _observers.remove(obs);
    }

    /**
     * Notifies all the registered observers about a content change in the cell.
     */
    private void notifyObservers() {
        for (Observer obs : _observers)
            obs.update();
    }

    /**
     * Retrieves the content of the cell.
     *
     * @return The content within the cell.
     */
    public Content getContent() {
        return _content;
    }

    /**
     * Sets the content of the cell and notifies the observers.
     *
     * @param content The content to be set.
     */
    public void setContent(Content content) {
        _content = content;
        notifyObservers();
    }

    /**
     * Prints the cell content and its position.
     *
     * @return The string representation of the cell and its content.
     */
    public String printCell() {
        return toString() + "|" + _content.printContent();
    }

    /**
     * Returns the string representation of the cell position.
     *
     * @return The string showing the line and column of the cell.
     */
    @Override
    public String toString() {
        int line = _line;
        int column = _column;
        return line + ";" + column;
    }
}
