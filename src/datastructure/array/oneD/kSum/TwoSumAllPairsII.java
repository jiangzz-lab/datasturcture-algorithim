package datastructure.array.oneD.kSum;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class TwoSumAllPairsII {
	public List<List<Integer>> twoSum(int[] array, int target) {
		List<List<Integer>> res = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();
		for (int probe = 0; probe < array.length; probe++) {
			int frontier = array[probe];
			int partner = target - frontier;
			if (map.containsKey(partner)) {
				Integer value = map.get(partner);
				if (value == null) {
					res.add(Arrays.asList(new Integer[] { frontier, partner }));
					map.put(frontier, partner);
					map.put(partner, frontier);
				}
			} else {
				map.put(frontier, null);
			}
		}
		return res;
	}

	public static void main(String[] args) {
		TwoSumAllPairsII solu = new TwoSumAllPairsII();
		int[] array = new int[] { 1, 1, 2, 1, 3, 0, 4, -1 };

		List<List<Integer>> res = solu.twoSum(array, 3);
		for (List<Integer> pair : res) {
			System.out.println(pair.get(0) + " " + pair.get(1));
		}
		/*
		  2 1 
		  0 3
		 -1 4
		 */
	}
}
