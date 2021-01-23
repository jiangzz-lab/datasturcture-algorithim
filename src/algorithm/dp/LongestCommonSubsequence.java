package algorithm.dp;

public class LongestCommonSubsequence {
	public int longest(String one, String two) {
		int rows = one.length() + 1;
		int cols = two.length() + 1;
		int[][] M = new int[rows][cols];
		int max = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (i == 0 || j == 0) {
					M[i][j] = 0;
				} else if (one.charAt(i - 1) == two.charAt(j - 1)) {
					M[i][j] = M[i - 1][j - 1] + 1;
				} else {
					M[i][j] = Math.max(M[i][j - 1], M[i - 1][j]);
				}
				max = Math.max(M[i][j], max);
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		LongestCommonSubsequence solu = new LongestCommonSubsequence();
		System.out.println(solu.longest("abcde", "cbabdfe"));
	}
}
