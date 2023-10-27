package xxl.core;

import xxl.core.exception.InvalidDataTypeException;
import xxl.core.exception.NullContentException;
import xxl.core.exception.UnrecognizedEntryException;

public class Mul extends BinaryOperation{
    public Mul(Content first, Content second){
        setOperationName("MUL");
        setArg(first, 1);
        setArg(second, 2);
    }

    /**
     *
     * @return Content value - int
     * @throws InvalidDataTypeException
     * @throws NullContentException
     */
    @Override
    public int getValueAsInt() throws InvalidDataTypeException, NullContentException {
        try {
            return getArg(1).getValueAsInt() * getArg(2).getValueAsInt();
        }catch (NullContentException | InvalidDataTypeException e){
           throw e;
        }
    }
}