package xxl.core.exception;

public class InvalidDataTypeException extends Exception{
    /** Invalid Data Type entry specification. */
    private String _entrySpecification;

    /**
     * @param entrySpecification
     */
    public InvalidDataTypeException(String entrySpecification) {
        super(entrySpecification);
        _entrySpecification = entrySpecification;
    }

    /**
     * @param entrySpecification
     * @param cause
     */
    public InvalidDataTypeException(String entrySpecification, Exception cause) {
        super(entrySpecification,cause);
        _entrySpecification = entrySpecification;
    }

    /**
     * @return the bad entry specification.
     */
    public String getEntrySpecification() {
        return _entrySpecification;
    }
}
