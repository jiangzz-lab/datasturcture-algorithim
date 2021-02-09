package datastructure.array.oneD.dedup;

import java.util.Arrays;
import datastructure.array.oneD.Basics;

public class DeDupI {
	public int[] dedup(int[] array) {
		if (array == null || array.length <= 1) {
			return array;
		}
		int res = 1;
		for (int probe = 1; probe < array.length; probe++) {
			if (array[probe] != array[probe - 1]) {
				array[res++] = array[probe];
			}
		}
		return Arrays.copyOfRange(array, 0, res);
	}

	public static void main(String[] args) {
		DeDupI solu = new DeDupI();
		Basics helper = new Basics();

		helper.print1DArray(solu.dedup(new int[] { 1, 1, 2, 2, 2, 3, 3, 4, 5, 5 }));
		// 1 2 3 4 5
	}
}
