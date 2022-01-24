package visualizer.SortAlgorithms;

import visualizer.SortingView;

/**
 * this class implements the Insertion Sort sorting algorithm
 *
 * Author: Shane Gaymon
 */

public class InsertionSort implements ISortAlgorithm {

    private SortingView view;
    private int [] unsorted;
    private int [] sorted;

    public InsertionSort(SortingView v){
        view = v;
    }

    public void runSort(int[] toSort) {
        sorted = toSort;
        int n = sorted.length;

        for(int i = 1; i < n; i++){
            int val = sorted[i];
            int j = i - 1;

            // move values greater than key to one position ahead
            while(j >= 0 && sorted[j] > val){
                sorted[j + 1] = sorted[j];
                view.updateStep(sorted, j + 1, j);
                j--;
            }
            sorted[j + 1] = val;
            view.updateSingle(j + 1, val);
        }

    }

    public void swap(int index1, int index2, int[] arr) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    public int[] getUnsortedBoard(int size) {
        unsorted = randomize(size);

        return unsorted;
    }

    public int[] randomize(int size) {
        int []random = new int[size];

        for(int k = 0; k < size; k++){
            random[k] = k + 1;
        }

        for(int i = 0; i < size; i++){
            int randIndex = (int) (Math.random() * size);
            int tmp = random[i];
            random[i] = random[randIndex];
            random[randIndex] = tmp;
        }

        return random;
    }


    public void printSorted() {
        for(int i = 0; i < sorted.length; i++){
            System.out.print(sorted[i] + " ");
        }
        System.out.println();
    }

    public void printUnsorted() {
        for(int i = 0; i < unsorted.length; i++){
            System.out.print(unsorted[i] + " ");
        }
        System.out.println();
    }

    public void setUnsorted(int[] arr) {
        unsorted = arr;
    }

    public int[] getUnsorted() {
        return unsorted;
    }

    public int[] getSorted() {
        return sorted;
    }



}
