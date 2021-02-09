package datastructre.array.OneD.sort;

import datastructure.array.oneD.Basics;

public class RainbowSort {
	/*
	 * input: int[] array
	 * 
	 * output: int[] array, all negative numbers to left, all 0s in the middle, all
	 * positives to right
	 * 
	 * corner case: array is null, empty ? do nothing
	 * 
	 * Solution: linear scan and partition with 3 pointers
	 * 
	 * [0, zero) < 0; [zero, probe) == 0; [probe, pos] to explore; (pos, n - 1] > 0
	 * 
	 * in every step, case1: array[probe] < 0 ? swap(zero, probe), zero++, probe++
	 * case2: array[probe] == 0 ? probe++ case3: array[probe] > 0 ? swap(probe,
	 * pos), pos--
	 * 
	 * loop condition: probe <= pos -- still have elements to explore
	 * 
	 * TC : n = input array length TC of every probe = O(1), total probe n --> total
	 * TC = O(n)
	 * 
	 * SC: O(1)
	 * 
	 */

	public int[] rainbowSort(int[] array) {
		if (array == null || array.length <= 1) {
			return array;
		}
		int zero = 0;
		int probe = 0;
		int pos = array.length - 1;
		while (probe <= pos) {
			if (array[probe] < 0) {
				swap(array, zero++, probe++);
			} else if (array[probe] == 0) {
				probe++;
			} else { // array[probe] > 0
				swap(array, probe, pos--);
			}
		}
		return array;
	}

	public int[] rainbowSort(int[] array, int k) {
		if (array == null || array.length <= 1) {
			return array;
		}
		int[] bounds = new int[k];
		bounds[k - 1] = array.length - 1;
		while (array[bounds[k - 2]] <= bounds[k - 1]) {
			if (bounds[k - 2] == k - 1) {
				swap(array, bounds[k - 2], bounds[k - 1]--);
			} else if (array[bounds[k - 2]] == k - 2) {
				bounds[k - 2]++;
			} else {
				for (int i = 0; i < k - 2; i++) {
					if (array[bounds[k - 2]] == i) {
						swap(array, bounds[k - 2], bounds[i]);
						for (int j = i + 1; j < k - 2; j++) {
							if (bounds[j] == bounds[i]) {
								bounds[j]++;
							}
						}
						bounds[i]++;
					}
				}
			}
		}
		return array;
	}

	void swap(int[] array, int left, int right) {
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}

	public static void main(String[] args) {
		RainbowSort solu = new RainbowSort();
		Basics helper = new Basics();

		helper.print1DArray(solu.rainbowSort(null));

		helper.print1DArray(solu.rainbowSort(new int[0]));

		helper.print1DArray(solu.rainbowSort(new int[] { 5 })); // 5

		helper.print1DArray(solu.rainbowSort(new int[] { 1, 1, 0, 0, -1, 1, 0 }));
		// -1 0 0 0 1 1 1

		helper.print1DArray(solu.rainbowSort(new int[] { 1, 0, -2, 0, 3, -3 }));
		// -3 -2 0 0 3 1
		// the relative position of positive/negtive numbers can change

		helper.print1DArray(solu.rainbowSort(new int[] { 1, 0, 1, 3, 0, 2, 3, 2 }, 4));
	}
}
