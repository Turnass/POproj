package xxl.core;

import java.util.ArrayList;
import java.util.Collections;

public class SearchFunctions implements SearchVisitor{

    ArrayList<String> res = null;
    String _operationName;
    public SearchFunctions(String operationName){
        _operationName = operationName;
    }
    @Override
    public void visit(CellStoreStrategy cellStorage) {
        res = new ArrayList<>();
        int lines = cellStorage.getNumLines();
        int columns = cellStorage.getNumColumns();
        for (int i = 1; i <= lines; i++){
            for (int j = 1; j <= columns; j++){
                Cell cell = cellStorage.getCell(i,j);
                if (cell.getContent().isFunction() && cell.getContent().toString().contains(_operationName)){
                    res.add(cell.printCell());
                }
            }
        }
        Collections.sort(res, new FunctionComparator());
    }

    @Override
    public ArrayList<String> getResult() {
        return res;
    }
}
