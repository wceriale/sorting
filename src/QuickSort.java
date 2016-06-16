/*
Sequential QuickSort implemention using median largest
index as pivot

-------------------------------------
 < pivot |    pivot       | > pivot |
-------------------------------------

*/

import java.util.Comparator;

public class QuickSort {
    @SuppressWarnings("rawtypes")
    static Comparator comparator;
        
    public static <E extends Comparable<E>> void sort(E[] array) {
        QuickSort.sort(array, (x, y) -> x.compareTo(y));
    }

    public static <E> void sort(E[] array, Comparator<E> compare) {
        comparator = compare;
        quicksort(array, 0, array.length - 1);
    }
    
    
    
    @SuppressWarnings("unchecked")
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
    
    // puts smallest value on the left, places pivot and greatest farthest to the right
    // @SuppressWarnings("unchecked")
    // private static <E> void medianOf3(E[] arr, int low, int high) {
    //     int middle = (high  + low)/2;
    //     E hi = arr[high];
    //     E lo = arr[low];
    //     E mid = arr[middle];
        
        

    //     if(comparator.compare(hi, lo) > 0) {       // hi > lo
    //        if(comparator.compare(lo, mid) > 0) {   // hi > lo > mid
    //            swap(arr, low, middle);
    //        } else if(comparator.compare(mid, hi) > 0) {  // mid > hi > lo
    //            swap(arr, middle, high);
    //        }
    //     } else {                                  // lo > hi
    //         if(comparator.compare(hi, mid) > 0) { // lo > hi > mid
    //             swap(arr, low, high);
    //             swap(arr, low, middle);
    //         } else {                         // lo > mid > hi
    //             swap(arr, low, high);
    //         }
    //     }
        
    //     swap(arr, middle, high - 1); 
        
    // }

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

   
}
