package visualizer.SortAlgorithms;

import visualizer.SortingView;

/**
 * this class implements the Quicksort sorting algorithm
 *
 * Author: Shane Gaymon
 */

public class Quicksort implements ISortAlgorithm {

    private SortingView view;
    private int [] unsorted;
    private int [] sorted;

    private boolean doneSorting = false;

    public Quicksort(SortingView v){
        view = v;
    }


    public void runSort(int []toSort){
        quickSort(toSort, 0, toSort.length - 1);
    }


    public void quickSort(int []toSort, int low, int high){
        sorted = toSort;

        if(low < high){
            int index = partition(sorted, low, high);
            quickSort(sorted, low, index - 1);
            quickSort(sorted, index + 1, high);
        }

    }


    public int partition(int []toSort, int low, int high){

        int pivot = toSort[high];
        int k = low - 1;

        for(int i = low; i <= high - 1; i++){
            if(toSort[i] < pivot){
                k++;
                swap(k, i, sorted);
                view.updateStep(toSort, k, i);
            }
        }

        swap(k + 1, high, sorted);
        view.updateStep(toSort, k+1, high);

        return k + 1;
    }

    public void swap(int index1, int index2, int []arr){
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    public int[] getUnsortedBoard(int size){
        unsorted = randomize(size);

        return unsorted;
    }

    public int[] randomize(int size){
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


    public void printSorted(){
        for(int i = 0; i < sorted.length; i++){
            System.out.print(sorted[i] + " ");
        }
        System.out.println();
    }

    public void printUnsorted(){
        for(int i = 0; i < unsorted.length; i++){
            System.out.print(unsorted[i] + " ");
        }
        System.out.println();
    }

    public void setUnsorted(int []arr){
        unsorted = arr;
    }

    public int[] getUnsorted(){
        return unsorted;
    }

    public int[] getSorted(){
        return sorted;
    }

    public boolean getDoneSorting(){
        return doneSorting;
    }

}
