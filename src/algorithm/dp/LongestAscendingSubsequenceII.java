package algorithm.dp;

import java.util.Arrays;

public class LongestAscendingSubsequenceII {
	public int[] longest(int[] array) {
		// check empty
		if (array.length == 0) {
			return array;
		}
		int[] M = new int[array.length];
		int[] pred = new int[array.length];
		int maxIndex = 0;
		M[0] = 1;
		for (int i = 1; i < M.length; i++) {
			pred[i] = -1;
			M[i] = 1;
			for (int j = 0; j < i; j++) {
				if (array[j] < array[i] && M[i] < M[j] + 1) {
					M[i] = M[j] + 1;
					pred[i] = j;
				}
			}
			if (M[i] > M[maxIndex]) {
				maxIndex = i;
			}
		}
		return traceBack(array, maxIndex, pred, M);
	}

	public int[] traceBack(int[] array, int maxIndex, int[] pred, int[] M) {
		int[] result = new int[M[maxIndex]];
		for (int i = result.length - 1; i >= 0; i--) {
			result[i] = array[maxIndex];
			maxIndex = pred[maxIndex];
		}
		return result;
	}
	
	public static void main(String[] args) {
		LongestAscendingSubsequenceII solu = new LongestAscendingSubsequenceII();
		int[] result = solu.longest(new int[] {5, 2, 6, 3, 4, 7, 5});
		System.out.print(Arrays.toString(result));
	}
}
