package algorithm.dp;

import java.util.Set;
import java.util.HashSet;

public class DictionaryWordI {
	public boolean canBreak(String input, String[] dict) {
		Set<String> set = getSet(dict);
		boolean[] M = new boolean[input.length() + 1];
		M[0] = true;
		for (int i = 1; i < M.length; i++) {
			for (int j = 0; j < i; j++) {
				if (M[j] && set.contains(input.substring(j, i))) {
					M[i] = true;
					break;
				}
			}
		}
		return M[input.length()];
	}

	Set<String> getSet(String[] dict) {
		Set<String> set = new HashSet<>();
		for (String word : dict) {
			set.add(word);
		}
		return set;
	}
	
	public static void main(String[] args) {
		DictionaryWordI solu = new DictionaryWordI();
		System.out.println(solu.canBreak("robcatd", new String[] {"rob","cat","d"}));
	}
}
