package datastructure.array.oneD.binarySearch;

public class KthClosest {

	/*
	 * input: 
	 * int[] array -- sorted in ascending order int target int k
	 * 
	 * output:
	 * int index of the kth closest
	 * 
	 * assumption:
	 * array not null, empty 0 < k <= input length
	 * 
	 * [0, left], [right, array.length - 1] is the searching range (left, right)
	 * contains the excluded elements
	 * 
	 * init : left = index of largest smaller or equal right = left + 1
	 * 
	 * in every step drop k / 2 elements which could not be the k-th closest
	 * 
	 * leftMid = left - k / 2 + 1 rightMid = right + k / 2 - 1
	 * 
	 * case1: rightMid >= array.length || (leftMid >= 0 && array[leftMid] is closer
	 * to target than array[rightMid]) ? 
	 * drop [leftMid, left] -- left = leftMid - 1 
	 * case2: rightMid < array.length && (leftMid < 0 || array[rightMid] is closer
	 * to target than array[leftMid]) ?
	 * drop [right, rightMid] -- right = rightMid + 1
	 * 
	 * base case: 
	 * left < 0 ? return right + k - 1 
	 * right >= array.length ? return
	 * left - k + 1 k == 1 ? return the closer of left, right
	 * 
	 * loop condition: left >= 0 || right < array.length (non-empty searching range)
	 * 
	 * TC: n = input array length 
	 * O(log(n)) + O(log(k)) = O(log(n))
	 * 
	 * SC: O(1)
	 * 
	 */

	public int kthClosest(int[] array, int target, int k) {
		// step1: find initial bounds of left/ right search ranges
		int left = largestSmallerOrEqual(array, target);
		int right = left + 1;
		// step2: search util no elements in ranges
		while (left >= 0 || right < array.length) {
			if (left < 0) {
				return right + k - 1;
			}
			if (right >= array.length) {
				return left - k + 1;
			}
			if (k == 1) {
				return Math.abs(array[left] - target) <= Math.abs(array[right] - target) ? left : right;
			}
			int leftMid = left - k / 2 + 1;
			int rightMid = right + k / 2 - 1;
			if (leftMid < 0 || (rightMid < array.length
					&& Math.abs(array[rightMid] - target) < Math.abs(array[leftMid] - target))) {
				right = rightMid + 1;
			} else {
				left = leftMid - 1;
			}
			k = k - k / 2;
		}
		return -1;
	}

	int largestSmallerOrEqual(int[] array, int target) {
		// check null, empty input
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
		KthClosest solu = new KthClosest();
		System.out.println(solu.kthClosest(new int[] { 1, 3, 5, 7, 8, 9 }, 4, 4)); // 3
	}
}
