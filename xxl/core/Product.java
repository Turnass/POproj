package xxl.core;

import xxl.core.exception.InvalidDataTypeException;
import xxl.core.exception.NullContentException;

public class Product extends GamaOperation{


    private int _value;
    public Product(Gamma gamma){
        setOperationName(GamaOperations.PRODUCT);
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
        int res = 1;
        Gamma gamma = getArg();
        Spreadsheet sheet = gamma.getSpreadsheet();
        for (int i = gamma.getPos(Position.FIRSTROW); i <= gamma.getPos(Position.LASTROW); i++){
            for (int j = gamma.getPos(Position.FIRSTCOLUMN); j<= gamma.getPos(Position.LASTCOLUMN); j++){
                try {
                    int tmp = sheet.getCell(i,j).getContent().getValueAsInt();
                    res *= tmp;
                }catch (InvalidDataTypeException | NullContentException e){
                    throw e;
                }
            }
        }
        _value = res;
        setHasChanged(false);
        return res;

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
