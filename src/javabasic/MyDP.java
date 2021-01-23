package javabasic;

public class MyDP {
	public long fibonacci(int K) {
		// check K <= 0
		if (K <= 0) {
		return 0;
	}
	long[] fibo = new long[K + 1];
	// base case
	fibo[0] = 0;
	fibo[1] = 1;
	// for i > 1
	for (int i = 2; i < K + 1; i++) {
		fibo[i] = fibo[i - 2] + fibo[i - 1];
	} 
	return fibo[K];
	}
	
	public int longest(int[] array) {
		// sanity check
		if (array.length <= 1) {
		return array.length;
	}
	int[] len = new int[array.length];
	len[0] = 1;
	int max = 1;
	for (int i = 1; i < array.length; i++) {
		if (array[i] > array[i - 1]) {
		len[i] = len[i - 1] + 1;
	max = Math.max(len[i], max); 
	} else {
		len[i] = 1;
	}
	}
	return max;
	}
	
	public int largestSquareOfMatches(int[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int rowSize = matrix.length;
		int colSize = matrix[0].length;
		int[][] M1 = new int[rowSize][colSize];
		int[][] M2 = new int[rowSize][colSize];
		int[][] M3 = new int[rowSize][colSize];
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < colSize; j++) {
				if (i == 0 || matrix[i - 1][j] == 0 || matrix[i - 1][j] == 1) {
					M1[i][j] = 0;
				} else {
					M1[i][j] = M1[i - 1][j] + 1;
				}
			}
		}

		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < colSize; j++) {
				if (j == 0 || matrix[i][j - 1] == 0 || matrix[i][j - 1] == 2) {
					M2[i][j] = 0;
				} else {
					M2[i][j] = M2[i][j - 1] + 1;
				}
			}
		}

		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < colSize; j++) {
				int maxLength = Math.min(M1[i][j], M2[i][j]);
				for (int len = maxLength; len >= 1; len--) {
					if (i - len >= 0 && j - len >= 0 && M1[i][j - len] >= len && M2[i - len][j] >= len) {
						M3[i][j] = len;
						break;
					}
				}
				max = Math.max(M3[i][j], max);
			}
		}
		return max;
	}
}
