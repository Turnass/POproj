package xxl.core;

import xxl.core.exception.UnrecognizedEntryException;

public class Reference extends Content{

    private Cell _cell;

    public Reference(Cell cell){
        _cell = cell;
    }
    @Override
    public String getValueAsString() throws UnrecognizedEntryException {
        return _cell.getContent().getValueAsString();
    }

    @Override
    public int getValueAsInt() throws UnrecognizedEntryException {
        return _cell.getContent().getValueAsInt();
    }


    @Override
    public String toString() {
        return "=" + _cell.toString();
    }

    @Override
    public String printContent(){
        try {
            return getValueAsString() + toString();
        }catch (UnrecognizedEntryException e){}

        try {
            return getValueAsInt() + toString();
        }catch (UnrecognizedEntryException e){}

        String str = "#VALUE";
        return str + toString();
    }
}