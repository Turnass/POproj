package xxl.core;

import java.io.Serial;
import java.io.Serializable;

public class CutBuffer implements Serializable {
    @Serial
    private static final long serialVersionUID = 202309102343L;

    private Gamma _clipboard;

    public void copy(Gamma gamma){
        _clipboard = gamma;
    }
    public void paste(Gamma gamma){


    }
    public void cut(Gamma gamma){

    }

    public void showClipboard(){
        _clipboard.getGamma();
    }
}