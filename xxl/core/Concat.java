package xxl.core;

import xxl.core.exception.InvalidDataTypeException;
import xxl.core.exception.NullContentException;

/**
 * Represents a Concatenation operation for a Gamma.
 */
public class Concat extends GamaOperation {

    // The concatenated value result
    private String _value;

    /**
     * Constructor for the Concat class.
     *
     * @param gamma The Gamma for the Concatenation operation.
     */
    public Concat(Gamma gamma) {
        // Set the operation name
        setOperationName("CONCAT");
        // Set the argument
        setArg(gamma);
        // Add observers
        addObservers();
    }

    /**
     * Gets the concatenated value as a String.
     *
     * @return The concatenated value as a String.
     */
    @Override
    public String getValueAsString() {
        // Check if the value has changed
        if (!getHasChanged()) {
            // Return the previous value
            return _value;
        }
        // Concatenation process
        String res = "";
        Gamma gamma = getArg();
        Spreadsheet sheet = gamma.getSpreadsheet();
        for (int i = gamma.getPos(Position.FIRSTROW); i <= gamma.getPos(Position.LASTROW); i++) {
            for (int j = gamma.getPos(Position.FIRSTCOLUMN); j <= gamma.getPos(Position.LASTCOLUMN); j++) {
                try {
                    // Get the value from the cell in the spreadsheet and concatenate
                    String tmp = sheet.getCell(i, j).getContent().getValueAsString();
                    res += tmp;
                } catch (InvalidDataTypeException | NullContentException e) {
                    res += "";
                }
            }
        }
        // Set the new value
        _value = res;
        setHasChanged(false);
        return res;
    }

    /**
     * Throws an exception because getting an integer from concatenation is not supported.
     *
     * @return Doesn't return as an exception is thrown.
     * @throws InvalidDataTypeException Thrown because the operation does not return an integer.
     */
    @Override
    public int getValueAsInt() throws InvalidDataTypeException {
        // Cannot get an integer from concatenation
        throw new InvalidDataTypeException("Not an Integer");
    }

    /**
     * Prints the concatenated string value.
     *
     * @return The string representing the concatenated value.
     */
    @Override
    public String printContent() {
        // Return the concatenated string value
        return "'" + getValueAsString() + toString();
    }
}
