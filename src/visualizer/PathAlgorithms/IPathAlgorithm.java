package visualizer.PathAlgorithms;

/**
 * Author: Shane Gaymon
 */

public interface IPathAlgorithm {

    public void findPath(Cell start, Cell end);

    public void findEdges(Cell curCell);

    public Cell[][] getNewGrid(double density, int rows, int cols);

    public boolean checkIndex(int r, int c);

}
