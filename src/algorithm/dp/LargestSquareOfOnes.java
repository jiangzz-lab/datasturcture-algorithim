package algorithm.dp;

public class LargestSquareOfOnes {
	public int largest(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int max = 0;
		int rows = matrix.length;
		int cols = matrix[0].length;
		int[][] M = new int[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (i == 0 || j == 0 || matrix[i][j] == 0) {
					M[i][j] = matrix[i][j];
				} else {
					M[i][j] = Math.min(Math.min(M[i - 1][j], M[i][j - 1]), M[i - 1][j - 1]) + 1;
				}
				max = Math.max(M[i][j], max);
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		LargestSquareOfOnes solu = new LargestSquareOfOnes();
		int[][] matrix = new int[][]{{0, 0, 0, 0}, {1, 1, 1, 1}, {0, 1, 1, 1}, {1, 0, 1, 1}};
		System.out.println(solu.largest(matrix));
		matrix = new int[][] {{1, 1, 1, 1}, {1, 1, 1, 1}, {0, 1, 1, 1}, {1, 1, 1, 1}};
		System.out.println(solu.largest(matrix));
		System.out.println(solu.largest(null));
		System.out.println(solu.largest(new int[0][0]));
		System.out.println(solu.largest(new int[10][0]));
	}
}
