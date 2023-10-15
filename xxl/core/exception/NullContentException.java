package xxl.core.exception;

public class NullContentException extends Exception{
    /** Null content message. */
    private static final String MESSAGE = "Conteudo Nulo";

    public NullContentException() {
        super(MESSAGE);
    }

    /**
     * @param cause
     */
    public NullContentException(Exception cause) {
        super(MESSAGE, cause);
    }

    /**
     * @return the bad entry specification.
     */
    public String getMessage() {
        return MESSAGE;
    }
}

