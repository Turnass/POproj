package xxl.core;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a buffer for cut operations within the spreadsheet.
 */
public class CutBuffer implements Serializable {
    @Serial
    private static final long serialVersionUID = 202309102343L;

    private Gamma _clipboard;

    /**
     * Retrieves the content in the clipboard.
     *
     * @return The content in the clipboard.
     */
    public Gamma getClipboard() {
        return _clipboard;
    }

    /**
     * Sets the clipboard with the provided gamma content.
     *
     * @param gamma The gamma content to set in the clipboard.
     */
    public void setClipboard(Gamma gamma) {
        _clipboard = gamma;
    }
}
