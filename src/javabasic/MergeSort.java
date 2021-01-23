package javabasic;

public class MergeSort {
	public int[] mergeSort(int[] array) {
			// sanity check
			if (array == null || array.length == 0) {
				return array;
			}
			// solve it recursively using overloaded mergeSort()
			int[] result = mergeSort(array, 0, array.length - 1);
			return result;
			}

	private int[] mergeSort(int[] array, int left, int right) {
		// base case
		if (left == right) {
			return new int[]{array[left]};
		}
		// recursive rule
		// sort the left half and right half subarray
		int mid = left + (right - left) / 2;
		int[] leftResult = mergeSort(array, left, mid);
		int[] rightResult = mergeSort(array, mid + 1, right);
		// merge the sorted left half and right half and keep the order 
		int[] result = merge(leftResult, rightResult);
		return result;
	}

	// merge two sorted arrays into one array keeping the order
	// assumption1 : arrays are sorted in ascending order
	// assumption2 : arrays are not null and array.length > 0
	private int[] merge(int[] one, int[] two) {
		int[] result = new int[one.length + two.length];
		int k = 0;
		int i = 0;
		int j = 0;
		while (i < one.length && j < two.length) {
			if (one[i] < two[j]) {
				result[k++] = one[i++];
			} else { // one[i] >= two[j]
				result[k++] = two[j++];
			}
		}
		while (i < one.length) {
			result[k++] = one[i++];
		}
		while (j < two.length) {
			result[k++] = two[j++];
		}
		return result;
	}
}
