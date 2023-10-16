package xxl.core;

import xxl.core.exception.UnrecognizedEntryException;

import java.io.Serial;
import java.io.Serializable;

public class CutBuffer implements Serializable {
    @Serial
    private static final long serialVersionUID = 202309102343L;

    private Gamma _clipboard;

    public Gamma getClipboard(){
        return _clipboard;
    }
    public void setClipboard(Gamma gamma){
        _clipboard = gamma;
    }
}