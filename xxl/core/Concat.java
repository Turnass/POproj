package xxl.core;

public class Concat extends GamaOperation{


    public Concat(Gamma gamma){
        setArg(gamma);
    }
    @Override
    public String getValueAsString() {
        return null;
    }

    @Override
    public int getValueAsInt() {
        return 0;
    }

    @Override
    public String printContent() {
        return null;
    }
}
