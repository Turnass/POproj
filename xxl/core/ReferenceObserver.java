package xxl.core;

public class ReferenceObserver implements ObserverVisitor{


    @Override
    public void visit(Reference reference, Observer obs) {
        reference.getCell().add(obs);
        reference.getCell().getContent().accept(this, obs);
    }
}
