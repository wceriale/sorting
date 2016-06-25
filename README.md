# Sorting Algorithms
##Mergesort
	Basic MergeSort
	Average runtime: nlog(n)
	Worst case:      nlog(n)
	Memory:			 n

<img src="https://raw.githubusercontent.com/wceriale/sorting/master/images/Mergesort.gif" width="550" height="300" />


##Heapsort
	In place Heap Sort
	Average runtime: nlog(n)
	Worst case:      nlog(n)
	Memory:			 1
	- Uses Floyd's algorithm to "heapify" the array in O(n).
	- MinHeap - Elements removed from front and added to back
	- Finally, reverses the array to put it in correct order.



##Quicksort
	Single Pivot Quicksort
	Average runtime: nlog(n)
	Worst case:      n²
	Memory:			 1
	- Uses last element in array as pivot
	
	Parallel Quicksort
	Work: n log n
	Span: log² n
	Space: 1
<img src="https://raw.githubusercontent.com/wceriale/sorting/master/images/Quicksort.png" width="550" height="300" />
