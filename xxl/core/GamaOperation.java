package xxl.core;

public abstract class GamaOperation extends Operation implements Observer{

    private Gamma _gamma;

    private boolean _hasChanged = true;
    enum GamaOperations{
        AVERAGE,
        PRODUCT,
        CONCAT,
        COALESCE;
    }

    @Override
    public void update() {
        _hasChanged = true;
    }
    public boolean getHasChanged(){
        return _hasChanged;
    }
    public void setHasChanged(boolean state){
        _hasChanged = state;
    }
    public void addObservers(){
        _gamma.addObserver(this);
    }

    public Gamma getArg(){
       return _gamma;
    }
    public void setArg(Gamma gamma){
        _gamma = gamma;
    }


    public String toString(){
        return "=" + getOperationName() + "(" + _gamma.toString() + ")";
    }
}
