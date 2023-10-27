package xxl.core;

public abstract class Operation extends Content{

    private String _operationName;
    public String getOperationName(){
        return _operationName;
    }
    public void setOperationName(String name){
        _operationName = name;
    }

    @Override
    public boolean accept(SearchVisitor v, Cell cell) {
        if(v.visit(this, cell))
            return true;
        return false;
    }
}
