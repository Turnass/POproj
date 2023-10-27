package xxl.core;

import xxl.core.exception.InvalidDataTypeException;
import xxl.core.exception.NullContentException;

import java.util.ArrayList;

public interface SearchVisitor {
    public boolean visit(Operation op);
    public boolean visit(Reference reference);
    public boolean visit(Literal literal);
}
