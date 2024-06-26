package xxl.core;

import xxl.core.exception.InvalidDataTypeException;
import xxl.core.exception.NullContentException;
import xxl.core.exception.UnrecognizedEntryException;

public class Reference extends Content{

    private Cell _cell;

    public Reference(Cell cell){
        _cell = cell;
    }

    @Override
    public String getValueAsString() throws InvalidDataTypeException, NullContentException {
        return _cell.getContent().getValueAsString();
    }

    @Override
    public int getValueAsInt() throws InvalidDataTypeException, NullContentException {
        return _cell.getContent().getValueAsInt();
    }

    @Override
    public boolean accept(SearchVisitor v, Cell cell) {
        if(v.visit(this, cell))
            return true;
        return false;
    }

    public void accept(ObserverVisitor v, Observer obs){
        v.visit(this, obs);
    }

    public Cell getCell(){
        return _cell;
    }

    @Override
    public String toString() {
        return _cell.toString();
    }

    @Override
    public String printContent(){
        String str = NullContent.VALUE;
        try {
            return "'" + getValueAsString() + "=" + toString();
        }catch (InvalidDataTypeException | NullContentException e){
            e.printStackTrace();
        }
        try {
            return getValueAsInt() + "=" + toString();
        }catch (InvalidDataTypeException | NullContentException e){
            e.printStackTrace();
        }

        return str + "=" + toString();
    }
}