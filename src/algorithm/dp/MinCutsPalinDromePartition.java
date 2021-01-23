package algorithm.dp;

public class MinCutsPalinDromePartition {
	public int minCuts(String input) {
		int[] M = new int[input.length() + 1];
		M[0] = 0;
		for (int i = 1; i <= input.length(); i++) {
			if (isPalindrome(input, 0, i)) {
				continue;
			}
			M[i] = Integer.MAX_VALUE;
			for (int j = 1; j < i; j++) {
				if (isPalindrome(input, j, i)) {
					M[i] = Math.min(M[i], M[j] + 1);
				}
			}
		}
		return M[input.length()];
	}
	
	// whether input[j, i) is palindrome
	boolean isPalindrome(String input, int j, int i) {
		while (j < --i) {
			if (input.charAt(j++) != input.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		MinCutsPalinDromePartition solu = new MinCutsPalinDromePartition();
		System.out.println(solu.minCuts("ababbbabbababa"));
	}
}
