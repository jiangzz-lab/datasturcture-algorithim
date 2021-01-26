package datastructure.array.oneD.binarySearch;

import datastructure.array.oneD.Basics;

public class ClosestK {

	/*
	 * input: 
	 * int array[] -- sorted in ascending order, could have duplicate elements 
	 * int target
	 * int k
	 * 
	 * assumption:
	 * array != null k >= 0 && k <= input array length
	 * 
	 * output:
	 * int[] k elements closest to target, sorted in ascending order by the
	 * difference between the number and target
	 * 
	 * corner case:
	 * array length == 0 ? return empty array
	 * 
	 * Solution:
	 * step1: find the index of the largest smaller or equal i 
	 * step2: use a window to take k smallest -- (left, right) is the closest right - left - 1
	 * elements -- init [i, i + 1]
	 * 
	 * tip: when finding the largest smaller or equal, if no found, we get -1 then
	 * init right = 0 within a valid range of the array
	 * 
	 * TC = O(logn) + O(k) = O(logn + k), n = input array length
	 * SC = O(1) if not including the result array
	 * 
	 */
	
	public int[] closestK(int[] array, int target, int k) {
		// step1: find largestSmallerOrEqual
		// and set it to be the left bound of the result window
		int left = largestSmallerOrEqual(array, target);
		int right = left + 1;
		int[] res = new int[k];
		for (int i = 0; i < k; i++) {
			// put the closer between left, right to result
			if (left >= 0 && (right >= array.length || target - array[left] <= array[right] - target)) {
				res[i] = array[left--];
			} else { // right won’t be out of bound since k <= array length
				res[i] = array[right++];
			}
		}
		return res;
	}

	int largestSmallerOrEqual(int[] array, int target) {
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
		ClosestK solu = new ClosestK();
		Basics helper = new Basics();

		int[] res = solu.closestK(new int[0], 1, 0);
		helper.print1DArray(res); // no elements

		res = solu.closestK(new int[] { 0, 1, 2, 3 }, 1, 2);
		helper.print1DArray(res); // 1 0
	}

}
