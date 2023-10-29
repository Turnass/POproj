package xxl.core;

import xxl.core.exception.InvalidDataTypeException;
import xxl.core.exception.NullContentException;

/**
 * Represents an 'Average' operation for a Gamma.
 */
public class Average extends GamaOperation {

    // The average value result
    private int _value;

    /**
     * Constructor for the Average class.
     *
     * @param gamma The Gamma for the 'Average' operation.
     */
    public Average(Gamma gamma) {
        // Set the operation name
        setOperationName("AVERAGE");
        // Set the argument
        setArg(gamma);
        // Add observers
        addObservers();
    }

    /**
     * Throws an exception because getting a String from an average operation is not supported.
     *
     * @return Doesn't return as an exception is thrown.
     * @throws InvalidDataTypeException Thrown because the operation does not return a String.
     */
    @Override
    public String getValueAsString() throws InvalidDataTypeException {
        // Cannot get a String from an average operation
        throw new InvalidDataTypeException("Not a String");
    }

    /**
     * Gets the average value as an integer.
     *
     * @return The calculated average value as an integer.
     * @throws InvalidDataTypeException Thrown if the operation cannot return a String.
     * @throws NullContentException    Thrown if there's null content in the operation.
     */
    @Override
    public int getValueAsInt() throws InvalidDataTypeException, NullContentException {
        // Check if the value has changed
        if (!getHasChanged()) {
            return _value;
        }
        int res = 0;
        int numCell = 0;
        Gamma gamma = getArg();
        Spreadsheet sheet = gamma.getSpreadsheet();
        for (int i = gamma.getPos(Position.FIRSTROW); i <= gamma.getPos(Position.LASTROW); i++) {
            for (int j = gamma.getPos(Position.FIRSTCOLUMN); j <= gamma.getPos(Position.LASTCOLUMN); j++) {
                try {
                    int tmp = sheet.getCell(i, j).getContent().getValueAsInt();
                    res += tmp;
                    numCell++;
                } catch (InvalidDataTypeException | NullContentException e) {
                    throw e;
                }
            }
        }
        int finalRes = res / numCell;
        _value = finalRes;
        setHasChanged(false);
        return finalRes;
    }

    /**
     * Prints the average integer value as a string.
     *
     * @return The string representation of the calculated average value.
     */
    @Override
    public String printContent() {
        try {
            return getValueAsInt() + toString();
        } catch (InvalidDataTypeException | NullContentException e) {
            return NullContent.VALUE + toString();
        }
    }
}
