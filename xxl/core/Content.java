package xxl.core;

import xxl.core.exception.InvalidDataTypeException;
import xxl.core.exception.NullContentException;
import xxl.core.exception.UnrecognizedEntryException;

import java.io.Serial;
import java.io.Serializable;

public abstract class Content implements Serializable {

    @Serial
    private static final long serialVersionUID = 202309102341L;
    public abstract String getValueAsString() throws InvalidDataTypeException, NullContentException;
    public abstract int getValueAsInt() throws InvalidDataTypeException, NullContentException;

    public abstract String printContent();

    public boolean isString(){
        // Default result is false, override on Text
        return false;
    }

    public boolean isNull(){
        // Default result is false, override on NullContent
        return false;
    }

}
