package xxl.core;

import xxl.core.exception.UnrecognizedEntryException;

import java.io.Serial;
import java.io.Serializable;

public abstract class Content implements Serializable {

    @Serial
    private static final long serialVersionUID = 202309102341L;
    public abstract String getValueAsString() throws UnrecognizedEntryException;
    public abstract int getValueAsInt() throws UnrecognizedEntryException;

    public abstract String printContent() throws UnrecognizedEntryException;

    public boolean isNull(){
        // Default result is false, override on NullContent
        return false;
    }

}
