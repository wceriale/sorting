// William Ceriale

import java.util.Comparator;


public class MergeSort {
	static Comparator comparator;


    public static <E extends Comparable<E>> void sort(E[] array) {
        MergeSort.sort(array, (x, y) -> x.compareTo(y));
    }

    public static <E> void sort(E[] array, Comparator<E> compare) {
        comparator = compare;

        // Store the sorted in temp array
        E[] result = mergesort(array);

        // Hard copy every value over to memory location in
        // passed array
        for(int i = 0; i < result.length; i++)
            array[i] = result[i];
    }

    public static <E> E[] mergesort(E[] arr) {
        // If it's a single element, just return it
    	if(arr.length == 1) 
            return arr;
        else {
            // Split array in half
    		E[] arr1 = (E[]) new Object[arr.length / 2];
    		E[] arr2 = (E[]) new Object[arr.length - arr1.length];

            // Fill halves with values
    		for(int i = 0; i < arr1.length; i++) 
    			arr1[i] = arr[i];
    		for(int i = 0; i < arr2.length; i++) 
    			arr2[i] = arr[i + arr1.length];

            // Recursively sort both parts of the array
    		arr1 = mergesort(arr1);
    		arr2 = mergesort(arr2);

            // Merge them together, return the result
    		return merge(arr1, arr2);
    	}
    }

    public static <E> E[] merge(E[] arr1, E[] arr2) {
        // Create new array which will be the result of the merge
        E[] arr = (E[]) (new Object[arr1.length + arr2.length]); 

        // Initialize an index for each array
    	int arr1i = 0; int arr2i = 0; int index = 0;

        // Merge elements based on Comparator function
    	while(arr1i < arr1.length && arr2i < arr2.length) {
    		if(comparator.compare(arr1[arr1i], arr2[arr2i]) < 0) {
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

        // Return result
        return arr;
    }
}







