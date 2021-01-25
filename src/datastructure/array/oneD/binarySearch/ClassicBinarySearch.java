package datastructure.array.oneD.binarySearch;

public class ClassicBinarySearch {
	/*
	 * input: int[] array, sorted in ascending order, could have duplicate elements
	 * int target
	 * 
	 * output: int the index of an element = target in array multiple elements ==
	 * target ? return index of any of them no target in array ? return -1
	 * 
	 * corner case: array is null or empty ? return -1
	 * 
	 * Solution: in every step, reduce the search range by comparing the middle
	 * element to the target
	 * 
	 * [left, right] := current search range, mid = (left + right) / 2
	 * 
	 * case1 array[mid] == target ? return mid case2 array[mid] < target ? drop
	 * array[mid] and any smaller element -- left = mid + 1 case3 array[mid] >
	 * target ? drop array[mid] and any larger element -- right = mid - 1
	 * 
	 * TC = O(logn), n = input array length worse case: no target in array total# of
	 * steps = log_2(n) TC of every step = O(1) total TC = O(logn)
	 * 
	 * SC = O(1)
	 */

	public int binarySearch(int[] array, int target) {
		if (array == null || array.length == 0) {
			return -1;
		}
		int left = 0;
		int right = array.length - 1;
		while (left <= right) {
			int mid = (right - left) / 2 + left;
			if (array[mid] == target) {
				return mid;
			} else if (array[mid] < target) {
				left = mid + 1;
			} else { // array[mid] > target
				right = mid - 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		ClassicBinarySearch solu = new ClassicBinarySearch();

		System.out.println(solu.binarySearch(null, 5)); // -1

		System.out.println(solu.binarySearch(new int[0], 5)); // -1

		System.out.println(solu.binarySearch(new int[] { 1 }, 1)); // 0

		int[] array = new int[] { 1, 2, 3, 4, 5, 6, 7 }; // 4
		System.out.println(solu.binarySearch(array, 5));

	}
}
