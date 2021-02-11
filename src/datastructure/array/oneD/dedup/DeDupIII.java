package datastructure.array.oneD.dedup;

import java.util.Arrays;
import datastructure.array.oneD.Basics;

public class DeDupIII {
	/*
	 * input: 
	 * int[] array
	 * 
	 * output: 
	 * int[] array with all duplicate elements removed
	 * 
	 * corner case: 
	 * array is null or empty ? do nothing
	 * 
	 * Solution: 
	 * linear scan the input and only copy non-duplicate elements to
	 * result
	 * 
	 * [0, res) := processed result, init res = 0; [0, probe) := processed input, init probe = 0
	 * 
	 * in every step, 
	 * case1: probe + 1 < array.length && array[probe] == array[probe + 1] ? skip all array[probe] 
	 * case2: probe == array.length - 1 || array[probe + 1] != array[probe] ? copy array[probe] to result 
	 * 
	 * TC: n = input array length
	 * # of checking probe = n, TC of every checking = O(1) O(n) to copy the result
	 * total TC = O(n)
	 * 
	 * SC: 
	 * O(1), not including the O(n) result array
	 * 
	 */
	public int[] dedupIII(int[] array) {
		if (array == null || array.length <= 1) {
			return array;
		}
		int res = 0;
		int probe = 0;
		while (probe < array.length) {
			if (probe + 1 < array.length && array[probe + 1] == array[probe]) {
				int frontier = array[probe];
				while (probe < array.length && array[probe] == frontier) {
					probe++;
				}
			} else {
				array[res++] = array[probe++];
			}
		}
		return Arrays.copyOfRange(array, 0, res);
	}

	public static void main(String[] args) {
		DeDupIII solu = new DeDupIII();
		Basics helper = new Basics();

		helper.print1DArray(solu.dedupIII(null)); // No elements!

		helper.print1DArray(solu.dedupIII(new int[0])); // No elements!

		helper.print1DArray(solu.dedupIII(new int[] { 1 })); // 1

		helper.print1DArray(solu.dedupIII(new int[] { 1, 1 })); // No elements!

		helper.print1DArray(solu.dedupIII(new int[] { 1, 1, 2, 2, 2, 3, 4, 5, 5, 5 }));
		// 3 4
	}
}
