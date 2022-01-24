package visualizer.SortAlgorithms;

import visualizer.SortingView;
import java.util.*;

/**
 * THIS CLASS IS CURRENTLY BROKEN AND NOT IN USE
 *
 * Author: Shane Gaymon
 */

public class CountingSort implements ISortAlgorithm {

    private SortingView view;
    private int []unsorted;
    private int []sorted;

    public CountingSort(SortingView v){
        view = v;
    }


    public void runSort(int[] toSort) {
        sorted = toSort;
        int n = sorted.length;
        int max = Arrays.stream(sorted).max().getAsInt();
        int min = Arrays.stream(sorted).min().getAsInt();
        int range = max - min + 1;
        int count [] = new int[range];
        int output [] = new int[sorted.length];

        for(int i = 0; i < n; i++){
            count[toSort[i] - min]++;
            view.updateSingle(i, toSort[i]);

        }

        for(int i = 1; i < count.length; i++){
            count[i] += count[i-1];
        }

        for(int i = output.length - 1; i >= 0; i++){
            output[count[toSort[i] - min] - 1] = toSort[i];
            view.updateSingle(count[toSort[i] - min], toSort[i]);
            count[toSort[i] - min]--;
        }

        for(int i = 0; i < toSort.length; i++){
            toSort[i] = output[i];
        }

        view.updateStep(sorted, sorted.length - 2, sorted.length - 1);

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
