package xxl.core;

public interface CellStoreStrategy {
    /**
     * Returns the cell in the specified address
     * @param line
     * @param column
     * @return
     */
    public Cell getCell(int line, int column);
}
