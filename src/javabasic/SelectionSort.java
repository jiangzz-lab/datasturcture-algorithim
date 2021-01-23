package javabasic;

public class SelectionSort {
	public int[] selectionSort(int[] array) {
		// sanity check
		if (array == null || array.length == 0) {
		return array;
	}
	for (int i = 0; i < array.length; i++) {
		int minIndex = i;
		for (int j = i + 1; j < array.length; j++) {
		if (array[j] < array[minIndex]) {
		minIndex = j;
	}
	}
	swap(array, i, minIndex);
	}
	return array;
	}

	private void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
