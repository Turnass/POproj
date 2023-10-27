package xxl.core;

public interface CellStoreStrategy {
    public Cell getCell(int line, int column);
    public int getNumLines();
    public int getNumColumns();
}
