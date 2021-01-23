package algorithm.dp;

public class EditDistance {
	public int editDistance(String one, String two) {
		int[][] M = new int[one.length()  + 1][two.length()  + 1];
		for (int i = 0; i < M.length; i++) {
		for (int j = 0; j < M[0].length; j++) {
		if (i == 0) {
		M[i][j] = j;
		
	} else if (j == 0) {
		M[i][j] = i;
	} else {
		int replace = M[i - 1][j - 1] + (one.charAt(i - 1) == two.charAt(j - 1) ? 0 : 1);
	int delete = M[i - 1][j] + 1;
	int insert = M[i][j  - 1] + 1;
	M[i][j] = Math.min(replace, Math.min(delete, insert));
	}
	}
	}
	return M[one.length()][two.length()];
	}
	
	public static void main(String[] args) {
		EditDistance solu = new EditDistance();
		System.out.println(solu.editDistance("ab","dbbabc"));
	}
}
