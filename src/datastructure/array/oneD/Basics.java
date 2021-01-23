package datastructure.array.oneD;

public class Basics {
	// print a 1D int array
	public void print1DArray(int[] array) {
		if (array == null || array.length == 0) {
			System.out.println("No elements!");
			return;
		}
		for (int num : array) {
			System.out.print(num + " ");
		}
		System.out.println();
	}

	// find min of a 1D int array
	public int min(int[] array) {
		// return 0 if null or empty input
		if (array == null || array.length == 0) {
			return 0;
		}
		int min = array[0];
		for (int i = 1; i < array.length; i++) {
			min = Math.min(min, array[i]);
		}
		return min;
	}

	// insert value into a 1D array at index
	public int[] insert(int[] array, int value, int index) {
		if (array == null) {
			return new int[] { value };
		}
		int[] res = new int[array.length + 1];
		for (int i = 0; i < index; i++) {
			res[i] = array[i];
		}
		res[index] = value;
		for (int i = index + 1; i < res.length; i++) {
			res[i] = array[i - 1];
		}
		return res;
	}

	// reverse a 1D array
	public int[] reverse(int[] array) {
		if (array == null || array.length <= 1) {
			return array;
		}
		for (int i = 0; i <= array.length / 2; i++) {
			swap(array, i, array.length - 1 - i);
		}
		return array;
	}

	void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static void main(String[] args) {
		Basics solu = new Basics();
		int[] array = new int[] { 1, 6, 3, 9, 8 };
		// test min method
		System.out.println(solu.min(array));
		array = new int[] { 1 };
		System.out.println(solu.min(array));
		array = new int[0];
		System.out.println(solu.min(array));

		// test insert
		int[] res = solu.insert(array, 1, 0);
		solu.print1DArray(res);
		res = solu.insert(res, 2, 0);
		solu.print1DArray(res);
		res = solu.insert(res, 3, 1);
		solu.print1DArray(res);

		// test reverse
		solu.print1DArray(solu.reverse(null));
		solu.print1DArray(solu.reverse(array)); // empty array
		array = new int[] {1, 2, 3, 4, 5};
		solu.print1DArray(solu.reverse(array));
	}
}
