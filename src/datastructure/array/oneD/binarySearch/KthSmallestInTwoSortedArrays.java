package datastructure.array.oneD.binarySearch;

public class KthSmallestInTwoSortedArrays {
	/*
	 * input:
	 * int[] a, int[] b -- both sorted in ascending order; could have duplicates
	 * int k
	 * 
	 * assumption: a, b not null; at least one is not empty
	 * k > 0 && k <= total size of a, b
	 * 
	 * output:
	 * int k-th smallest
	 * 
	 * Solution:
	 * in every step reduce the searching range by half:
	 * 
	 * searching k-th smallest in range a[aLeft … ], b[bLeft … ] (k should be updated accordingly)
	 * 
	 * aMid = aLeft + k / 2 - 1, bMid = bLeft + k / 2 - 1
	 * 
	 * case1: bMid >= b.length || (aMid < a.length && a[Mid] <= b[Mid]) -- aLeft --> aMid + 1 
	 * case2: bMid < b.length && aMid >= a.length || a[Mid] > b[Mid] -- bLeft --> bMid + 1
	 * 
	 * k --> k - k / 2
	 * 
	 * TC = O(logk) 
	 * it takes O(log_2(k)) steps to reduce the searching range from k to 1;
	 * TC of every step = O(1)
	 * 
	 * SC = O(1)
	 * 
	 */
	
	public int kthSmallest(int[] a, int[] b, int k) {
		int aLeft = 0;
		int bLeft = 0;
		while (aLeft < a.length || bLeft < b.length) {
			if (aLeft >= a.length) {
				return b[bLeft + k - 1];
			}
			if (bLeft >= b.length) {
				return a[aLeft + k - 1];
			}
			if (k == 1) {
				return Math.min(a[aLeft], b[bLeft]);
			}
			int aMid = aLeft + k / 2 - 1;
			int bMid = bLeft + k / 2 - 1;
			if (bMid >= b.length || (aMid < a.length && a[aMid] < b[bMid])) {
				aLeft = aMid + 1;
			} else {
				bLeft = bMid + 1;
			}
			k -= k / 2;
		}
		return Integer.MAX_VALUE;
	}

	public static void main(String[] args) {
		KthSmallestInTwoSortedArrays solu = new KthSmallestInTwoSortedArrays();

		int[] a = new int[] { 1, 2, 3 };
		int[] b = new int[] { 3, 4, 5, 6, 8, 9, 10, 20 };
		System.out.println(solu.kthSmallest(a, b, 10)); // 10

		a = new int[] { 1, 4, 5, 5, 8, 12, 12, 12 };
		b = new int[] { 2, 2, 3, 7, 9, 9, 9 };
		System.out.println(solu.kthSmallest(a, b, 12)); // 9

	}
}
