package algorithm.dp;

public class MaxProductOfCuttingRope {
	public int maxProduct(int length) {
		int[] M = new int[length + 1];
		// base cases
		M[2] = 1;
		for (int i = 3; i <= length; i++) {
			for (int j = 1; j < i; j++) {
				M[i] = Math.max(M[i], Math.max(j, M[j]) * (i - j));
			}
		}
		return M[length];
	}
	// TC O(n^2)
	// SC O(n)
	
	public static void main(String[] args) {
		MaxProductOfCuttingRope solu = new MaxProductOfCuttingRope();
		System.out.println(solu.maxProduct(2));
		System.out.println(solu.maxProduct(3));
		System.out.println(solu.maxProduct(12));
	}
}
