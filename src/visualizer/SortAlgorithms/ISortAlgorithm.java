package visualizer.SortAlgorithms;

/**
 * Author: Shane Gaymon
 */

public interface ISortAlgorithm {

    /**
     * this method will start the running of the sort
     */

    public void runSort(int []toSort);

    /**
     * gets a new unsorted board
     * @param size - size of the board that will be sorted
     */

    public int[] getUnsortedBoard(int size);

    /**
     * randomizes an array so that it can be sorted later
     * @param size - size of the board that will be sorted
     */

    public int[] randomize(int size);

    /**
     * swaps two indexes in the array argument
     */

    public void swap(int index1, int index2, int []arr);

    /**
     * prints the sorted board to command line
     */

    public void printSorted();

    /**
     * prints the unsorted board to command line
     */

    public void printUnsorted();

    /**
     * sets the unsorted board
     */

    public void setUnsorted(int []arr);

    /**
     * returns the unsorted board
     */

    public int[] getUnsorted();

    /**
     * returns the sorted board
     */

    public int[] getSorted();


}
