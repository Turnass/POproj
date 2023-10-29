package xxl.core;

public abstract class Literal extends Content{

    @Override
    /**
     * Accepts the visitor
     */
    public boolean accept(SearchVisitor v, Cell cell) {
        if(v.visit(this, cell))
            return true;
        return false;
    }
}
