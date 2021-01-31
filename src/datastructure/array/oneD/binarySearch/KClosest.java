package datastructure.array.oneD.binarySearch;

import java.util.Arrays;
// import datastructure.array.OneD.Basics;

public class KClosest {

	/*
	 * input: int[] array -- sorted in ascending order; could have duplicates 
	 * int target
	 * int k
	 * 
	 * assumption: 
	 * array not null; 
	 * k >= 0 && k <= array.length
	 * 
	 * output: 
	 * int[] res -- size = k, k closest elements to target in array
	 * 
	 * corner case: 
	 * k == 0 ? return empty array
	 * 
	 * Solution:
	 * 
	 * step1. find largest smaller or equal, record its index i 
	 * step2. search leftwards and rightwards using binary-search strategy
	 * 
	 * search range [0, left], [right, 0], init: left = i, right = i + 1 (left,
	 * right) := range of the result elements
	 * 
	 * in every step, put k / 2 elements in result 
	 * k should be updated accordingly after every step (k -- k - k / 2)
	 * 
	 * leftMid = left - k / 2 + 1 rightMid = right + k / 2 - 1
	 * 
	 * case1: rightMid >= array.length || leftMid > 0 && abs(array[leftMid] -
	 * target) <= abs(array[rightMid] - target) ? array[leftMid … left] must be all
	 * in the result
	 * 
	 * case2: rightMid < array.length && (leftMid < 0 || abs(array[leftMid] -
	 * target) > abs(array[rightMid] - target)) ? array[right … rightMid] must be
	 * all in the result
	 * 
	 * base case: k == 1 ? put the closer between array[left], array[right] in res
	 * 
	 * TC: n = input array size 
	 * O(logn) determine the initial bounds + O(logk) determine the bounds of the result
	 * + O(k) to generate result total
	 * TC = O(logn + k)
	 * 
	 * SC: O(1) (O(k) of result not included)
	 * 
	 */

	public int[] kClosest(int[] array, int target, int k) {
		// step1: determine the initial bounds of the searching range
		int left = largestSmallerOrEqual(array, target);
		int right = left + 1;
		// step2: search tehe bounds of the result
		while (k > 0) {
			if (left < 0) {
				right = right + k - 1;
				break;
			} else if (right >= array.length) {
				left = left - k + 1;
				break;
			} else if (k == 1) {
				if (Math.abs(array[left] - target) <= Math.abs(array[right] - target)) {
					left--;
				} else {
					right++;
				}
				break;
			} else {
				int leftMid = left - k / 2 + 1;
				int rightMid = right + k / 2 - 1;
				if (rightMid > array.length || (leftMid >= 0
						&& (Math.abs(array[leftMid]) - target) <= Math.abs(array[rightMid] - target))) {
					left = leftMid - 1;
				} else {
					right = rightMid + 1;
				}
				k = k - k / 2;
			}
		}
		return Arrays.copyOfRange(array, left + 1, right);
	}

	public int largestSmallerOrEqual(int[] array, int target) {
		if (array == null || array.length == 0) {
			return -1;
		}
		int left = 0;
		int right = array.length - 1;
		while (left < right - 1) {
			int mid = left + (right - left) / 2;
			if (array[mid] <= target) {
				left = mid;
			} else {
				right = mid - 1;
			}
		}
		if (array[right] <= target) {
			return right;
		}
		if (array[left] <= target) {
			return left;
		}
		return -1;
	}

	public static void main(String[] args) {
		KClosest solu = new KClosest();

		int[] array = new int[] { 1, 2, 3, 4, 5, 5, 6, 7, 8, 9 };
		int[] res = solu.kClosest(array, 5, 4);
		for (int num : res) {
			System.out.print(num + " ");
		} // 4 5 5 6
	}
}
