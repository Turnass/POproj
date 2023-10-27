package xxl.core;

import java.util.ArrayList;
import java.util.Collections;

public class SearchFunctions implements SearchVisitor{
    String _operationName;
    public SearchFunctions(String name){
        _operationName = name;
    }

    @Override
    public boolean visit(Operation op) {
        if (op.getOperationName().contains(_operationName))
            return true;
        return false;
    }

    @Override
    public boolean visit(Literal literal) {
        return false;
    }

    @Override
    public boolean visit(Reference reference) {
        return false;
    }
}
