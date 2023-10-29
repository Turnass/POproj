package xxl.core;

import xxl.core.exception.InvalidDataTypeException;
import xxl.core.exception.NullContentException;

/**
 * Represents the 'COALESCE' operation to a Gamma.
 */
public class Coalesce extends GamaOperation {

    private String _value;

    /**
     * Constructor for the Coalesce operation.
     *
     * @param gamma The Gamma object to set as an argument for this operation.
     */
    public Coalesce(Gamma gamma) {
        setOperationName("COALESCE");
        setArg(gamma);
        addObservers();
    }

    /**
     * Retrieves the value of this operation as a string.
     *
     * @return The value as a string.
     */
    @Override
    public String getValueAsString() {
        if (!getHasChanged()) {
            return _value;
        }
        String res = "";
        Gamma gamma = getArg();
        Spreadsheet sheet = gamma.getSpreadsheet();
        boolean found = false;
        for (int i = gamma.getPos(Position.FIRSTROW); i <= gamma.getPos(Position.LASTROW); i++) {
            for (int j = gamma.getPos(Position.FIRSTCOLUMN); j <= gamma.getPos(Position.LASTCOLUMN); j++) {
                Content content = sheet.getCell(i, j).getContent();
                try {
                    res = content.getValueAsString();
                    found = true;
                    break;
                } catch (InvalidDataTypeException | NullContentException e) {
                    e.printStackTrace();
                }
            }
            if (found) {
                break;
            }
        }
        _value = res;
        setHasChanged(false);
        return res;
    }

    /**
     * Retrieves the value of this operation as an integer.
     *
     * @return The value as an integer.
     * @throws InvalidDataTypeException If the data is not of integer type.
     */
    @Override
    public int getValueAsInt() throws InvalidDataTypeException {
        throw new InvalidDataTypeException("Not an Integer");
    }

    /**
     * Prints the content of the 'COALESCE' operation.
     *
     * @return The content as a string.
     */
    @Override
    public String printContent() {
        return "'" + getValueAsString() + toString();
    }
}

