package xxl.core;

public class NullContent extends Content{

    @Override
    public String getValueAsString() {
        return null;
    }

    @Override
    public int getValueAsInt() {
        return 0;
    }

    @Override
    public String printContent() {
        return "";
    }
    public String toString(){
        return "";
    }
}
