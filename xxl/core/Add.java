package xxl.core;

import xxl.core.exception.InvalidDataTypeException;
import xxl.core.exception.NullContentException;

public class Add extends BinaryOperation{


    public Add(Content first, Content second){
        setOperationName("ADD");
        setArg(first, 1);
        setArg(second, 2);
    }

    /**
     *
     * @return Content Value - int
     * @throws NullContentException
     * @throws InvalidDataTypeException
     */
    @Override
    public int getValueAsInt() throws NullContentException, InvalidDataTypeException {
        try {
            return getArg(1).getValueAsInt() + getArg(2).getValueAsInt();
        }catch (NullContentException | InvalidDataTypeException e){
            throw e;
        }
    }

}
