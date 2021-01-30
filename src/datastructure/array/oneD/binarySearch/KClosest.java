package datastructure.array.oneD.binarySearch;

import java.util.Arrays;
// import datastructure.array.OneD.Basics;

public class KClosest {
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
		}
	}
}
