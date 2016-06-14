import java.util.Arrays;

public class SortingTests {
	public static final int ARR_SIZE = 10;

	public static void main(String[] args) {
		Integer[] arr = new Integer[ARR_SIZE];
		Integer[] arr2 = new Integer[ARR_SIZE];
		Integer[] arr3 = new Integer[ARR_SIZE];
		for(int i = 0; i < arr.length; i++) {
			int random = ((int) (Math.random() * 100 + 1));
			arr[i] = random;
			arr2[i] = random;
			arr3[i] = random; 
		}
				System.out.println(Arrays.toString(arr));

		long timeBeforeSort2 = System.currentTimeMillis();
		MergeSort.sort(arr);
		long timeAfterSort2 = System.currentTimeMillis();
				System.out.println(Arrays.toString(arr));

		System.out.println("MergeSort took " + (timeAfterSort2 - timeBeforeSort2) + "ms");

		// System.out.println();
		// System.out.println();

		// long timeBeforeSort1 = System.currentTimeMillis();
		// HeapSort.sort(arr2);
		// long timeAfterSort1 = System.currentTimeMillis();
		// System.out.println("HeapSort took" + (timeAfterSort1 - timeBeforeSort1) + " ms");

		// System.out.println();
		// System.out.println();

		// long timeBeforeSort = System.currentTimeMillis();
		// QuickSort.sort(arr);
		// long timeAfterSort = System.currentTimeMillis();
		// System.out.println("QuickSort took " + (timeAfterSort - timeBeforeSort) + "ms");
	}
}