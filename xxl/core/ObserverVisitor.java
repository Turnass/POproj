package xxl.core;

public interface ObserverVisitor {
    /**
     * Visits the reference
     * @param reference
     * @param obs
     */
    void visit(Reference reference, Observer obs);
}
