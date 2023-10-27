package xxl.core;

import java.util.ArrayList;

public interface SearchVisitor {
    public void visit(CellStoreStrategy cellStorage);
    public ArrayList<String> getResult();
}
