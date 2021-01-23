package javabasic;

import java.util.Random;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class MyArrays {
	public int[] mergeSort(int[] array) {
		// sanity check
		if (array == null || array.length == 0) {
			return array;
		}
		return mergeSort(array, 0, array.length - 1);
	}

	public int[] mergeSort(int[] array, int left, int right) {
		// base case
		if (left == right) {

			return new int[] { array[left] };
		}
		// calculate mid index
		int mid = left + (right - left) / 2;
		// sort left half and right half
		int[] leftResult = mergeSort(array, left, mid);
		int[] rightResult = mergeSort(array, mid + 1, right);
		// return the result of merging leftResult and rightResult
		return merge(leftResult, rightResult);
	}

	public int[] merge(int[] left, int[] right) {
		int[] result = new int[left.length + right.length];
		int leftIndex = 0;
		int rightIndex = 0;
		int cur = 0;
		while (leftIndex < left.length && rightIndex < right.length) {
			if (left[leftIndex] < right[rightIndex]) {
				result[cur++] = left[leftIndex++];
			} else {
				result[cur++] = right[rightIndex++];
			}
		}
		while (leftIndex < left.length) {
			result[cur++] = left[leftIndex++];
		}
		while (rightIndex < right.length) {
			result[cur++] = right[rightIndex++];
		}
		return result;
	}

	public int[] quickSort(int[] array) {
		// sanity check
		if (array == null || array.length == 0) {
			return array;
		}
		return quickSort(array, 0, array.length - 1);
	}

	public int[] quickSort(int[] array, int left, int right) {
		// base case
		if (left >= right) {
			return array;
		}
		// partition array
		int pivotIndex = partition(array, left, right);
		// sort the subarrays
		quickSort(array, left, pivotIndex - 1);
		quickSort(array, pivotIndex + 1, right);
		return array;
	}

	public int partition(int[] array, int left, int right) {
		int pivotIndex = getPivotIndex(left, right);
		int pivot = array[pivotIndex];
		swap(array, pivotIndex, right);
		// define the bounds
		// [left, smallRight) < pivot; [smallRight, largeLeft] to be explored;
		// (largeLeft, right - 1] > pivot
		int smallRight = left;
		int largeLeft = right - 1;
		while (smallRight <= largeLeft) {
			if (smallRight < pivot) {
				smallRight++;
			} else {
				swap(array, smallRight, largeLeft--);
			}
		}
		swap(array, smallRight, right);
		return smallRight;
	}

	public int getPivotIndex(int left, int right) {
		Random rand = new Random();
		return left + rand.nextInt(right - left + 1);
	}

	public void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public int[] kSmallest(int[] array, int k) {
		// check null, empty
		if (array == null || array.length == 0) {
			return new int[0];
		}
		// check array.length <= k
		if (array.length <= k) {
			return array;
		}
		// declare a max heap
		Queue<Integer> maxHeap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// do not use “==” here !!!
				if (o1.equals(o2)) {
					return 0;
				}
				return o1 > o2 ? -1 : 1;
			}
		});
		for (int i = 0; i < array.length; i++) {
			// add the first k elements into the maxHeap without extra steps
			// not using internal heapify() here, TC klogk
			if (i < k) {
				maxHeap.offer(array[i]);
			} else {
				if (array[i] < maxHeap.peek()) {
					maxHeap.poll();
					maxHeap.offer(array[i]);
				}
			}
		}
		int[] result = new int[k];
		for (int i = 0; i < k; i++) {
			result[i] = maxHeap.poll();
		}
		return result;
	}

	// assumption: matrix != null, N > 0, M > 0, K > 0, K <= N * M
	public int kthSmallest(int[][] matrix, int k) {
		// create minHeap and boolean[][] visited to record generated
		// init: Cell of matrix[0][0] in min heap; visited all false
		Queue<Cell> minHeap = new PriorityQueue<Cell>(k, new Comparator<Cell>() {
			public int compare(Cell c1, Cell c2) {
				if (c1.value == c2.value) {
					return 0;
				}
				return c1.value < c2.value ? -1 : 1;
			}
		});
		minHeap.offer(new Cell(0, 0, matrix[0][0]));
		int row = matrix.length;
		int col = matrix[0].length;
		boolean[][] visited = new boolean[row][col];
		// expand + generate
		while (k-- > 1) {
			Cell cur = minHeap.poll();
			if (cur.row + 1 < row && !visited[cur.row + 1][cur.col]) {
				minHeap.offer(new Cell(cur.row + 1, cur.col, matrix[cur.row + 1][cur.col]));
				visited[cur.row + 1][cur.col] = true;
			}
			if (cur.col + 1 < col && !visited[cur.row][cur.col + 1]) {
				minHeap.offer(new Cell(cur.row, cur.col + 1, matrix[cur.row][cur.col + 1]));
				visited[cur.row][cur.col + 1] = true;
			}
		}
		return minHeap.peek().value;
	}

	public class Cell {
		public int col;
		public int row;
		public int value;

		public Cell(int row, int col, int value) {
			this.row = row;
			this.col = col;
			this.value = value;
		}
	}

	public int missing(int[] array) {
		// check empty
		if (array.length == 0) {
			return 1;
		}
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < array.length; i++) {
			set.add(array[i]);
		}
		int missing = 0;
		for (int i = 1; i <= array.length + 1; i++) {
			if (!set.contains(i)) {
				missing = i;
				break;
			}
		}
		return missing;
	}

	// a,b not null
	public List<Integer> common(int[] a, int[] b) {
		// step1: add elements in a to a hash map
		Map<Integer, Integer> countMap = new HashMap<>();
		for (int i = 0; i < a.length; i++) {
			Integer count = countMap.get(a[i]);
			if (count == null) {
				countMap.put(a[i], 1);
			} else {
				countMap.put(a[i], count + 1);
			}
		}
		List<Integer> result = new ArrayList<>();
		// step2: loop b and add common elements into the result
		for (int i = 0; i < b.length; i++) {
			Integer count = countMap.get(b[i]);
			if (count != null && count != 0) {
				result.add(b[i]);
				countMap.put(b[i], count - 1);
			}
		}
		return result;
	}

	public int[] reOrder(int[] array) {
		if (array == null || array.length <= 3) {
			return array;
		}
		reOrder(array, 0, array.length - 1 - array.length % 2);
		return array;
	}

	public void reOrder(int[] array, int left, int right) {
		// base case
		if (left >= right - 1) {
			return;
		}
		int size = right - left + 1;
		int mid = left + size / 2;
		int leftmid = left + size / 4;
		int rightmid = left + 3 * size / 4;
		// exchange [leftmid, mid - 1] and [mid, rightmid - 1]
		reverse(array, leftmid, mid - 1);
		reverse(array, mid, rightmid - 1);
		reverse(array, leftmid, rightmid - 1);
		// reOrder [left, left + 2 * (leftmid - left) - 1], [left + 2 * (leftmid -
		// left), right]
		reOrder(array, left, left + 2 * (leftmid - left) - 1);
		reOrder(array, left + 2 * (leftmid - left), right);
	}
	
	public int[] reverse(int[] array, int start, int end) {
		// sanity check
		if (start >= end) {
			return array;
		}
		while (start <= end) {
			swap(array, start++, end--);
		}
		return array;
	}

	public int longestConsecutiveOnes(int[] nums, int k) {
		int count = 0;
		int longest = 0;
		int slow = 0;
		int fast = 0;
		while (fast < nums.length) {
			if (nums[fast] == 1) {
				longest = Math.max(longest, ++fast - slow);
			} else if (count < k) {
				count++;
				longest = Math.max(longest, ++fast - slow);
			} else if (nums[slow++] == 0) {
				count--;
			}
		}
		return longest;
	}

	public List<Integer> spiral(int[][] matrix) {
		List<Integer> result = new ArrayList<>();
		spiral(matrix, 0, matrix.length, result);
		return result;
	}

	public void spiral(int[][] matrix, int offset, int size, List<Integer> result) {
		// base case
		if (size == 0) {
			return;
		}
		if (size == 1) {
			result.add(matrix[offset][offset]);
			return;
		}
		// traverse current circle first
		// upper
		for (int i = offset; i <= offset + size - 2; i++) {
			result.add(matrix[offset][i]);
		}
		// right
		for (int i = offset; i <= offset + size - 2; i++) {
			result.add(matrix[i][offset + size - 1]);
		}
		// lower
		for (int i = offset + size - 1; i >= offset + 1; i--) {
			result.add(matrix[offset + size - 1][i]);
		}
		// left
		for (int i = offset + size - 1; i >= offset + 1; i--) {
			result.add(matrix[i][offset]);
		}
		// traverse the next inner circle
		spiral(matrix, offset + 1, size - 2, result);
	}

	public List<List<Integer>> nqueens(int n) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> solution = new ArrayList<>();
		nqueens(n, 0, solution, result);
		return result;
	}

	public void nqueens(int n, int index, List<Integer> solution, List<List<Integer>> result) {
		// base case
		if (index == n) {
			result.add(new ArrayList(solution));
			return;
	}
	// check whether col 0, 1, … n - 1 are valid for Qindex
	for (int i = 0; i < n; i++) {
		if (isValid(index, i, solution)) {
			solution.add(i);
			nqueens(n, index + 1, solution, result);
			solution.remove(solution.size() - 1);
	}
	}
	}

	public boolean isValid(int index, int y, List<Integer> solution) {
		// check against queens 0, 1 …  
		for (int j = 0; j < solution.size(); j++) {
			if (y == solution.get(j) || Math.abs(index - j) == Math.abs(solution.get(j) - y)) {
		return false;
	}
	}
	return true;
	}
	
	public int minJump(int[] array) {
		long[] M = new long[array.length];
		// base case
		M[0] = 0;
		// i from 1 to M.length  - 1
		for (int i = 1; i < M.length; i++) {
		M[i] = Integer.MAX_VALUE + 1L;
		for (int j = 0; j < i; j++) {
		if (M[j] <= Integer.MAX_VALUE && i - j <= array[j]) {
		M[i] = Math.min(M[i], M[j] + 1);
	}
	}
	}
	return M[M.length - 1] > Integer.MAX_VALUE ? -1: (int)M[M.length - 1];
	}
	
	public int[] largestSum(int[] array) {
		// base case
		int globalMax = array[0];
	int maxLeft = 0;
	int maxRight = 0;
	int lastMax = 0;
	int lastLeft = 0;
	// i > 0
	for (int i = 1; i < array.length; i++) {
		if (lastMax + array[i] < array[i]) {
		lastMax = array[i];
		lastLeft = i;
	} else {
		lastMax += array[i]; 
	}
	if (lastMax > globalMax) {
		globalMax = lastMax;
		maxLeft = lastLeft;
		maxRight = i;
	}
	}
	return new int[]{globalMax, maxLeft, maxRight};
	}
	
	public int largestSquareSurroundedByOne(int[][] matrix) {
		// check empty
		int row = matrix.length;
		int col = matrix[0].length;
		if (row == 0 || col == 0) {
		return 0;
	}
	int[][] M1 = new int[row][col];
	int[][] M2 = new int[row][col];
	int[][] M = new int[row][col];

	for (int j = 0; j < col; j++) {
		for (int i = 0; i < row; i++) {
		if (i == 0) {
		M1[i][j] = matrix[i][j];
	} else if (matrix[i][j] == 1) {
		M1[i][j] = M1[i - 1][j] + 1;
	} else {
		M1[i][j] = 0;
	}
	}
	}

	for (int i = 0; i < row; i++) {
		for (int j = 0; j < col; j++) {
			if (j == 0) {
		M2[i][j] = matrix[i][j]; 
	} else if (matrix[i][j] == 1) {
		M2[i][j] = M2[i][j - 1] + 1;
	} else {
		M2[i][j] = 0;
	}
	}
	}

	for (int i = 0; i < row; i++) {
		for (int j = 0; j < col; j++) {
		int len = Math.min(M1[i][j], M2[i][j]);
		for (int k = len; k >= 1; k--) {
		if (M1[i][j - k + 1] >= k && M2[i - k + 1][j] >= k) {
		M[i][j] = k;
		break;
	}
	}
	}
	}
	return max(M);
	}
	
	public int max(int[][] M) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < M.length; i++) {
			for (int j = 0; j < M[0].length; j++) {
		max = Math.max(max, M[i][j]);
	}
	}
	return max;
	}
	
	public int largestCross(int[][] matrix) {
		// check empty
	int row = matrix.length;
	int col = matrix[0].length;
	if (row == 0 || col == 0) {
		return 0;
	}

	int[][] M1 = new int[row][col];
	int[][] M2 = new int[row][col];
	int[][] M3 = new int[row][col];
	int[][] M4 = new int[row][col];
	int[][] M = new int[row][col];

	// calculate M1
	for (int i = 0; i < row; i++) {
		for (int j = 0; j < col; j++) {
		if (i == 0 || j == 0) {
		M1[i][j] = matrix[i][j];
	} else if (matrix[i][j] == 1) {
		M1[i][j] = M1[i - 1][j - 1] + 1; 
	} else {
		M1[i][j] = 0;
	}
	}
	}

	for (int i = 0; i < row; i++) {
		for (int j = col - 1; j >= 0; j--) {
		if (i == 0 || j == col - 1) {
		M2[i][j] = matrix[i][j];
	} else if (matrix[i][j] == 1) {
		M2[i][j] = M2[i - 1][j + 1] + 1;
	} else {
		M2[i][j] = 0;
	}
	}
	}

	for (int i = row - 1; i >= 0; i--) {
		for (int j = 0; j < col; j++) {
		if (i == row - 1 || j == 0) {
		M3[i][j] = matrix[i][j];
	} else if (matrix[i][j] == 1) {
		M3[i][j] = M3[i + 1][j - 1] + 1;
	} else {
		M3[i][j] = 0;
	}
	}
	}

	for (int i = row - 1; i >= 0; i--) {
		for (int j = col - 1; j >= 0; j--) {
		if (i == row - 1 || j == col - 1) {
		M4[i][j] = matrix[i][j];
	} else if (matrix[i][j] == 1) {
		M4[i][j] = M4[i + 1][j + 1] + 1;
	} else {
		M4[i][j] = 0;
	}
	}
	}

	for (int i = 0; i < row; i++) {
		for (int j = 0; j < col; j++) {
		M[i][j] = Math.min(Math.min(M1[i][j], M2[i][j]), Math.min(M3[i][j], M4[i][j]));
	}
	}

	return max(M);
	}
	
	public int watertrapped(int[] array) {
		// corner cases
		if (array.length <= 1) {
		return 0;
	}
	int[] leftMax = new int[array.length];
	leftMax[0] = array[0];
	int[] rightMax = new int[array.length];
	rightMax[array.length - 1] = array[array.length - 1];
	for (int i = 1; i < array.length; i++) {
		leftMax[i] = Math.max(leftMax[i - 1], array[i]);
	}
	for (int i = array.length - 2; i >= 0; i--) {
		rightMax[i] = Math.max(array[i], rightMax[i  + 1]);
	}
	int sum = 0;
	for (int i = 0; i < array.length - 1; i++) {
		sum += Math.min(leftMax[i], rightMax[i]) - array[i];
	}
	return sum;
	}

	public int maxTrapped(int[][] matrix) {
		int sum = 0;
		int m = matrix.length;
		int n = matrix[0].length;
		int[][] M1 = new int[m][n];
		int[][] M2 = new int[m][n];
		int[][] M3 = new int[m][n];
		int[][] M4 = new int[m][n];

		for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++) {
		if (i == 0 || matrix[i][j] > M1[i - 1][j]) {
		M1[i][j] = matrix[i][j];
	} else {
		M1[i][j] = M1[i - 1][j];
	}
	}
	}

	for (int j = 0; j < n; j++) {
		for (int i = 0; i < m; i++) {
		if (j == 0 || matrix[i][j] > M2[i][j - 1]) {
		M2[i][j] = matrix[i][j];
	} else {
		M2[i][j] = M2[i][j - 1];
	}
	}
	}

	for (int i = m - 1; i >= 0; i--) {
		for (int j = 0; j < n; j++) {
		if (i == m - 1 || matrix[i][j] > M3[i + 1][j]) {
		M3[i][j] = matrix[i][j];
	} else {
		M3[i][j] = M3[i + 1][j];
	}
	}
	}

	for (int j = n - 1; j >= 0; j--) {
		for (int i = 0; i < m; i++) {
		if (j == n - 1 || matrix[i][j] > M4[i][j + 1]) {
		M4[i][j] = matrix[i][j];
	} else {
		M4[i][j] = M4[i][j + 1];
	}
	}
	}

	for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++) {
		sum += Math.min(Math.min(M1[i][j], M2[i][j]), Math.min(M3[i][j], M4[i][j])) - matrix[i][j];
	}
	}

	return sum;
	}

	static class Line {
		double k;
		double b;
		
	public Line(double k, double b) {
		this.k = k;
		this.b = b;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
		return true;
	}
	if (!(obj instanceof Line)) {
		return false;
	}
	Line another = (Line) obj;
	return this.k == another.k && this.b == another.b;
	}
	@Override
	public int hashCode() {
		return (int) ((int)k * 101 + b);
	}
	}

}
