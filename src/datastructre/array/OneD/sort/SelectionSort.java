package datastructre.array.OneD.sort;

import datastructure.array.oneD.Basics;

public class SelectionSort {
	public int[] selectionSort(int[] array) {
		if (array == null || array.length <= 1) {
			return array;
	}
	for (int left = 0; left < array.length - 1; left++) {
		int min = array[left];
		int minIndex = left;
		for(int i = left + 1; i < array.length; i++) {
		if (array[i] < min) {
		min = array[i];
		minIndex = i;
	}
	}
	swap(array, left, minIndex);
	}
	return array;
	}
	
	void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static void main(String[] args) {
		SelectionSort solu = new SelectionSort();
		Basics helper = new Basics();
		helper.print1DArray(solu.selectionSort(new int[] {1, 3, 5, 6, 7, 9}));
		// 1 3 5 6 7 9
	}
}
