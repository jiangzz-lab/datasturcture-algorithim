package datastructre.array.OneD.sort;

import java.util.Random;
import datastructure.array.oneD.Basics;

public class QuickSort {
	public void quickSort(int[] array) {
		if (array == null || array.length <= 1) {
			return;
		}
		quickSort(array, 0, array.length - 1);
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
		int[] array = new int[] { 3, 2, 5, 8, 7, 9, 0 };
		solu.quickSort(array);
		helper.print1DArray(array); // 0 2 3 5 7 8 9
	}

}
