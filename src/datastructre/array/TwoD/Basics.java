package datastructre.array.TwoD;

public class Basics {
	// print a 2D Array
	public void print2DArray(int[][] array) {
		if (array == null || array.length == 0) {
			System.out.println("No element!");
			return;
		}

		int count = 0;
		for (int row = 0; row < array.length; row++) {
			// not traverse a null
			if (array[row] == null) {
				continue;
			}
			for (int col = 0; col < array[row].length; col++) {
				System.out.print(array[row][col] + " ");
				count++;
			}
			// separate rows
			System.out.println();
		}
		// if all rows are null or empty, say no element
		if (count == 0) {
			System.out.println("No element!");
		}
	}

	// get the trace of a square matrix
	public int trace(int[][] matrix) {
		if (matrix == null || matrix.length == 0) {
			return 0;
		}
		int trace = 0;
		for (int i = 0; i < matrix.length; i++) {
			trace += matrix[i][i];
		}
		return trace;
	}

	// get the sum of two matrix
	public int[][] sum(int[][] m1, int[][] m2) {
		// assumption: m1, m2 not null, empty and have the same row, col sizes
		int[][] res = new int[m1.length][m1[0].length];
		for (int row = 0; row < res.length; row++) {
			for (int col = 0; col < res[0].length; col++) {
				res[row][col] = m1[row][col] + m2[row][col];
			}
		}
		return res;
	}

	// get the sum of elements in a 2D array
	public int elementSum(int[][] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int sum = 0;
		for (int row = 0; row < array.length; row++) {
			if (array[row] == null) {
				continue;
			}
			for (int col = 0; col < array[row].length; col++) {
				sum += array[row][col];
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		Basics solu = new Basics();

		// test trace
		int[][] matrix = new int[][] { { 1, 2, 3 }, { 4, 2, 2 }, { 1, 2, 1 } };
		System.out.println(solu.trace(matrix)); // 4

		// test sum
		int[][] m1 = new int[][] { { 1, 0 }, { 0, 1 } };
		int[][] m2 = new int[][] { { 0, 1 }, { 1, 0 } };
		int[][] res = solu.sum(m1, m2);
		solu.print2DArray(res);
		// 1 1
		// 1 1
		
		// test elementSum
		System.out.println(solu.elementSum(res)); // 4
		System.out.println(solu.elementSum(null)); // 0
		System.out.println(solu.elementSum(new int[][] {})); // 0
		System.out.println(solu.elementSum(new int[][] {{}})); // 0
	}
}
