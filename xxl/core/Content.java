package xxl.core;

import xxl.core.exception.InvalidDataTypeException;
import xxl.core.exception.NullContentException;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents the abstraction of content in a cell.
 */
public abstract class Content implements Serializable {

    @Serial
    private static final long serialVersionUID = 202309102341L;

    /**
     * Retrieves the value of this content as a string.
     *
     * @return The content's value as a string.
     * @throws InvalidDataTypeException If the data type is invalid.
     * @throws NullContentException     If the content is null.
     */
    public abstract String getValueAsString() throws InvalidDataTypeException, NullContentException;

    /**
     * Retrieves the value of this content as an integer.
     *
     * @return The content's value as an integer.
     * @throws InvalidDataTypeException If the data type is invalid.
     * @throws NullContentException     If the content is null.
     */
    public abstract int getValueAsInt() throws InvalidDataTypeException, NullContentException;

    /**
     * Accepts a search visitor to execute the search operation for the provided cell.
     *
     * @param v    The search visitor.
     * @param cell The cell to perform the search operation.
     * @return True if the search is successful, false otherwise.
     */
    public abstract boolean accept(SearchVisitor v, Cell cell);

    /**
     * Accepts an observer visitor to execute the observation operation for the provided observer.
     *
     * @param v   The observer visitor.
     * @param obs The observer to perform the observation.
     */
    public void accept(ObserverVisitor v, Observer obs) {
        // Do nothing, override in reference
    }

    /**
     * Prints the content.
     *
     * @return The content as a string.
     */
    public abstract String printContent();
}
