// William Ceriale

import java.util.Comparator;

public class MergeSort {
	static Comparator comparator;


    public static <E extends Comparable<E>> void sort(E[] array) {
        MergeSort.sort(array, (x, y) -> x.compareTo(y));
    }

    public static <E> void sort(E[] array, Comparator<E> compare) {
        comparator = compare;
        E[] result = (E[]) new Object[array.length];
        mergesort(array, result);
        array = result;
    }

    public static <E> void mergesort(E[] arr, E[] result) {
    	if(arr.length > 1) {
    		E[] arr1 = (E[]) new Object[arr.length / 2];
    		E[] arr2 = (E[]) new Object[arr.length - arr1.length];
    		for(int i = 0; i < arr1.length; i++) 
    			arr1[i] = arr[i];
    		for(int i = 0; i < arr2.length; i++) 
    			arr2[i] = arr[i];
    		mergesort(arr1, result);
    		mergesort(arr2, result);
    		merge(result, arr1, arr2);
    	}
    }

    public static <E> void merge(E[] arr, E[] arr1, E[] arr2) {
    	int arr1i = 0;
    	int arr2i = 0;
    	int index = 0;
    	while(arr1i < arr1.length && arr2i < arr2.length) {
    		if(comparator.compare(arr1[arr1i], arr2[arr2i]) > 0) {
    			arr[index] = arr1[arr1i];
    			arr1i++;
    		} else {
    			arr[index] = arr2[arr2i];
    			arr2i++;
    		}
    		index++;
    	}

    	// Add remainding elements if any
    	while(arr1i < arr1.length) {
    		arr[index] = arr1[arr1i];
    		index++;
    		arr1i++;
    	}
    	while(arr2i < arr2.length) {
    		arr[index] = arr2[arr2i];
    		index++;
    		arr2i++;
    	}
    }
}







