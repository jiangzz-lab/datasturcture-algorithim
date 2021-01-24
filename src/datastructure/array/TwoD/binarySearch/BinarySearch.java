package datastructure.array.TwoD.binarySearch;

import java.util.Arrays;

public class BinarySearch {
	/*
	 * search a target in a sorted matrix and get the (row, col) indexes input:
	 * 
	 * input:
	 * int[][] matrix, sorted as -- every row is in ascending order, elements with
	 * larger row index are larger (or equal to those with smaller row indexes)
	 * int target
	 * 
	 * output:
	 * int[][] {row of target, col of target} 
	 * not found ? { -1, -1 } 
	 * multiple targets in input? indexes of any of them
	 * 
	 * corner case: 
	 * null or empty input ? {-1, -1}
	 * 
	 * Solution: 
	 * 
	 * in every step reduce the search range based on the value of the middle element
	 * 
	 * conceptually map the input matrix to a sorted 1D array:
	 * 
	 * (row, col) <--> i-th element -- row = i / cols, col = i % cols
	 * 
	 * 
	 * search range := [low, high], defined with indexes of the 1D array
	 * 
	 * case1: midValue == target ? return its (row, col) 
	 * case2: midValue < target ? search elements after it -- low --> mid + 1
	 * case3: midValue > target ? search elements before it -- high --> high - 1
	 * 
	 * 
	 * TC: n = # of elements in input TC = O(log(n)) * O(1)
	 * SC: O(1)
	 * 
	 */
	
	public int[] binarySearch(int[][] matrix, int target) {
		// sanity check
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return new int[] { -1, -1 };
		}
		int rows = matrix.length;
		int cols = matrix[0].length;
		int low = 0;
		int high = rows * cols - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			int midRow = mid / cols;
			int midCol = mid % cols;
			if (matrix[midRow][midCol] == target) {
				return new int[] { midRow, midCol };
			} else if (matrix[midRow][midCol] < target) {
				low = mid + 1;
			} else { // matrix[midRow][midCol] > target
				high = mid - 1;
			}
		}
		return new int[] { -1, -1 };
	}

	public static void main(String[] args) {
		BinarySearch solu = new BinarySearch();

		int[][] matrix = null;
		int[] res = solu.binarySearch(matrix, 3);
		System.out.println(Arrays.toString(res)); // [-1, -1]

		matrix = new int[][] {};
		res = solu.binarySearch(matrix, 3);
		System.out.println(Arrays.toString(res)); // [-1, -1]

		matrix = new int[][] { {} };
		res = solu.binarySearch(matrix, 3);
		System.out.println(Arrays.toString(res)); // [-1, -1]

		matrix = new int[][] { { 1, 2 }, { 3, 4 } };
		res = solu.binarySearch(matrix, 3);
		System.out.println(Arrays.toString(res)); // [1, 0]

		matrix = new int[][] { { 1, 2 }, { 3, 4 } };
		res = solu.binarySearch(matrix, 3);
		System.out.println(Arrays.toString(res)); // [1, 0]
	}
}
