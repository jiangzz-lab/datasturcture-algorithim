package datastructure.array.oneD.dedup;

import java.util.Arrays;
import datastructure.array.oneD.Basics;

public class DeDupI {
	/*
	 * input: 
	 * int[] array, sorted
	 * 
	 * output: 
	 * int[] array with max occurrence of duplicate elements = 1 (no
	 * duplicates)
	 * 
	 * corner case: 
	 * array is null, empty ? do nothing
	 * 
	 * Solution: 
	 * linear scan and copy only one of the duplicate elements to result
	 * 
	 * [0, res) := processed result; [0, probe) := processed input
	 * 
	 * in every step: 
	 * case1: array[probe] != array[res - 1] ? copy array[probe] to res, res++, probe++
	 * case2: array[probe] == array[res - 1] ? do nothing, probe++
	 * 
	 * TC: n = array length 
	 * n steps, TC of every step = O(1); O(n) to produce the result total TC = O(n)
	 * 
	 * 
	 * SC: O(1) not including the O(n) result array
	 * 
	 */
	public int[] dedup(int[] array) {
		if (array == null || array.length <= 1) {
			return array;
		}
		int res = 1; // the first element must be in the result
		for (int probe = 1; probe < array.length; probe++) {
			if (array[probe] != array[res - 1]) {
				array[res++] = array[probe];
			}
		}
		return Arrays.copyOfRange(array, 0, res);
	}

	public static void main(String[] args) {
		DeDupI solu = new DeDupI();
		Basics helper = new Basics();

		helper.print1DArray(solu.dedup(null)); // No elements!

		helper.print1DArray(solu.dedup(new int[0])); // No elements!

		helper.print1DArray(solu.dedup(new int[] { 1 })); // 1

		helper.print1DArray(solu.dedup(new int[] { 1, 1 })); // 1

		helper.print1DArray(solu.dedup(new int[] { 1, 1, 2, 2, 2, 3, 3, 4, 5, 5 })); // 1 2 3 4 5

	}
}
