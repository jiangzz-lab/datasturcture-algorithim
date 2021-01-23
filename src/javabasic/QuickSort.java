package javabasic;

import java.util.Random;

public class QuickSort {
	 public int[] quickSort(int[] array) {
		 // sanity check
		if (array == null || array.length <= 1) {
			return array;
		}
		// solve it using overloaded quickSort()
		quickSort(array, 0, array.length - 1);
		return array;
	}

	 private void quickSort(int[] array, int left, int right) {
		// base case
		if (left >= right) {
			return;
		}
		// recursive rules
		// randomly pick a pivot and partition the array
		int pivotIndex = partition(array, left, right);
		// solve subproblems
		quickSort(array, left, pivotIndex);
		quickSort(array, pivotIndex + 1, right);
	}
	 
	// partition subarray [left, right] 
	 private int partition(int[] array, int left, int right) {
	 	// randomly pick a pivot
	 	Random rand = new Random();
	 	int pivotIndex = left + rand.nextInt(right - left + 1);
	 	int pivot = array[pivotIndex];
	 	// swap the pivot with the rightmost element
	 	swap(array, pivotIndex, right);
	 	// define the range to explore: [leftBound, rightBound]
	 	// [0, leftBound) < pivot; (rightBount, right - 1] >= pivot
	 	int leftBound = left;
	 	int rightBound = right - 1;
	 	while (leftBound <= rightBound) {
	 		if (array[leftBound] < pivot) {
	 			leftBound++;
	 		} else { // array[leftBound] >= pivot
	 			swap(array, leftBound, rightBound--);
	 		}
	 	}
	 	swap(array, leftBound, right);
	 	return leftBound;
	 }


	private void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
