package xxl.core;

import xxl.core.exception.InvalidDataTypeException;
import xxl.core.exception.NullContentException;

public class Coalesce extends GamaOperation{


    private String _value;
    public Coalesce(Gamma gamma){
        setOperationName(GamaOperations.COALESCE);
        setArg(gamma);
        addObservers();
    }
    @Override
    public String getValueAsString() {
        if (!getHasChanged()){
            return _value;
        }
        String res = "";
        Gamma gamma = getArg();
        Spreadsheet sheet = gamma.getSpreadsheet();
        for (int i = gamma.getPos(Position.FIRSTROW); i <= gamma.getPos(Position.LASTROW); i++){
            for (int j = gamma.getPos(Position.FIRSTCOLUMN); j<= gamma.getPos(Position.LASTCOLUMN); j++) {
                Content content = sheet.getCell(i, j).getContent();
                if (content.isString()){
                    res = content.toString();
                    break;
                }
            }
        }
        _value = res;
        setHasChanged(false);
        return res;
    }

    @Override
    public int getValueAsInt() throws InvalidDataTypeException {
        throw new InvalidDataTypeException("Not a Integer");
    }

    @Override
    public String printContent() {
        return "'" + getValueAsString() + toString();
    }
}
