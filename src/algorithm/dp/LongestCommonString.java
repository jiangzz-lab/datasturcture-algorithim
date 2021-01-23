package algorithm.dp;

public class LongestCommonString {
	public String longestCommon(String one, String two) {
		int rows = one.length() + 1;
		int cols = two.length() + 1;
		int[][] M = new int[rows][cols];
		int max = 0;
		int right = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (i == 0 || j == 0 || one.charAt(i - 1) != two.charAt(j - 1)) {
					M[i][j] = 0;
				} else {
					M[i][j] = M[i - 1][j - 1] + 1;
					if (M[i][j] > max) {
						max = M[i][j];
						right = i;
					}
				}
			}
		}
		return one.substring(right - max, right);
	}

	public static void main(String[] args) {
		LongestCommonString solu = new LongestCommonString();
		System.out.println(solu.longestCommon("aaaaaa", "bbb"));
		System.out.println(solu.longestCommon("abcde", "cdf"));
	}
}
