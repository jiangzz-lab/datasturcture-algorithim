package algorithm.string;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class AllAnagrams {
	public List<Integer> allAnagrams(String sh, String lo) {
		// check sh longer than lo
		List<Integer> result = new ArrayList<>();
		if (sh.length() > lo.length()) {
			return result;
		}
		Map<Character, Integer> countMap = getCountMap(sh);
		int matches = 0;
		for (int right = 0; right <= lo.length(); right++) {
			int left = right - sh.length();
			if (matches == countMap.size()) {
				result.add(left);
			}
			if (right < lo.length()) { // update the right bound for next round if right does not reach the end
				char ch = lo.charAt(right);
				Integer count = countMap.get(ch);
				if (count != null) { // update the countMap accordingly
					countMap.put(ch, count - 1);
					if (count == 1) { // find a new match
						matches++;
					}
				}
			}
			if (left >= 0) { // update the countMap for next round if there is a char at current left bound
				char ch = lo.charAt(left);
				Integer count = countMap.get(ch);
				if (count != null) {
					countMap.put(ch, count + 1);
					if (count == 0) { // to lose a match
						matches--;
					}
				}
			}
		}
		return result;
	}

	Map<Character, Integer> getCountMap(String str) {
		Map<Character, Integer> countMap = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			Integer count = countMap.get(ch);
			if (count == null) {
				countMap.put(ch, 1);
			} else {
				countMap.put(ch, count + 1);
			}
		}
		return countMap;
	}
	
	public static void main(String[] args) {
		AllAnagrams solu = new AllAnagrams();
		List<Integer> result = solu.allAnagrams("abc", "abccba");
		for (Integer index : result) {
			System.out.println(index);
		}
	}

}
