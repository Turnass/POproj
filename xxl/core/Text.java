package xxl.core;

public class Text extends Literal{
    private String _value;

    public Text(String value){
        _value = value;
    }

    @Override
    public String getValueAsString() {
        return null;
    }

    @Override
    public int getValueAsInt(){
        //throw new ...;
        return 0;
    }

    public String toString(){
        return _value;
    }

    @Override
    public String printContent() {return toString();}
}
