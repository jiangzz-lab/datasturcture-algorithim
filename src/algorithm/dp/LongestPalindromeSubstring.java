package algorithm.dp;

public class LongestPalindromeSubstring {
	 public String longestPalindrome(String s) {
	        if (s == null || s.length() == 0) {
	            return s;
	        }
	        int[][] M = new int[s.length()][s.length() + 1];
	        int max = 1;
	        int maxStart = 0;
	        for (int j = 0; j <= s.length(); j++) {
	            for (int i = j - 1; i >= 0; i--) {
	                if (j == i + 1) {
	                    M[i][j] = 1;
	                } else if (j > i + 1) {
	                    if (s.charAt(i) == s.charAt(j - 1) && M[i + 1][j - 1] == j - i - 2) {
	                        M[i][j] = M[i + 1][j - 1] + 2;
	                    } else {
	                        M[i][j] = Math.max(M[i + 1][j], M[i][j - 1]);
	                    }  
	                }
	                if (M[i][j] > max) {
	                    max = M[i][j];
	                    maxStart = i;
	                }
	            }
	        }
	        return s.substring(maxStart, maxStart + max);
	    }
	 
	 public static void main(String[] args) {
		 LongestPalindromeSubstring solu = new LongestPalindromeSubstring();
		 System.out.println(solu.longestPalindrome("cbbd"));
	 }
}
