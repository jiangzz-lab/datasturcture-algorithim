package datastructure.array.oneD.kSum;

import java.util.Set;
import java.util.HashSet;

public class TwoSum {
	public boolean twoSum(int[] array, int target) {
		if (array == null || array.length == 0) {
			return false;
		}
		Set<Integer> scanned = new HashSet<>();
		for (int probe = 0; probe < array.length; probe++) {
			int frontier = array[probe];
			if (!scanned.isEmpty() && scanned.contains(target - frontier)) {
				return true;
			}
			scanned.add(frontier);
		}
		return false;
	}

	public static void main(String[] args) {
		TwoSum solu = new TwoSum();
		System.out.println(solu.twoSum(new int[] { 1, 3, 1, 2, 5 }, 6)); // true
	}
}
