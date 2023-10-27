package xxl.core;

public abstract class Literal extends Content{

    @Override
    public boolean accept(SearchVisitor v) {
        if(v.visit(this))
            return true;
        return false;
    }
}
