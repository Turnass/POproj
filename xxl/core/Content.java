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

    public abstract boolean accept(SearchVisitor v, Cell cell);
    public void accept(ObserverVisitor v, Observer obs){
        //Do nothing, override in reference
    }
    public abstract String printContent();



}
