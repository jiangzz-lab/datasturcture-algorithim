package algorithm.recursion;

import java.util.List;
import java.util.ArrayList;

public class SpiralOrderTraverse {
	public List<Integer> spiral(int[][] matrix) {
		List<Integer> result = new ArrayList<>();
		if (matrix.length == 0) {
			return result;
		}
		spiral(matrix, 0, matrix.length, matrix[0].length, result);
		return result;
	}

	void spiral(int[][] matrix, int offset, int rows, int cols, List<Integer> result) {
		// base cases
		if (rows == 0 || cols == 0) {
			return;
		}
		if (rows == 1) {
			for (int j = 0; j < cols; j++) {
				result.add(matrix[offset][offset + j]);
				return;
			}
		}
		if (cols == 1) {
			for (int i = 0; i < rows; i++) {
				result.add(matrix[offset + i][offset]);
			}
			return;
		}

		for (int j = 0; j < cols - 1; j++) {
			result.add(matrix[offset][offset + j]);
		}

		for (int i = 0; i < rows - 1; i++) {
			result.add(matrix[offset + i][offset + cols - 1]);
		}

		for (int j = cols - 1; j > 0; j--) {
			result.add(matrix[offset + rows - 1][offset + j]);
		}

		for (int i = rows - 1; i > 0; i--) {
			result.add(matrix[offset + i][offset]);
		}

		spiral(matrix, offset + 1, rows - 2, cols - 2, result);
	}

	public static void main(String[] args) {
		SpiralOrderTraverse solu = new SpiralOrderTraverse();
		int[][] matrix = new int[][] { { 1, 2, 3 }, { 4, 5, 6 } };
		List<Integer> result = solu.spiral(matrix);
		for (int num : result) {
			System.out.println(num);
		}
	}
}
