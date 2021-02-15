package datastructure.array.oneD.merge;

import java.util.List;
import java.util.ArrayList;

public class CommonOfTwoSortedArrays {
	public List<Integer> common(int[] A, int[] B) {
		List<Integer> res = new ArrayList<>();
		int a = 0;
		int b = 0;
		while (a < A.length && b < B.length) {
			if (A[a] == B[b]) {
				res.add(A[a]);
				a++;
				b++;
			} else if (A[a] < B[b]) {
				a++;
			} else {
				b++;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		CommonOfTwoSortedArrays solu = new CommonOfTwoSortedArrays();

		int[] A = new int[] { 1, 1, 1, 2, 3, 3, 4, 5, 5, 5 };
		int[] B = new int[] { 1, 1, 2, 2, 3, 3, 4, 5 };

		List<Integer> res = solu.common(A, B);
		for (int num : res) {
			System.out.print(num + " ");
		}
		// 1 1 2 3 3 4 5
	}
}
