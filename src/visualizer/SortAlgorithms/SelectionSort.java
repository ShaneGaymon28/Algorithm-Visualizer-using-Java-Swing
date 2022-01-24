package visualizer.SortAlgorithms;

import visualizer.SortingView;

/**
 * this class implements the Selection Sort sorting algorithm
 *
 * Author: Shane Gaymon
 */

public class SelectionSort implements ISortAlgorithm {

    private SortingView view;
    private int []unsorted;
    private int []sorted;

    private boolean doneSorting = false;

    public SelectionSort(SortingView v){
        view = v;
    }


    public void runSort(int[] toSort) {
        sorted = toSort;
        int n = sorted.length;

        for(int i = 0; i < n - 1; i++){
            int min = i;
            for(int j = i + 1; j < n; j++){
                if(sorted[j] < sorted[min]){
                    min = j;
                }
            }
            swap(min, i, sorted);
            view.updateStep(sorted, min, i);
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


    public boolean getDoneSorting() {
        return doneSorting;
    }


}
