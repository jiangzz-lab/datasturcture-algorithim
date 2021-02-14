package datastructure.array.oneD.dedup;

import java.util.Arrays;
import datastructure.array.oneD.Basics;



public class DeDupIV {
	public int[] dedup(int[] array) {
		if (array == null || array.length <= 1) {
		return array;
	}
	int res = 1;
	int probe = 1;
	while (probe < array.length) {
		int frontier = array[probe];
		if (res == 0 || frontier != array[res - 1]) {
		array[res++] = frontier;
	} else {
		while (probe < array.length && array[probe] == frontier) {
		probe++;
		
	}
	res--;
	}
	} 
	return Arrays.copyOf(array, res);
	}
	
	public static void main(String[] args) {
		DeDupIV solu = new DeDupIV();
		Basics helper = new Basics();
		
		helper.print1DArray(solu.dedup(new int[] {1, 2, 3, 3, 3, 2, 2})); // 1
	}

}
