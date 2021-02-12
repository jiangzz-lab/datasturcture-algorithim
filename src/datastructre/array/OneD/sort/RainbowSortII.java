package datastructre.array.OneD.sort;

import datastructure.array.oneD.Basics;

public class RainbowSortII {
	/*
	 * input: 
	 * int[] array, contains 0, 1, 2, 3; could have duplicates
	 * 
	 * output: 
	 * int[] array, sorted in the order 0-1-2-3
	 * 
	 * corner case: 
	 * array is null, empty ? do nothing
	 * 
	 * Solution: 
	 * linearly scan the input and distribute every element to the expected range:
	 * 
	 * use 4 pointers to define the ranges --
	 * [0, b0) := 0, [b0, b1) := 1, [b1, b2) := 2, [b2, b3] := to explore, (b3, n - 1] := 3
	 * 
	 * in every step, inspect frontier = array[b2]
	 * 
	 * case1: frontier == 3 ? array[b2] = array[b3], array[b3] = frontier; b3--
	 * case2: frontier == 2 ? b2++ 
	 * case3: frontier == 1 ? array[b2] --> array[b1], array[b1] --> frontier; b1, b2++
	 * case4: frontier == 0 ? array[b2] --> array[b1], array[b1] --> array[b0], array[b0] --> frontier(= 0); b0, b1, b2++
	 * 
	 * TC : n = input array length 
	 * TC of every step = O(1), total step# = total inspection# = n
	 * total TC = O(n)
	 * 
	 * SC: O(1)
	 * 
	 */
	public int[] rainbowSortII(int[] array) {
		if (array == null || array.length <= 1) {
			return array;
		}
		int b0 = 0;
		int b1 = 0;
		int b2 = 0;
		int b3 = array.length - 1;
		while (b2 <= b3) {
			int frontier = array[b2];
			if (frontier == 0) {
				array[b2++] = array[b1];
				array[b1++] = array[b0];
				array[b0++] = frontier;
			} else if (frontier == 1) {
				array[b2++] = array[b1];
				array[b1++] = frontier;
			} else if (frontier == 2) {
				b2++;
			} else {
				array[b2] = array[b3];
				array[b3--] = frontier;
			}
		}
		return array;
	}

	public static void main(String[] args) {
		RainbowSortII solu = new RainbowSortII();
		Basics helper = new Basics();

		helper.print1DArray(solu.rainbowSortII(new int[] { 3, 0, 1, 2, 1, 0, 2, 0, 1, 3 }));
		// 0 0 0 1 1 1 2 2 3 3
	}
}
