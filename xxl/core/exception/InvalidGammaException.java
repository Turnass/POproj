package xxl.core.exception;

public class InvalidGammaException extends Exception{
    private final String _range;

    /** @param range  */
    public InvalidGammaException(String range) {
        super("A gama '" + range + "' é inválida.");
        _range = range;
    }

    public final String getInvalidRange() {
        return _range;
    }
}
