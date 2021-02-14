package datastructure.array.oneD.dedup;

import java.util.Arrays;
import datastructure.array.oneD.Basics;

public class DeDupV {
	/*
	 * input: int[] array -- could be unsorted
	 * 
	 * output: int[] array -- for each group of adjacent elements with the same
	 * value keep at most two of them
	 * 
	 * example: [1, 2, 2, 3, 3, 3] -> [1, 2, 2, 3, 3]
	 * 
	 * Solution: linear scan the input and keep the last <= 2 elements of every
	 * group of adjacent elements with the same value
	 * 
	 * [0, res) := processed result [0, probe) := processed input
	 * 
	 * in every step, inspect frontier = array[probe] case1: frontier == array[probe
	 * + 1] ? copy frontier to res and res++ twice, then skip all element equals to
	 * frontier from probe + 2 case2: in all other case just copy frontier to res;
	 * res++, probe++
	 * 
	 * TC: n = input array length TC of every inspection = O(1), total # of
	 * inspection = n O(n) to generate result total TC = O(n)
	 * 
	 * SC: O(1)
	 */
	public int[] dedup(int[] array) {
		if (array == null || array.length <= 2) {
			return array;
		}
		int res = 0;
		int probe = 0;
		while (probe < array.length) {
			int frontier = array[probe];
			if (probe + 1 < array.length && array[probe + 1] == frontier) {
				array[res++] = frontier;
				array[res++] = frontier;
				probe = probe + 2;
				while (probe < array.length && array[probe] == frontier) {
					probe++;
				}
			} else {
				array[res++] = frontier;
				probe++;
			}
		}
		return Arrays.copyOf(array, res);
	}
	
	public static void main(String[] args) {
		DeDupV solu = new DeDupV();
		Basics helper = new Basics();
		
		helper.print1DArray(solu.dedup(new int[] {1, 3, 1, 2, 2, 2, 5, 2})); // 1 3 1 2 2 5 2 
	}
}
