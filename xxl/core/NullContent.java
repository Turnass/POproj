package xxl.core;

import xxl.core.exception.NullContentException;
import xxl.core.exception.UnrecognizedEntryException;

public class NullContent extends Content{

    public static final String VALUE = "#VALUE";
    @Override
    public String getValueAsString() throws NullContentException {
        throw new NullContentException();
    }

    @Override
    public int getValueAsInt() throws NullContentException {
         throw new NullContentException();
    }

    @Override
    public boolean accept(SearchVisitor v, Cell cell) {
        return false;
    }

    @Override
    public String printContent() {
        return toString();
    }
    @Override
    public String toString(){
        return "";
    }
}
