package xxl.core;

public abstract class GamaOperation extends Operation{

    private GamaOperations _operationName;
    private Gamma _gamma;

    enum GamaOperations{
        AVERAGE,
        PRODUCT,
        CONCAT,
        COALESCE;
    }
    public GamaOperations getOperationName(){
        return _operationName;
    }
    public void setOperationName(GamaOperations op){
        _operationName = op;
    }

    public Gamma getArg(){
       return _gamma;
    }
    public void setArg(Gamma gamma){
        _gamma = gamma;
    }


    public String toString(){
        return "=" + _operationName + "(" + _gamma.toString() + ")";
    }
}
