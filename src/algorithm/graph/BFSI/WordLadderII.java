package algorithm.graph.BFSI;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Arrays;

public class WordLadderII {
	static public class NeighborFinder {
		// wordIndex stores every word from input words list and its index
		private Map<String, Integer> wordIndex = new HashMap<>();
		// words points to input words list
		private List<String> words;

		public NeighborFinder(List<String> words) {
			for (int i = 0; i < words.size(); i++) {
				String word = words.get(i);
				wordIndex.put(word, i);
			}
			this.words = words;
		}

		public List<Integer> findNeighbors(int i) {
			List<Integer> neighbors = new ArrayList<>(16);
			String word = words.get(i);
			StringBuilder wordModifier = new StringBuilder(word);
			for (int j = 0; j < wordModifier.length(); j++) {
				char orig = word.charAt(j);
				for (char c = 'a'; c <= 'z'; c++) {
					if (c == orig) {
						continue;
					}
					wordModifier.setCharAt(j, c);
					int neighbor = wordIndex.getOrDefault(wordModifier.toString(), -1);
					if (neighbor != -1) {
						neighbors.add(neighbor);
					}
				}
				wordModifier.setCharAt(j, orig);
			}
			return neighbors;
		}
	}

	static class Tracer {
		private List<String> words;
		private List<List<Integer>> preds;

		public Tracer(List<String> words) {
			this.words = words;

			preds = new ArrayList<>(words.size());
			for (int i = 0; i < words.size(); i++) {
				preds.add(new ArrayList<>(16));
			}
		}

		public void addPredecessor(int x, int y) {
			preds.get(y).add(x);
		}

		public List<List<String>> findLadders(int beginIndex, int y) {
			List<List<String>> ladders = new ArrayList<>();
			List<String> trace = new ArrayList<>();
			trace.add(words.get(y));
			findLaddersHelper(beginIndex, y, trace, ladders);
			return ladders;
		}

		private void findLaddersHelper(int beginIndex, int y, List<String> trace, List<List<String>> ladders) {
			if (beginIndex == y) {
				List<String> ladder = new ArrayList<>(trace);
				Collections.reverse(ladder);
				ladders.add(ladder);
				return;
			}
			for (int x : preds.get(y)) {
				trace.add(words.get(x));
				findLaddersHelper(beginIndex, x, trace, ladders);
				trace.remove(trace.size() - 1);
			}
		}

	}

	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		int endIndex = wordList.indexOf(endWord);
		if (endIndex == -1) {
			return new ArrayList<>();
		}
		List<String> words;
		int beginIndex = wordList.indexOf(beginWord);
		// the begin word is not necessarily in the input wordList
		// in this case just put it into the list
		if (beginIndex == -1) {
			// the input wordList might not be appendable (e.g, the result of Arrays.asList)
			words = new ArrayList<String>(wordList);
			words.add(beginWord);
			beginIndex = words.size() - 1;
		} else {
			words = wordList;
		}

		NeighborFinder finder = new NeighborFinder(words);

		// queue stores all generated words which are not expanded yet
		Queue<Integer> queue = new ArrayDeque<>();
		// step[i] is the step number of words.get(i)
		int[] step = new int[words.size()];
		// the default step for ungenerated words are -1
		Arrays.fill(step, -1);

		queue.offer(beginIndex);
		step[beginIndex] = 0;
		// Tracer object helps to find wordladders
		Tracer tracer = new Tracer(words);

		while (!queue.isEmpty()) {
			int x = queue.poll();
			// x == endIndex -- find a valid ladder
			if (x == endIndex) {
				return tracer.findLadders(beginIndex, endIndex);
			}
			for (int y : finder.findNeighbors(x)) {
				// if a neighbor y is not generated before, x is a processor of y and step[y] =
				// step[x] + 1
				if (step[y] == -1) {
					queue.offer(y);
					step[y] = step[x] + 1;
				}
				// otherwise if step[y] = step[x] + 1, y is a predecessor of x
				// here we want to find all predecessors of x
				if (step[x] + 1 == step[y]) {
					tracer.addPredecessor(x, y);
				}
			}
		}
		return new ArrayList<>();
	}

	public static void main(String[] args) {
		WordLadderII solu = new WordLadderII();
		List<String> wordList = Arrays.asList(new String[] { "git", "hit", "hog", "hot", "got" });
		List<List<String>> result = solu.findLadders("git", "hot", wordList);
		for (List<String> trace : result) {
			for (String str : trace) {
				System.out.print(str + " ");
			}
			System.out.println();
		}
	}
}
