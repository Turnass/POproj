package xxl.core;

import xxl.core.exception.InvalidDataTypeException;
import xxl.core.exception.NullContentException;

import java.util.ArrayList;

public class SearchValue implements SearchVisitor{
    private ArrayList<String> res = null;
    private String _value;
    public SearchValue(String value){
        _value = value;
    }
    @Override
    public void visit(CellStoreStrategy cellStorage) {
        res = new ArrayList<>();
        int numLines = cellStorage.getNumLines();
        int numColumns = cellStorage.getNumColumns();
        if (_value.charAt(0) == '\'') {
            for (int i = 1; i <= numLines; i++) {
                for (int j = 1; j <= numColumns; j++) {
                    try {
                        Cell tmp = cellStorage.getCell(i,j);
                        if (tmp.getContent().getValueAsString().equals(_value.substring(1))){
                            res.add(tmp.printCell());
                        }
                    }catch (InvalidDataTypeException | NullContentException e){
                        e.printStackTrace();
                    }
                }
            }
        }else{
            for (int i = 1; i <= numLines; i++) {
                for (int j = 1; j <= numColumns; j++) {
                    try {
                        Cell tmp = cellStorage.getCell(i,j);
                        if (tmp.getContent().getValueAsInt() == Integer.parseInt(_value)){
                            res.add(tmp.printCell());
                        }
                    }catch (InvalidDataTypeException | NullContentException e){
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    @Override
    public ArrayList<String> getResult() {
        return res;
    }
}
