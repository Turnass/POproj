package xxl.core;

import xxl.core.exception.InvalidDataTypeException;
import xxl.core.exception.NullContentException;

public class Coalesce extends GamaOperation{


    public Coalesce(Gamma gamma){
        setOperationName(GamaOperations.COALESCE);
        setArg(gamma);
    }
    @Override
    public String getValueAsString() {
        Gamma gamma = getArg();
        Spreadsheet sheet = gamma.getSpreadsheet();
        for (int i = gamma.getPos(Position.FIRSTROW); i <= gamma.getPos(Position.LASTROW); i++){
            for (int j = gamma.getPos(Position.FIRSTCOLUMN); j<= gamma.getPos(Position.LASTCOLUMN); j++){
                Content content = sheet.getCell(i,j).getContent();
                if (content.isString())
                    return content.toString();
            }
        }
        return "";
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
