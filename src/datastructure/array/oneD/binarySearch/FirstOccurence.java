package datastructure.array.oneD.binarySearch;

public class FirstOccurence {
	/*
	 * Solution: in every step, reduce the searching range by comparing the middle
	 * element to the target
	 * 
	 * case1: midValue == target ? drop all elements after mid -- right = mid 
	 * case2: midValue < target ? drop mid and all elements before mid -- left = mid + 1
	 * case3: midValue > target ? drop mid and all elements after mid -- right = mid - 1
	 * 
	 * cannot continue to reduce searching range when only 1 element left loop
	 * condition: left < right
	 * 
	 * 2 elements could be reduced to 0 element: 2, 3 target = 1 -- next: right =
	 * left - 1
	 * 
	 * or 1 elememt: 2, 3 target = 4 -- next: left = right
	 * 
	 * if left < right || array[left] != target return -1 otherwise return left
	 * 
	 * TC = O(logn), n = input array length SC= O(1)
	 * 
	 */
	
	public int firstOccurence(int[] array, int target) {
		// check null, empty input
		if (array == null || array.length == 0) {
			return -1;
		}
		int left = 0;
		int right = array.length - 1;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (array[mid] == target) {
				right = mid;
			} else if (array[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return (left > right || array[left] != target) ? -1 : left;
	}

	public static void main(String[] args) {
		FirstOccurence solu = new FirstOccurence();

		System.out.println(solu.firstOccurence(null, 5)); // -1

		System.out.println(solu.firstOccurence(new int[0], 5)); // -1

		System.out.println(solu.firstOccurence(new int[] { 5 }, 5)); // 0

		System.out.println(solu.firstOccurence(new int[] { 2, 3 }, 5)); // -1

		int[] array = new int[] { 1, 2, 5, 5, 5, 5 };
		System.out.println(solu.firstOccurence(array, 5)); // 2
	}
}
