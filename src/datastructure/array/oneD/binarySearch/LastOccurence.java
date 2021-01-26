package datastructure.array.oneD.binarySearch;

public class LastOccurence {
	/*
	 * input: 
	 * int[] array -- sorted in ascending order, could have duplicates 
	 * int target
	 * 
	 * output: int index of the last element == target in array
	 * no found ? return -1
	 * 
	 * corner case:a
	 * array == null, empty ? return -1
	 * 
	 * Solution: 
	 * in every step, reduce the searching range by comparing the middle element to the target
	 * 
	 * case1: midValue == target ? drop elements before mid -- left = mid
	 * case2: midValue < target ? drop mid and elements before mid -- left = mid + 1
	 * case3: midValue > target ? drop mid and elements after mid -- right = mid - 1
	 * 
	 * left = mid cannot reduce the searching range for 1 or 2 elements
	 * 
	 * loop condition: left < right - 1
	 * 
	 * there could be 2 or 1 elements left after the loop
	 * 
	 * post process: 
	 * 2 elements -- left = right - 1
	 * return right if array[right] == target
	 * else return left if array[left] == target
	 * else return -1 
	 * 1 elements -- left == right
	 * can be handled with the same logic
	 * 
	 * TC = O(logn), n = input array length
	 * SC = O(1)
	 * 
	 */
	
	public int lastOccurence(int[] array, int target) {
		// check null, empty input
		if (array == null || array.length == 0) {
			return -1;
		}
		int left = 0;
		int right = array.length - 1;
		while (left < right - 1) {
			int mid = left + (right - left) / 2;
			if (array[mid] == target) {
				left = mid;
			} else if (array[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		if (array[right] == target) {
			return right;
		}
		if (array[left] == target) {
			return left;
		}
		return -1;
	}

	public static void main(String[] args) {
		LastOccurence solu = new LastOccurence();

		System.out.println(solu.lastOccurence(null, 1)); // -1

		System.out.println(solu.lastOccurence(new int[0], 1)); // -1

		System.out.println(solu.lastOccurence(new int[] { 1, 2 }, 1)); // 0

		System.out.println(solu.lastOccurence(new int[] { 0, 2 }, 1)); // -1

		System.out.println(solu.lastOccurence(new int[] { 0, 1, 2, 4, 5 }, 1)); // 1

		System.out.println(solu.lastOccurence(new int[] { 0, 2, 4, 5, 7 }, 1)); // -1
	}
}
