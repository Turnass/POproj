package xxl.core;

import xxl.core.exception.InvalidDataTypeException;
import xxl.core.exception.NullContentException;

import java.util.ArrayList;

public interface SearchVisitor extends Visitor{
    public boolean visit(Operation op, Cell cell);
    public boolean visit(Reference reference, Cell cell);
    public boolean visit(Literal literal, Cell cell);
    public ArrayList<String> getResult();
}
