package visualizer.SortAlgorithms;

import visualizer.SortingView;

/**
 * this class implements the Merge Sort sorting algorithm
 *
 * Author: Shane Gaymon
 */

public class MergeSort implements ISortAlgorithm {

    private SortingView view;
    private int []unsorted;
    private int []sorted;

    private boolean doneSorting = false;

    public MergeSort(SortingView v){
        view = v;
    }

    public void runSort(int[] toSort) {
        sorted = toSort;
        sort(toSort, 0, sorted.length - 1);
    }

    public void sort(int []toSort, int l, int h){

        if(l < h){
            // have to figure out when to send to the view
            //int m = l + (h - 1)/2;
            int m = (l + h)/2;
            sort(toSort, l, m);
            sort(toSort, m + 1, h);
            //view.updateStep(toSort, m + 1, h);
            merge(toSort, l, m, h);
            //view.updateStep(toSort, m + 1, h);
        }


    }

    public void merge(int []toSort, int l, int m, int h){
        int i, j, k;
        int n1 = m - l + 1;
        int n2 = h - m;

        int []left = new int[n1];
        int []right = new int[n2];

        for(i = 0; i < n1; i++){
            left[i] = toSort[l + i];
        }

        for(j = 0; j < n2; j++){
            right[j] = toSort[m + 1 + j];
        }

        i = 0;
        j = 0;
        k = l;

        while(i < n1 && j < n2){
            if(left[i] <= right[j]){
                toSort[k] = left[i];
                view.updateSingle(k, left[i]);
                i++;
            }
            else {
                toSort[k] = right[j];
                view.updateSingle(k, right[j]);
                j++;
            }
            k++;
        }

        while(i < n1){
            toSort[k] = left[i];
            view.updateSingle(k, left[i]);
            i++;
            k++;
        }

        while(j < n2){
            toSort[k] = right[j];
            view.updateSingle(k, right[j]);
            j++;
            k++;
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
