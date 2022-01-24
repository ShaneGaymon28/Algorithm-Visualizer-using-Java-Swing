package visualizer.SortAlgorithms;

import visualizer.SortingView;

/**
 * this class implements the Heap Sort sorting algorithm
 *
 * Author: Shane Gaymon
 */

public class HeapSort implements ISortAlgorithm {

    private SortingView view;
    private int [] unsorted;
    private int [] sorted;

    public HeapSort(SortingView v){
        view = v;
    }


    public void runSort(int[] toSort) {
        sorted = toSort;
        int n = sorted.length;

        for(int i = n/2 - 1; i >= 0; i--){
            heapify(toSort, n, i);
        }


        for(int i = n - 1; i >= 0; i--){
            swap(0, i, toSort);
            view.updateStep(toSort, 0, i);
            heapify(toSort, i,0);
        }

    }

    public void heapify(int toSort[], int n, int i){
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if(l < n && toSort[l] > toSort[largest]){
            largest = l;
        }

        if(r < n && toSort[r] > toSort[largest]){
            largest = r;
        }

        if(largest != i){
            swap(i, largest, toSort);
            view.updateStep(toSort, i, largest);
            heapify(toSort, n, largest);
        }
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

    public void swap(int index1, int index2, int[] arr) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
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
