package algorithm.dp;

public class MinJump {
	public int minJump(int[] array) {
		int[] M = new int[array.length];
		M[array.length - 1] = 0;
		for (int i = array.length - 2; i >= 0; i--) {
			M[i] = Integer.MAX_VALUE;
			for (int j = Math.min(array.length - 1, i + array[i]); j > i; j--) {
				if (M[j] < Integer.MAX_VALUE) {
					M[i] = Math.min(M[i], M[j] + 1);
				}
			}
		}
		return M[0] < Integer.MAX_VALUE ? M[0] : -1;
	}
	
	public static void main(String[] args) {
		MinJump solu = new MinJump();
		System.out.println(solu.minJump(new int[] {3, 3, 1, 0, 4}));
		System.out.println(solu.minJump(new int[] {2, 1, 1, 0, 2}));
		System.out.println(solu.minJump(new int[] {2, 3, 4, 0}));
	}
}
