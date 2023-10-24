package xxl.core;

import xxl.core.exception.InvalidDataTypeException;
import xxl.core.exception.NullContentException;

public class Average extends GamaOperation{


    private int _value;
    public Average(Gamma gamma){
        setOperationName(GamaOperations.AVERAGE);
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
        int res = 0;
        int  numCell = 0;
        Gamma gamma = getArg();
        Spreadsheet sheet = gamma.getSpreadsheet();
        for (int i = gamma.getPos(Position.FIRSTROW); i <= gamma.getPos(Position.LASTROW); i++){
            for (int j = gamma.getPos(Position.FIRSTCOLUMN); j<= gamma.getPos(Position.LASTCOLUMN); j++){
                try {
                    int tmp = sheet.getCell(i,j).getContent().getValueAsInt();
                    res += tmp;
                    numCell++;
                }catch (InvalidDataTypeException | NullContentException e){
                    throw e;
                }
            }
        }
        int finalRes = res / numCell;
        _value = finalRes;
        setHasChanged(false);
        return finalRes;

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
