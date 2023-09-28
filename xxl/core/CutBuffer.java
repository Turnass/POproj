package xxl.core;
public class CutBuffer {
    private Gamma _clipboard;

    public void copy(Gamma gamma){
        _clipboard = gamma;
    }
    public void paste(Gamma gamma){


    }
    public void cut(Gamma gamma){

    }

    public void showClipboard(){
        _clipboard.printGamma();
    }
}