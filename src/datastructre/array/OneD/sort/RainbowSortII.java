package datastructre.array.OneD.sort;

import datastructure.array.oneD.Basics;

public class RainbowSortII {
	public int[] rainbowSortII(int[] array) {
		if (array == null || array.length <= 1) {
			return array;
		}
		int one = 0;
		int two = 0;
		int probe = 0;
		int unknown = array.length - 1;
		while (probe <= unknown) {
			if (array[probe] == 0) {
				swap(array, one, probe);
				if (two > one) {
					swap(array, two, probe);
				}
				one++;
				two++;
				probe++;
			} else if (array[probe] == 1) {
				swap(array, two++, probe++);
			} else if (array[probe] == 2) {
				probe++;
			} else {
				swap(array, probe, unknown--);
			}
		}
		return array;
	}

	void swap(int[] array, int left, int right) {
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}

	public static void main(String[] args) {
		RainbowSortII solu = new RainbowSortII();
		Basics helper = new Basics();

		helper.print1DArray(solu.rainbowSortII(new int[] { 1, 2, 3, 0, 2, 1, 0, 3, 3 }));
		// 0 0 1 1 2 2 3 3 3
	}
}
