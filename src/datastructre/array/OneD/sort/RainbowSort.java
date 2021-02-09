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

	/*
	 * sort the elements into k groups
	 * 
	 * input: 
	 * int[] array, int k
	 * 
	 * assumption: 
	 * k >=1, array contains number 1, 2, ... k
	 * 
	 * output: 
	 * int[] array = [1 ... 1, 2 ... 2 ... k ... k]
	 * 
	 * corner case: 
	 * array is null, empty ? do nothing
	 * 
	 * Solution: 
	 * linearly scan and distribute
	 * 
	 * use k bounds to separate the input into k sections:
	 * [0, bounds[1]] := 1s, [0, bounds[2]] := 2s, ... [0, bounds[k - 1]] := (k-1)s
	 * [bounds[k - 1], bounds[k]] := to explore; array[bounds[k - 1]] := frontier
	 * (bounds[k], input length) := ks
	 * 
	 * in every step,
	 * 
	 * case1: frontier = k - 1 ? b[k - 1]++
	 * case2: frontier = k ? swap(b[k - 1], b[k]), b[k]--
	 * case3: frontier < k ? loop swap -- 
	 * b[k - 1] -> b[k - 2], b[k - 2] -> b[k - 3] ... b[frontier] -> frontier (original b[k - 1])
	 * and all bounds[frontier] ... bounds[k - 1]++
	 * 
	 * loop condition: bounds[k - 1] <= bounds[k]
	 * 
	 * TC: n = input array length
	 * n steps, TC of every step = O(k) -- total TC = O(k * n)
	 * 
	 * SC: O(k) from usage of array bounds
	 * 
	 */

	public int[] rainbowSort(int[] array, int k) {
		if (array == null || array.length <= 1) {
			return array;
		}
		int[] bounds = new int[k + 1];
		bounds[k] = array.length - 1;
		while (bounds[k - 1] <= bounds[k]) {
			int frontier = array[bounds[k - 1]];
			if (frontier == k - 1) {
				bounds[k - 1]++;
			} else if (frontier == k) {
				swap(array, bounds[k - 1], bounds[k]--);
			} else {
				for (int i = k - 1; i > frontier; i--) {
					array[bounds[i]++] = array[bounds[i - 1]];
				}
				array[bounds[frontier]++] = frontier;
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

		helper.print1DArray(solu.rainbowSort(new int[] { 1, 2, 1, 3 }, 4));
		// 1 1 2 3
		
		helper.print1DArray(solu.rainbowSort(new int[] { 5, 1, 4, 2, 1, 3, 5, 4, 4 }, 5));
		// 1 1 2 3 4 4 4 5 5 
	}
}
