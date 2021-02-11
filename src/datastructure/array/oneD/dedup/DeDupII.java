package datastructure.array.oneD.dedup;

import java.util.Arrays;
import datastructure.array.oneD.Basics;

public class DeDupII {
	public int[] dedupII(int[] array) {
		if (array == null || array.length <= 2) {
			return array;
		}
		int res = 0;
		for (int probe = 0; probe < array.length; probe++) {
			if (probe + 2 >= array.length || array[probe + 2] != array[probe]) {
				array[res++] = array[probe];
			}
		}
		return Arrays.copyOfRange(array, 0, res);
	}

	public static void main(String[] args) {
		DeDupII solu = new DeDupII();
		Basics helper = new Basics();

		helper.print1DArray(solu.dedupII(new int[] { 1, 1, 2, 2, 2, 3, 4, 5, 5, 5 }));
		// 1 1 2 2 3 4 5 5
	}
}
