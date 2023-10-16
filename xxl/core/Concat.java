package xxl.core;

import xxl.core.exception.InvalidDataTypeException;
import xxl.core.exception.NullContentException;

public class Concat extends GamaOperation{


    public Concat(Gamma gamma){
        setOperationName(GamaOperations.CONCAT);
        setArg(gamma);
    }
    @Override
    public String getValueAsString() {
        String res = "";
        Gamma gamma = getArg();
        Spreadsheet sheet = gamma.getSpreadsheet();
        for (int i = gamma.getPos(Position.FIRSTROW); i <= gamma.getPos(Position.LASTROW); i++){
            for (int j = gamma.getPos(Position.FIRSTCOLUMN); j<= gamma.getPos(Position.LASTCOLUMN); j++){
                try {
                    String tmp = sheet.getCell(i,j).getContent().getValueAsString();
                    res += tmp;
                }catch (InvalidDataTypeException | NullContentException e){
                    res += "";
                }
            }
        }
        return res;

    }

    @Override
    public int getValueAsInt() throws InvalidDataTypeException {
        throw new InvalidDataTypeException("Not a Integer");
    }


    @Override
    public String printContent() {
        return "'" + getValueAsString() + toString() ;
    }
}
