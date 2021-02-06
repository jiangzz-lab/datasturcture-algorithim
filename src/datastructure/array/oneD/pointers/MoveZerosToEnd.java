package datastructure.array.oneD.pointers;

import datastructre.array.OneD.*;

public class MoveZerosToEnd {
	public int[] moveZeros(int[] array) {
		if (array == null || array.length <= 1) {
			return array;
		}
		int probe = 0;
		int unknown = array.length - 1;
		while (probe <= unknown) {
			if (array[probe] == 0) {
				swap(array, probe, unknown--);
			} else {
				probe++;
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
		MoveZerosToEnd solu = new MoveZerosToEnd();
		int[] res = solu.moveZeros(new int[] { 0, 1, -2, 0, 0, 3, 5, 0, 8, 9 });
		for (int num : res) {
			System.out.print(num + " ");
		}
		// 9 1 -2 8 5 3 0 0 0 0
	}
}
