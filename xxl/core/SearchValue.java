package xxl.core;

import xxl.core.exception.InvalidDataTypeException;
import xxl.core.exception.NullContentException;

import java.util.ArrayList;

public class SearchValue implements SearchVisitor{
    String _value;
    public SearchValue(String value){
        _value = value;
    }

    @Override
    public boolean visit(Reference reference){
        try {
            if (_value.charAt(0) == '\''){
                if (reference.getValueAsString().equals(_value.substring(1))) {
                    return true;
                }
            }else{
                if (reference.getValueAsInt() == Integer.parseInt(_value)) {
                    return true;
                }
            }
        }catch (InvalidDataTypeException | NullContentException ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean visit(Literal literal) {
        if (_value.charAt(0) == '\'') {
            if (literal.toString().equals(_value.substring(1))) {
                return true;
            }
        }else if (literal.toString().equals(_value)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean visit(Operation op) {
        try {
            if(_value.charAt(0) == '\'')
                if (op.getValueAsString().equals(_value.substring(1)))
                    return true;
            else
                if (op.getValueAsInt() == Integer.parseInt(_value))
                    return true;
                return false;
        }catch (InvalidDataTypeException | NullContentException ex){
            ex.printStackTrace();
        }
        return false;
    }
}
