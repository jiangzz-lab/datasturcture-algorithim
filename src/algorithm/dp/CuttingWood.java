package algorithm.dp;

public class CuttingWood {
	public int minCost(int[] cuts, int length) {
		if (length == 0 || cuts.length == 0) {
			return 0;
		}
		int[] array = getExtendedArray(cuts, length);
		int[][] M = new int[array.length - 1][array.length];
		for (int delta = 1; delta <= array.length - 1; delta++) {
			for (int i = 0; i + delta <= array.length - 1; i++) {
				if (delta == 1) {
					M[i][i + delta] = 0;
				} else {
					M[i][i + delta] = Integer.MAX_VALUE;
					for (int k = i + 1; k < i + delta; k++) {
						M[i][i + delta] = Math.min(M[i][i + delta], M[i][k] + M[k][i + delta]);
					}
					M[i][i + delta] += array[i + delta] - array[i];
				}
			}
		}
		return M[0][array.length - 1];
	}

	int[] getExtendedArray(int[] cuts, int length) {
		int[] newArray = new int[cuts.length + 2];
		newArray[0] = 0;
		newArray[cuts.length + 1] = length;
		for (int i = 0; i < cuts.length; i++) {
			newArray[i + 1] = cuts[i];
		}
		return newArray;
	}

	public static void main(String[] args) {
		CuttingWood solu = new CuttingWood();
		System.out.println(solu.minCost(new int[] { 3, 16 }, 20));
	}
}
