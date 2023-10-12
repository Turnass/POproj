package xxl.core.exception;

public class UnknownFunctionException extends Exception{
    private final String _functionName;

    /** @param functionName  */
    public UnknownFunctionException(String functionName) {
        super("A função '" + functionName + "' é desconhecida.");
        _functionName = functionName;
    }

    public final String getFunctionName() {
        return _functionName;
    }
}
