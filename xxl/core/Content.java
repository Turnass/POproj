package xxl.core;

import java.io.Serial;
import java.io.Serializable;

public abstract class Content implements Serializable {

    @Serial
    private static final long serialVersionUID = 202309102341L;
    public abstract String getValueAsString();
    public abstract int getValueAsInt();

    public abstract String printContent();

}
