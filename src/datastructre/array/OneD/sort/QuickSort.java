package datastructre.array.OneD.sort;

import java.util.Random;
import datastructure.array.oneD.Basics;

public class QuickSort {
	/*
	 * input: 
	 * int[] array -- unsorted, could have duplicates
	 * 
	 * output: 
	 * int[] elements in array sorted in ascending order
	 * 
	 * corner case: 
	 * array is null, empty ? do nothing
	 * 
	 * Solution: recursive
	 * 
	 * quickSort(array, left, right) := quick sort array[left … right]
	 * 
	 * recursion rule: 
	 * i. pick a pivot and partition array[left … right] so that 
	 * array[left, pivotIndex - 1] are <= pivot and array[pivot + 1, right] are smaller than pivot 
	 * ii. quickSort(array, left, pivotIndex - 1), quickSort(array, pivotIndex + 1, right)
	 * 
	 * TC: n = input array length
	 * TC of every recursion call = O(n) from partition;
	 * total recursion call# = worst case: n; average case: O(logn) 
	 * total TC = O(n^2) worst case; O(nlogn) average case 
	 * 
	 * SC: SC of every recursion call = O(1) 
	 * recursion call# along a root-to-leaf path on the recursion tree = 
	 * worse case n; average case O(logn) 
	 * total SC = O(n) worst case; O(logn) average case
	 * 
	 */

	public int[] quickSort(int[] array) {
		if (array == null || array.length <= 1) {
			return array;
		}
		quickSort(array, 0, array.length - 1);
		return array;
	}

	void quickSort(int[] array, int left, int right) {
		if (left >= right) {
			return;
		}
		int pivotIndex = partition(array, left, right);
		quickSort(array, left, pivotIndex - 1);
		quickSort(array, pivotIndex + 1, right);
	}

	int partition(int[] array, int left, int right) {
		Random rand = new Random();
		int pivotIndex = left + rand.nextInt(right + 1 - left);
		int pivot = array[pivotIndex];
		swap(array, pivotIndex, right);
		pivotIndex = right--;
		while (left <= right) {
			if (array[left] <= pivot) {
				left++;
			} else {
				swap(array, left, right--);
			}
		}
		swap(array, pivotIndex, left);
		return left;
	}

	void swap(int[] array, int left, int right) {
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}

	public static void main(String[] args) {
		QuickSort solu = new QuickSort();
		Basics helper = new Basics();

		helper.print1DArray(solu.quickSort(null)); // no elements!

		helper.print1DArray(solu.quickSort(new int[0])); // no elements!

		helper.print1DArray(solu.quickSort(new int[] { 3 })); // 3

		int[] array = new int[] { 3, 2, 5, 8, 7, 9, 0 };
		helper.print1DArray(solu.quickSort(array));
		; // 0 2 3 5 7 8 9
	}

}
