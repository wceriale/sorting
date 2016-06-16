/*
Parallel Quicksort using Random Pivot Point selection

  sorted in parallel    sorted in parallel
       ^                   ^
-------------------------------------
 lo | < pivot | pivot| > pivot | hi |
-------------------------------------


*/

import java.util.Comparator;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelQuickSort {
    @SuppressWarnings("rawtypes")
    static Comparator comparator;
    static final ForkJoinPool POOL = new ForkJoinPool();
    static final int SeqCutOff = 10000;
        
    public static <E extends Comparable<E>> void sort(E[] array) {
        ParallelQuickSort.sort(array, (x, y) -> x.compareTo(y));
    }

    public static <E> void sort(E[] array, Comparator<E> compare) {
        comparator = compare;
        POOL.invoke(new ParallelSort(array, 0, array.length));
        // quicksort(array, 0, array.length - 1);
    }

    private static <E> void quicksort(E[] arr, int low, int high) {
        
        // If it's only 2 elements, do a quick check and swap if necessary.
        if(high - low == 1) {
            if(comparator.compare(arr[low], arr[high]) > 0)
                swap(arr, low, high);
        } else if(low < high) {                
            int pivotIndex = partition(arr, low, high);
            quicksort(arr, low, pivotIndex - 1);
            quicksort(arr, pivotIndex + 1, high);     
        }
    }

    @SuppressWarnings("unchecked")
    private static <E> int partition(E[] arr, int low, int high) {

        E pivotVal = arr[high];
        int i = low;
        int j = high - 1;
        for(;;) {
            while(comparator.compare(arr[i], pivotVal) < 0) { // arr[i] < pivotVal
                i++;
            }
            while(comparator.compare(arr[j], pivotVal) > 0) { // arr[j] > pivotVal
                j--;
            }
            if(i < j) {
                swap(arr, i, j);
                i++;
                j--;
            } else 
                break;

        }
        swap(arr, i, high);
        return i;
    }

    private static <E> void swap(E[] arr, int x, int y) {
        E temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
 

    private static class ParallelSort<E> extends RecursiveAction {
        E[] arr;
        int low, high;

        public ParallelSort(E[] arr, int lo, int hi) {
            this.arr = arr; this.low = lo; this.high = hi;
        }

        public void compute() {
            if(high - low < SeqCutOff) {
                ParallelQuickSort.quicksort(arr, low, high - 1);
            } else {
                int pivot = partition();
                ParallelSort left = new ParallelSort(arr, low, pivot);
                ParallelSort right = new ParallelSort(arr, pivot + 1, high);
                left.fork();
                right.compute();
                left.join();
            }
        }

        private int partition() {
            int high = this.high - 1;
            // int pivotIndex = pickPivot();
            E pivotVal = arr[high];
            int i = low;
            int j = high - 1;
            for(;;) {
                while(comparator.compare(arr[i], pivotVal) < 0) { // arr[i] < pivotVal
                    i++;
                }
                while(comparator.compare(arr[j], pivotVal) > 0) { // arr[j] > pivotVal
                    j--;
                }
                if(i < j) {
                    swap(i, j);
                    i++;
                    j--;
                } else 
                    break;

            }
            swap(i, high);
            return i;
        }

        // private int pickPivot() {
        //     int rand = low + (int) (Math.random() * (high - low - 1));
        //     return rand;
        // }

        // private void medianOf3() {
        //     int high = this.high - 1;
        //     int middle = (high + low)/2;
        //     E hi = arr[high];
        //     E lo = arr[low];
        //     E mid = arr[middle];
        //     if(comparator.compare(hi, lo) > 0) {       // hi > lo
        //        if(comparator.compare(lo, mid) > 0) {   // hi > lo > mid
        //            swap(low, middle);
        //        } else if(comparator.compare(mid, hi) > 0) {  // mid > hi > lo
        //            swap(middle, high);
        //        }
        //     } else {                                  // lo > hi
        //         if(comparator.compare(hi, mid) > 0) { // lo > hi > mid
        //             swap(low, high);
        //             swap(low, middle);
        //         } else {                         // lo > mid > hi
        //             swap(low, high);
        //         }
        //     }
            
        //     swap(middle, high); 
        // }

        private void swap(int x, int y) {
            E temp = arr[x];
            arr[x] = arr[y];
            arr[y] = temp;
        }
    }













   
}
