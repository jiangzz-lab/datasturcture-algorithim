package algorithm.graph.BFSI;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.ArrayDeque;


public class WordLadderI {
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		Set<String> dict = getDict(wordList);
		Queue<String> frontiers = new ArrayDeque<>();
		Set<String> generated = new HashSet<>();
		if (!dict.contains(beginWord) || !dict.contains(endWord)) {
		return 0;
	}
	if (beginWord.equals(endWord)) {
		return 1;
	}
	frontiers.offer(beginWord);
	generated.add(beginWord);
	int len = 1;
	while (!frontiers.isEmpty()) {
		int size = frontiers.size();
		len++;
		while (size-- > 0) {
		String curr = frontiers.poll();
		List<String> neighbors = getNeighbors(curr, dict);
		for (String nei : neighbors) {
			if (nei.equals(endWord)) {
		return len;
	}
		if (generated.add(nei)) {
		frontiers.offer(nei);
	}
	}
	}
	}
	return 0;
	}

	List<String> getNeighbors(String curr, Set<String> dict) {
		List<String> result = new ArrayList<>();
		char[] array = curr.toCharArray();
		for (int i = 0; i < array.length; i++) {
			char old = array[i];
			for (int j = 0; j < 26; j++) {
				char ch = (char)('a' + j);
				if (old != ch) {
					array[i] = ch;
					String nei = new String(array);
					if (dict.contains(nei)) {
						result.add(nei);
					}
				}
			}
		array[i] = old;
		}
		return result;
	}
		
	
	Set<String> getDict(List<String> words) {
		Set<String> dict = new HashSet<>();
		for (String word : words) {
			dict.add(word);
		}
		return dict;
	}
	
	public static void main(String[] args) {
		WordLadderI solu = new WordLadderI();
		List<String> wordList = new ArrayList<>();
		wordList.add("git");
		wordList.add("hit");
		wordList.add("hog");
		wordList.add("hot");
		System.out.println(solu.ladderLength("git", "hot", wordList));
	}

}
