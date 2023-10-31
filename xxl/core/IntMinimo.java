package xxl.core;

import xxl.core.exception.InvalidDataTypeException;
import xxl.core.exception.NullContentException;

public class IntMinimo extends GamaOperation{

    private int _value;
    public IntMinimo(Gamma gamma){
        setOperationName("INTMINIMO");
        setArg(gamma);
        addObservers();
    }
    @Override
    public String getValueAsString() throws InvalidDataTypeException {
        throw new InvalidDataTypeException("Not a String");
    }

    @Override
    public int getValueAsInt() throws InvalidDataTypeException, NullContentException {
        if (!getHasChanged()) {
            return _value;
        }
        Integer res = null;
        Gamma gamma = getArg();
        Spreadsheet sheet = gamma.getSpreadsheet();
        for (int i = gamma.getPos(Position.FIRSTROW); i <= gamma.getPos(Position.LASTROW); i++){
            for (int j = gamma.getPos(Position.FIRSTCOLUMN); j<= gamma.getPos(Position.LASTCOLUMN); j++){
                try {
                    int tmp = sheet.getCell(i,j).getContent().getValueAsInt();
                    if (res == null){
                        res = tmp;
                    }else{
                        if (tmp < res){
                          res = tmp;
                        }
                    }
                }catch (InvalidDataTypeException | NullContentException e){
                    e.printStackTrace();
                }
            }
        }
        if (res == null){
          throw new InvalidDataTypeException("Doesn't have Integers");
        }
        _value = res;
        setHasChanged(false);
        return _value;
    }

    @Override
    public String printContent() {
        try {
            return getValueAsInt() + toString();
        }catch (InvalidDataTypeException | NullContentException e){
            return NullContent.VALUE + toString();
        }
    }
}


