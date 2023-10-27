package xxl.core;

import java.util.ArrayList;
import java.util.Collections;

public class SearchFunctions implements SearchVisitor{
    String _operationName;
    ArrayList<String> result;
    public SearchFunctions(String name){
        result = new ArrayList<>();
        _operationName = name;
    }

    @Override
    public boolean visit(Operation op, Cell cell) {
        if (op.getOperationName().contains(_operationName)){
            result.add(cell.printCell());
            return true;
        }
        return false;
    }

    @Override
    public boolean visit(Literal literal, Cell cell) {
        return false;
    }

    @Override
    public boolean visit(Reference reference, Cell cell) {
        return false;
    }

    public ArrayList<String> getResult(){
        Collections.sort(result, new FunctionComparator());
        return result;
    }
}
