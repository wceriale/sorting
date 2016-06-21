import java.util.Arrays;

public class SortingTests {
	public static final int ARR_SIZE = 30000000;

	public static void main(String[] args) {
		Integer[] arr = new Integer[ARR_SIZE];
		Integer[] arr2 = new Integer[ARR_SIZE];
		Integer[] arr3 = new Integer[ARR_SIZE];
		Integer[] arr4 = new Integer[ARR_SIZE];
		for(int i = 0; i < arr.length; i++) {
			int random = ((int) (Math.random() * 100 + 1));
			arr[i] = random;
			arr2[i] = random;
			arr3[i] = random; 
			arr4[i] = random;
		}
		// System.out.println(Arrays.toString(arr2));
		System.out.println("Test 4. Ran with SeqCutoff of 10000");
		testParallelQuickSort(arr);
		testQuickSort(arr2);
		testMergeSort(arr3);
		testHeapSort(arr4);
		// System.out.println(Arrays.toString(arr2));
	}

	public static void testParallelQuickSort(Integer[] arr) {
		System.out.println();
		System.out.println("Sorting Array of " + ARR_SIZE + " elements");
		System.out.println("Array is sorted: " + isSorted(arr));
		System.out.println("ParallelQuickSort sort used");
		long timeBeforeSort2 = System.currentTimeMillis();
		ParallelQuickSort.sort(arr);
		long timeAfterSort2 = System.currentTimeMillis();
		System.out.println("Array is sorted: " + isSorted(arr));
		System.out.println("ParallelQuickSort took " + (timeAfterSort2 - timeBeforeSort2) + "ms");
		System.out.println();
	}

	public static void testQuickSort(Integer[] arr) {
		System.out.println();
		System.out.println("Sorting Array of " + ARR_SIZE + " elements");
		System.out.println("Array is sorted: " + isSorted(arr));
		System.out.println("QuickSort sort used");
		long timeBeforeSort2 = System.currentTimeMillis();
		QuickSort.sort(arr);
		long timeAfterSort2 = System.currentTimeMillis();
		System.out.println("Array is sorted: " + isSorted(arr));
		System.out.println("QuickSort took " + (timeAfterSort2 - timeBeforeSort2) + "ms");
		System.out.println();
	}

	public static void testHeapSort(Integer[] arr) {
		System.out.println();
		System.out.println("Sorting Array of " + ARR_SIZE + " elements");
		System.out.println("Array is sorted: " + isSorted(arr));
		System.out.println("HeapSort sort used");
		long timeBeforeSort2 = System.currentTimeMillis();
		HeapSort.sort(arr);
		long timeAfterSort2 = System.currentTimeMillis();
		System.out.println("Array is sorted: " + isSorted(arr));
		System.out.println("HeapSort took " + (timeAfterSort2 - timeBeforeSort2) + "ms");
		System.out.println();
	}

	public static void testMergeSort(Integer[] arr) {
		System.out.println();
		System.out.println("Sorting Array of " + ARR_SIZE + " elements");
		System.out.println("Array is sorted: " + isSorted(arr));
		System.out.println("MergeSort sort used");
		long timeBeforeSort2 = System.currentTimeMillis();
		MergeSort.sort(arr);
		long timeAfterSort2 = System.currentTimeMillis();
		System.out.println("Array is sorted: " + isSorted(arr));
		System.out.println("MergeSort took " + (timeAfterSort2 - timeBeforeSort2) + "ms");
		System.out.println();
	}

	public static boolean isSorted(Integer[] arr) {
		for(int i = 0; i < arr.length - 1; i++)
			if(arr[i] > arr[i + 1])
				return false;
		return true;
	}








}