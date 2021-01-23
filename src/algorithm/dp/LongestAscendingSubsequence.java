package algorithm.dp;

public class LongestAscendingSubsequence {
	public int longest(int[] array) {
		if (array.length == 0) {
			return 0;
		}
		int[] M = new int[array.length];
		M[0] = 1;
		int max = 1;
		for (int i = 1; i < array.length; i++) {
			M[i] = 1;
			for (int j = 0; j < i; j++) {
				if (array[j] < array[i])
					M[i] = Math.max(M[i], M[j] + 1);
			}
			max = Math.max(M[i], max);
		}
		return max;
	}

	// TC O(n^2)
	// SC O(n)

	public static void main(String[] args) {
		LongestAscendingSubsequence solu = new LongestAscendingSubsequence();
		System.out.println(solu.longest(new int[] { 123, 236, 227, 258, 138, 441, 496, 479, 124, 389, 84 }));
	}
}
