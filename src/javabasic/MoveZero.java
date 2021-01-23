package javabasic;

public class MoveZero {
	public int[] moveZeros(int[] array) {
		// sanity check
		if (array.length == 0) {
			return array;
		}
		// define the range to explore [leftBound, rightBound]; [0, leftBound) != 0; (rightBound, n - 1] == 0
		int leftBound = 0;
		int rightBound = array.length - 1;
		while (leftBound <= rightBound) {
			if (array[leftBound] != 0) {
				leftBound++;
			} else {
				swap(array, leftBound, rightBound--);
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
