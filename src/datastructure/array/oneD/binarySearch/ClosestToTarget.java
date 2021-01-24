package datastructure.array.oneD.binarySearch;

public class ClosestToTarget {
	/*
	 * search the element closest to the target
	 *  
	 * input: int[] array, sorted in ascending order, could be duplicates
	 * 
	 * output: int index of the element closest to target
	 * multiple closest? return the index of any of them
	 * 
	 * corner case: array null, empty ? return -1
	 * 
	 * Solution:
	 * 
	 * in every step reduce the search range by comparing the mid element to the
	 * target
	 * 
	 * case1: midValue == target ? return midValue
	 * case2: midValue < target ? drop those smaller than midValue -- left = mid
	 * case3: midValue > target ? drop those larger than midValue -- right = mid
	 * 
	 * might not be able to reduce the searching range to 1
	 * 
	 * 1 3, target = 2 -- loop condition left <= right, left < right gives dead loop
	 * 
	 * terminate loop when there are 2 (or less) elements to be check -- 
	 * loop condition left < right - 1
	 * 
	 * return the closer to the target between array[left], array[right]
	 * 
	 * TC = log(n), n = input array length
	 * 
	 * SC = O(1)
	 * 
	 */
	
	public int closest(int[] array, int target) {
		if (array == null || array.length == 0) {
			return -1;
		}
		int left = 0;
		int right = array.length - 1;
		while (left < right - 1) {
			int mid = left + (right - left) / 2;
			if (array[mid] == target) {
				return mid;
			} else if (array[mid] < target) {
				left = mid;
			} else {
				right = mid;
			}
		}
		return Math.abs(array[left] - target) <= Math.abs(array[right] - target) ? left : right;
	}

	public static void main(String[] args) {
		ClosestToTarget solu = new ClosestToTarget();

		System.out.println(solu.closest(null, 5)); // -1

		System.out.println(solu.closest(new int[0], 5)); // -1

		int[] array = new int[] { 1, 2, 4, 6, 8 };
		System.out.println(solu.closest(array, 5)); // 2
	}
}
