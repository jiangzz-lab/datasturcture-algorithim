package datastructure.array.oneD.binarySearch;

public class KthSmallestInTwoSortedArrays {
	public int kthSmallest(int[] a, int[] b, int k) {
		return kthSmallest(a, 0, b, 0, k);
	}
	
	public int kthSmallest(int[] a, int aLeft, int[] b, int bLeft, int k) {
		if (aLeft >= a.length) {
		return b[bLeft + k - 1];
	}
	if (bLeft >= b.length) {
		return a[aLeft + k - 1];
	}
	if (k == 1) {
		return Math.min(a[aLeft], b[bLeft]);
	}
	int aMid = aLeft + k / 2  - 1;
	int bMid = bLeft + k / 2 - 1;
	int aMidValue = aMid >= a.length ? Integer.MAX_VALUE : a[aMid];
	int bMidValue = bMid >= b.length ? Integer.MAX_VALUE : b[bMid];
	if (aMidValue <= bMidValue) {
		return kthSmallest(a, aMid + 1, b, bLeft, k - k / 2);
	} else {
		return kthSmallest(a, aLeft, b, bMid + 1, k - k / 2);
	}
	}
	
	public static void main(String[] args) {
		KthSmallestInTwoSortedArrays solu = new KthSmallestInTwoSortedArrays();
		int[] a = new int[] {1, 2, 3, 5, 6};
		int[] b = new int[] {3, 4, 5, 6, 8, 9, 10};
		
		System.out.println(solu.kthSmallest(a, b, 5)); // 4
	}
}
