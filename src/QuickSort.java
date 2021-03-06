/*
Parallel QuickSort.
After the pivot is placed in the correct 
position, sorts both sides at the same time
in parallel. 
When there are 5,000 elements, it switches to
sorting it sequentially
*/

import java.util.Comparator;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class QuickSort {
    @SuppressWarnings("rawtypes")
    static Comparator comparator;
    static final ForkJoinPool POOL = new ForkJoinPool();
    static final int SeqCutOff = 10000;
        
    public static <E extends Comparable<E>> void parallelSort(E[] array) {
        QuickSort.parallelSort(array, (x, y) -> x.compareTo(y));
    }

    public static <E> void parallelSort(E[] array, Comparator<E> compare) {
        comparator = compare;
        POOL.invoke(new ParallelSort(array, 0, array.length));
        // quicksort(array, 0, array.length - 1);
    }

    public static <E extends Comparable<E>> void sort(E[] array) {
        QuickSort.sort(array, (x, y) -> x.compareTo(y));
    }

    public static <E> void sort(E[] array, Comparator<E> compare) {
        comparator = compare;
        quicksort(array, 0, array.length - 1);
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
                QuickSort.quicksort(arr, low, high - 1);
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


        private void swap(int x, int y) {
            E temp = arr[x];
            arr[x] = arr[y];
            arr[y] = temp;
        }
    }













   
}
