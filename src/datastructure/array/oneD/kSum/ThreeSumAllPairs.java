package datastructure.array.oneD.kSum;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;
import java.util.Arrays;

public class ThreeSumAllPairs {
	public List<List<Integer>> allTriples(int[] array, int target) {
		Set<List<Integer>> resSet = new HashSet<>();
		List<List<Integer>> pairs = new ArrayList<>();
		for (int probe = 2; probe < array.length; probe++) {
			int frontier = array[probe];
			pairs = twoSum(array, probe, target - frontier);
			for (List<Integer> pair : pairs) {
				pair.add(frontier);
				pair.sort(Comparator.naturalOrder());
				resSet.add(pair);
			}
		}
		return new ArrayList<List<Integer>>(resSet);
	}

	List<List<Integer>> getListOfList(Set<Set<Integer>> sets) {
		List<List<Integer>> list = new ArrayList<>();
		for (Set<Integer> set : sets) {
			list.add(new ArrayList<Integer>(set));
		}
		return list;
	}

	List<List<Integer>> twoSum(int[] array, int rightBound, int target) {
		List<List<Integer>> res = new ArrayList<>();
		Map<Integer, Integer> pairs = new HashMap<>();
		for (int probe = 0; probe < rightBound; probe++) {
			int frontier = array[probe];
			int partner = target - frontier;
			if (pairs.containsKey(partner)) {
				Integer value = pairs.get(partner);
				if (value == null) {
					pairs.put(partner, frontier);
					pairs.put(frontier, partner);
					List<Integer> newPair = new ArrayList<Integer>();
					newPair.add(frontier);
					newPair.add(partner);
					res.add(newPair);
				} 
			} else {
				pairs.put(frontier, null);
			}
		}
		return res;
	}

	public static void main(String[] args) {
		ThreeSumAllPairs solu = new ThreeSumAllPairs();
		int[] array = new int[] { 1, 2, 2, 3, 2, 4 };

		List<List<Integer>> res = solu.allTriples(array, 8);
		for (List<Integer> triple : res) {
			System.out.println(triple.get(0) + " " + triple.get(1) + " " + triple.get(2));
		}
		/*
		   2 3 3
		   2 2 4
		 */
	}

}
