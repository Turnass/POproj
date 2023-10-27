package xxl.core;

import xxl.core.exception.InvalidDataTypeException;
import xxl.core.exception.NullContentException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SearchValue implements SearchVisitor{
    ArrayList<String> result = null;
    String _value;
    public SearchValue(String value){
        _value = value;
        result = new ArrayList<>();
    }

    @Override
    public boolean visit(Reference reference, Cell cell){
        try {
            if (_value.charAt(0) == '\''){
                if (reference.getValueAsString().equals(_value.substring(1))){
                    result.add(cell.printCell());
                    return true;
                }
            }else{
                if (reference.getValueAsInt() == Integer.parseInt(_value)) {
                    result.add(cell.printCell());
                    return true;
                }
            }
        }catch (InvalidDataTypeException | NullContentException ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean visit(Literal literal, Cell cell) {
        if (_value.charAt(0) == '\'') {
            if (literal.toString().equals(_value.substring(1))) {
                result.add(cell.printCell());
                return true;
            }
        }else if (literal.toString().equals(_value)) {
            result.add(cell.printCell());
            return true;
        }
        return false;
    }

    @Override
    public boolean visit(Operation op, Cell cell) {
        try {
            if(_value.charAt(0) == '\'') {
                if (op.getValueAsString().equals(_value.substring(1))) {
                    result.add(cell.printCell());
                    return true;
                }
            }else {
                if (op.getValueAsInt() == Integer.parseInt(_value)) {
                    result.add(cell.printCell());
                    return true;
                }
                return false;
            }
        }catch (InvalidDataTypeException | NullContentException ex){
            ex.printStackTrace();
        }
        return false;
    }

    public ArrayList<String> getResult(){
        return result;
    }
}
