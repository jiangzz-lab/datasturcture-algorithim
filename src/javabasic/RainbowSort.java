package javabasic;

public class RainbowSort {
	public int[] rainbowSort(int[] array) {
		// sanity check
		if (array == null || array.length == 0) {
			return array;
		}
		// define the ranges: [0, negRight) == -1; [negRight, zeroRight) == 0; [zeroRight, posLeft] to be explored; (posRight, n - 1] == 1
		int negRight = 0;
		int zeroRight = 0;
		int posLeft = array.length - 1;
		while (zeroRight <= posLeft) {
			if (array[zeroRight] == 0) {
				zeroRight++;
			} else if (array[zeroRight] == -1) {
				swap(array, negRight++, zeroRight++);
			} else {
				swap(array, posLeft--, zeroRight);
			}
		}
		return array;
	}

	private void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
