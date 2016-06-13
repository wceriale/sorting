import java.util.Comparator;


public class HeapSort {
    static Comparator comparator;
    static int size;

    public static <E extends Comparable<E>> void sort(E[] array) {
        HeapSort.sort(array, (x, y) -> x.compareTo(y));
    }

    public static <E> void sort(E[] array, Comparator<E> compare) {
        comparator = compare;
        size = array.length;
        floyd(array);
        for(int i = array.length - 1; i >= 0; i--)
            array[i] = next(array);
        for(int i = 0; i < array.length / 2; i++) 
            swap(i, array.length - i - 1, array);
    }

    private static <E> void floyd(E[] arr) {
        for(int i = arr.length / 2 + 1; i >= 0; i--) {
            percolateDown(i, arr);
        }
    }

    public static <E> E next(E[] data) {
    	E temp = data[0];
    	data[0] = data[size - 1];
    	size--;
    	percolateDown(0, data);
    	return temp;
    }
   
    // Ensures heap property starting
    // from top of heap
    private static <E> void percolateDown(int i, E[] data) {
    	int hole = i;     	
    	
    	// While has children continue check/swap
    	// 
    	// checkChildren returns size if heap
    	// property is valid, breaking while
    	while((2 * hole + 1) < size) {
    		hole = checkChildren(hole, data);
    	}
    }
    
    // Checks all children of hole
    // Swaps with smallest child if heap property
    // is not maintained and returns new index
    // If heap property is valid, returns size of array
    private static <E> int checkChildren(int hole, E[] data) {
    	int smallestIndex = hole; 
    	int childrenStart = hole * 2 + 1;
    	
    	// Grab last index to check
    	int end = (size - 1 < (childrenStart + 1)) ? size - 1 : childrenStart + 1; 
    	
    	// Check children, store smallest child index
    	for(int i = childrenStart; i <= end; i++)
    		if(comparator.compare(data[smallestIndex], data[i]) > 0)
    			smallestIndex = i;							
    			
    	// Check if smaller E found - swap if so
    	if( !(hole == smallestIndex) ) {                    
    		swap(hole, smallestIndex, data);
    		return smallestIndex;
    	} else {
    		return data.length; // Returns impossible hole
    	}	
    	
    }
    
    // Swap x and y in field array
    private static <E> void swap(int x, int y, E[] data) {
    	E temp = data[x];
    	data[x] = data[y];
    	data[y] = temp;
    }
}