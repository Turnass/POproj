package xxl.core;

        import xxl.core.exception.InvalidDataTypeException;
        import xxl.core.exception.NullContentException;

/**
 * Represents an abstract Binary Operation for content.
 */
public abstract class BinaryOperation extends Operation {

    // First argument of the Binary Operation
    private Content _firstArg;

    // Second argument of the Binary Operation
    private Content _secondArg;

    /**
     * Get the argument at the specified index.
     *
     * @param arg Index of the argument to retrieve (1 or 2).
     * @return The argument at the specified index.
     */
    public Content getArg(int arg) {
        if (arg == 1)
            return _firstArg;
        else
            return _secondArg;
    }

    /**
     * Set the argument at the specified index.
     *
     * @param content The content to set as an argument.
     * @param arg     Index of the argument (1 or 2).
     */
    public void setArg(Content content, int arg) {
        if (arg == 1)
            _firstArg = content;
        else
            _secondArg = content;
    }

    /**
     * Throws an exception because getting a String from a binary operation is not supported.
     *
     * @return Doesn't return as an exception is thrown.
     * @throws InvalidDataTypeException Thrown because the operation does not return a String.
     */
    @Override
    public String getValueAsString() throws InvalidDataTypeException {
        // Cannot get a String from a binary operation
        throw new InvalidDataTypeException("Not a String");
    }

    /**
     * Prints the Binary Operation content.
     *
     * @return The string representation of the binary operation content.
     */
    @Override
    public String printContent() {
        try {
            return getValueAsInt() + toString();
        } catch (NullContentException | InvalidDataTypeException | ArithmeticException e) {
            return NullContent.VALUE + toString();
        }
    }

    /**
     * Returns the string representation of the Binary Operation.
     *
     * @return The formatted string representing the Binary Operation.
     */
    @Override
    public String toString() {
        return "=" + getOperationName() + "(" + getArg(1).toString() + "," + getArg(2).toString() + ")";
    }
}

