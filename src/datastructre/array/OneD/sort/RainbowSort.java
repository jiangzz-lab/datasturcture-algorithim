package datastructre.array.OneD.sort;

import datastructure.array.oneD.Basics;

public class RainbowSort {
	/*
	 * input: 
	 * int[] array
	 * 
	 * output: 
	 * int[] array, all negative numbers to left, all 0s in the middle, all positives to right
	 * 
	 * corner case: 
	 * array is null, empty ? do nothing
	 * 
	 * Solution: 
	 * linear scan and partition with 3 pointers
	 * 
	 * [0, bneg) < 0; [bneg, b0) == 0; [b0, bpos] to explore; (bpos, n - 1] > 0
	 * 
	 * in every step, inspect frontier = array[b0]
	 * case1: frontier < 0 ? array[b0] = array[bneg], array[bneg] = frontier, b0++, bneg++
	 * case2: frontier == 0 ? b0++
	 * case3: frontier > 0 ? array[b0] = array[bpos], array[bpos] = frontier, bpos--
	 * 
	 * loop condition:
	 * probe <= pos
	 * 
	 * TC : n = input array length 
	 * TC of every step = O(1), total step# = total inspection# = n
	 * total TC = O(n)
	 * 
	 * SC: O(1)
	 * 
	 */

	public int[] rainbowSort(int[] array) {
		if (array == null || array.length <= 1) {
			return array;
		}
		int bneg = 0;
		int b0 = 0;
		int bpos = array.length - 1;
		while (b0 <= bpos) {
			int frontier = array[b0];
			if (frontier == 0) {
				b0++;
			} else if (frontier < 0) {
				array[b0++] = array[bneg];
				array[bneg++] = frontier;
			} else { // frontier > 0
				array[b0] = array[bpos];
				array[bpos--] = frontier;
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
	 * linearly scan and distribute every element to the expected range
	 * 
	 * use k bounds to separate the input into k sections: 
	 * [0, bounds[1]] := 1s, [0, bounds[2]] := 2s, ... [0, bounds[k - 1]] := (k-1)s 
	 * [bounds[k - 1], bounds[k]] := to explore;
	 * (bounds[k], input length) := ks
	 * 
	 * in every step, inspect frontier = array[bounds[k - 1]]  
	 * 
	 * case1: frontier = k - 1 ? b[k - 1]++ 
	 * case2: frontier = k ? swap(b[k - 1], b[k]), b[k]
	 * case3: frontier < k ? loop swap -- b[k - 1] -> b[k - 2], b[k - 2] -> b[k - 3] ... b[frontier] -> frontier (original b[k - 1]) 
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
