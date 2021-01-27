package datastructure.array.oneD.binarySearch;

public class SmallestLarger {
	
	/*
	 * input: 
	 * int[] array -- sorted in ascending order, could have duplicates
	 * 
	 * output:
	 * int index of the smallest element larger than target
	 * 
	 * corner case:
	 * array == null, empty ? return -1
	 * 
	 * Solution:
	 * in every step, reduce the searching range [left, right] by
	 * comparing the middle element to the target
	 * 
	 * case1: midValue > target ? drop elements right to mid -- right = mid
	 * case2: midValue <= target ? drop mid and elements left to it -- left = mid + 1
	 * 
	 * right = mid cannot reduce the searching range for 1 element
	 * 
	 * loop condition: left < right
	 * 
	 * if array[left] > target, return left; otherwise return -1
	 * 
	 * TC = log(n), n = input array length
	 * SC = O(1)
	 * 
	 */

	public int smallestLarger(int[] array, int target) {
		// check null, empty input
		if (array == null || array.length == 0) {
			return -1;
		}
		int left = 0;
		int right = array.length - 1;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (array[mid] > target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return array[left] > target ? left : -1;
	}

	public static void main(String[] args) {
		SmallestLarger solu = new SmallestLarger();

		System.out.println(solu.smallestLarger(null, 1)); // -1

		System.out.println(solu.smallestLarger(new int[0], 1)); // -1

		System.out.println(solu.smallestLarger(new int[] { 1, 2, 3 }, 1)); // 1
	}
}
