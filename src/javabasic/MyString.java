package javabasic;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Arrays;
import java.util.Deque;
import java.util.ArrayDeque;

public class MyString {
	public List<String> subSets(String set) {
		StringBuilder solutionPrefix = new StringBuilder();
		List<String> result = new ArrayList<>();
		findSubSets(set, 0, solutionPrefix, result);
		return result;
	}

	public void findSubSets(String set, int index, StringBuilder solutionPrefix, List<String> result) {
		// base case: index = set.length()
		if (index == set.length()) {
			result.add(solutionPrefix.toString());
			return;
		}
		// case1: append set[index]
		solutionPrefix.append(set.charAt(index));
		findSubSets(set, index + 1, solutionPrefix, result);
		// recover the initial state of root node
		solutionPrefix.deleteCharAt(solutionPrefix.length() - 1);
		findSubSets(set, index + 1, solutionPrefix, result);
		// case2: not append set[index]
	}

	public List<String> validParentheses(int n) {
		List<String> result = new ArrayList<>();
		StringBuilder solutionPrefix = new StringBuilder();
		validParentheses(n, 0, 0, solutionPrefix, result);
		return result;
	}

	public void validParentheses(int n, int left, int right, StringBuilder solutionPrefix, List<String> result) {
		// base case: n == left + right
		if (2 * n == left + right) {
			result.add(solutionPrefix.toString());
			return;
		}
		// add “(” if left < n
		if (left < n) {
			solutionPrefix.append("(");
			validParentheses(n, left + 1, right, solutionPrefix, result);
			solutionPrefix.deleteCharAt(solutionPrefix.length() - 1);
		}

		if (left > right) {
			solutionPrefix.append(")");
			validParentheses(n, left, right + 1, solutionPrefix, result);
			solutionPrefix.deleteCharAt(solutionPrefix.length() - 1);
		}
	}

	public List<String> permutations(String input) {
		List<String> result = new ArrayList<>();
		char[] chars = input.toCharArray();
		findP(chars, 0, result);
		return result;
	}

	public void findP(char[] chars, int index, List<String> result) {
		// base case
		if (index == chars.length) {
			result.add(new String(chars));
			return;
		}
		Set<Character> set = new HashSet<>();
		for (int i = index; i < chars.length; i++) {
			if (set.contains(chars[i])) {
				continue;
			}
			set.add(chars[i]);
			swap(chars, index, i);
			findP(chars, index + 1, result);
			swap(chars, index, i);
		}
	}

	public void swap(char[] chars, int i, int j) {
		char temp = chars[i];
		chars[i] = chars[j];
		chars[j] = temp;
	}

	public String swap(String input, int i, int j) {
		char[] chars = input.toCharArray();
		char temp = chars[i];
		chars[i] = chars[j];
		chars[j] = temp;
		String result = new String(chars);
		return result;
	}

	// input nor null, k >= 1, k could be larger than the number of of distinct
	// words
	public String[] topKFrequent(String[] combo, int k) {
		// corner case
		if (combo.length == 0) {
			return new String[0];
		}
		// use a HashMap<String, Integer>
		Map<String, Integer> freqMap = getFreqMap(combo);

		// use a minHeap
		PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(k,
				new Comparator<Map.Entry<String, Integer>>() {
					@Override
					public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
						return e1.getValue().compareTo(e2.getValue());
					}
				});
		for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
			if (minHeap.size() < k) {
				minHeap.offer(entry);
			} else if (entry.getValue() > minHeap.peek().getValue()) {
				minHeap.poll();
				minHeap.offer(entry);
			}
		}
		// build result array
		return freqArray(minHeap);
	}

	private Map<String, Integer> getFreqMap(String[] combo) {
		Map<String, Integer> freqMap = new HashMap<>();
		for (String word : combo) {
			Integer freq = freqMap.get(word);
			if (freq != null) {
				freqMap.put(word, freq + 1);
			} else {
				freqMap.put(word, 1);
			}
		}
		return freqMap;
	}

	private String[] freqArray(PriorityQueue<Map.Entry<String, Integer>> minHeap) {
		String[] result = new String[minHeap.size()];
		for (int i = result.length - 1; i >= 0; i--) {
			result[i] = minHeap.poll().getKey();
		}
		return result;
	}

	public String remove(String input, String t) {
		if (t.length() == 0) {
			return input;
		}
		// use two pointer fast, slow
		// [0, fast) i is the processed range
		// [0, slow) is the part to keep
		Set<Character> set = new HashSet<>();
		for (int i = 0; i < t.length(); i++) {
			set.add(t.charAt(i));
		}

		StringBuilder output = new StringBuilder(input);
		int slow = 0;
		int fast = 0;
		for (; fast < output.length(); fast++) {
			if (!set.contains(output.charAt(fast))) {
				output.setCharAt(slow++, output.charAt(fast));
			}
		}
		return output.delete(slow, output.length()).toString();
	}

	public String removeSpaces(String input) {
		// check empty
		if (input.length() == 0) {
			return input;
		}
		StringBuilder output = new StringBuilder(input);
		int slow = 0;
		for (int fast = 0; fast < output.length(); fast++) {
			if (output.charAt(fast) != ' ' || fast > 0 && output.charAt(fast - 1) != ' ') {
				output.setCharAt(slow++, output.charAt(fast));
			}
		}
		if (slow > 0 && output.charAt(slow - 1) == ' ') {
			slow--;
		}
		return output.substring(0, slow);
	}

	public String deDup(String input) {
		// check null, empty
		if (input == null || input.length() <= 0) {
			return input;
		}
		StringBuilder output = new StringBuilder(input);
		int slow = 1;
		for (int fast = 1; fast < output.length(); fast++) {
			if (output.charAt(fast) != output.charAt(fast - 1)) {
				output.setCharAt(slow++, output.charAt(fast));
			}
		}
		return output.substring(0, slow);
	}

	public String deDupRep(String input) {
		// check null, empty, single character
		if (input == null || input.length() <= 1) {
			return input;
		}
		StringBuilder output = new StringBuilder(input);
		int slow = -1;
		int fast = 0;
		while (fast < output.length()) {
			if (slow == -1 || output.charAt(slow) != output.charAt(fast)) {
				output.setCharAt(++slow, output.charAt(fast++));
			} else {
				while (fast < output.length() && output.charAt(slow) == output.charAt(fast)) {
					fast++;
				}
				slow--;
			}
		}
		return output.substring(0, slow + 1);
	}

	// large, small not null
	public int strstr(String large, String small) {
		// check empty
		if (small.length() == 0) {
			return 0;
		}
		for (int i = 0; i <= large.length() - small.length(); i++) {
			if (equals(large, i, small)) {
				return i;
			}
		}
		return -1;
	}

	public boolean equals(String large, int start, String small) {
		for (int i = 0; i < small.length(); i++) {
			if (large.charAt(i + start) != small.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	// input not null
	public String reverse(String input) {
		// check empty, one char
		if (input.length() <= 1) {
			return input;
		}
		// convert input to char[]
		char[] chars = input.toCharArray();
		// use left, right pointers
		int left = 0;
		int right = chars.length - 1;
		while (left <= right) {
			char temp = chars[left];
			chars[left] = chars[right];
			chars[right] = temp;
			left++;
			right--;
		}
		return new String(chars);
	}

	public String reverseWords(String input) {
		// check null, empty, single char
		if (input == null || input.length() <= 1) {
			return input;
		}
		char[] chars = input.toCharArray();
		// step1: reverse whole array
		reverse(chars, 0, chars.length - 1);
		// step2: reverse words separately
		int slow = 0;
		int fast = 0;
		while (fast < chars.length) {
			// find the next space
			while (fast < chars.length && chars[fast] != ' ') {
				fast++;
			}
			// reverse [slow, fast - 1]
			reverse(chars, slow, fast - 1);
			// make slow, fast at the beginning of the next word;
			slow = ++fast;
		}
		return new String(chars);
	}

	private void reverse(char[] chars, int left, int right) {
		while (left <= right) {
			char temp = chars[left];
			chars[left] = chars[right];
			chars[right] = temp;
			left++;
			right--;
		}
	}

	public String rightShift(String input, int n) {
		// check input length <= 1
		if (input.length() <= 1 || n == 0) {
			return input;
		}
		char[] chars = input.toCharArray();
		// step1: reverse whole array
		reverse(chars, 0, chars.length - 1);
		// step1: reverse [0, n % length - 1], [n % length, length - 1]
		reverse(chars, 0, n % chars.length - 1);
		reverse(chars, n % chars.length, chars.length - 1);
		return new String(chars);
	}

	public String replace(String input, String source, String target) {
		// case1: source.length >= target.length
		if (source.length() >= target.length()) {
			return replaceToShorterOrEqual(input, source, target);
		} else { // case2: source.length < target.length
			return replaceToLonger(input, source, target);
		}
	}

	public String replaceToShorterOrEqual(String input, String source, String target) {
		// step1: find all sources in input and store the indexes of their starting
		// characters in a set
		Set<Integer> sourceStartings = findSubStr(input, source);
		if (sourceStartings.size() == 0) {
			return input;
		}
		// step2: replace sources with target one by one using pointers slow, fast
		// convert input to char[]
		char[] chars = input.toCharArray();
		int slow = 0;
		int fast = 0;
		while (fast < chars.length) {
			if (!sourceStartings.contains(fast)) {
				chars[slow++] = chars[fast++];
			} else {
				for (int i = 0; i < target.length(); i++) {
					chars[slow++] = target.charAt(i);
				}
				fast += source.length();
			}
		}
		return new String(chars, 0, slow);
	}

	public String replaceToLonger(String input, String source, String target) {
		// find all source in input
		Set<Integer> sourceStartings = findSubStr(input, source);
		if (sourceStartings.size() == 0) {
			return input;
		}
		// convert input to char[] and resize it
		int extra = (target.length() - source.length()) * sourceStartings.size();
		int originalLength = input.length();
		char[] chars = Arrays.copyOf(input.toCharArray(), originalLength + extra);
		int slow = chars.length - 1;
		int fast = originalLength - 1;
		while (fast >= 0) {
			if (!sourceStartings.contains(fast + 1 - source.length())) {
				chars[slow--] = chars[fast--];
			} else {
				for (int i = target.length() - 1; i >= 0; i--) {
					chars[slow--] = target.charAt(i);
				}
				fast -= source.length();
			}
		}
		return new String(chars);
	}

	// input, sub not null
	public Set<Integer> findSubStr(String input, String sub) {
		Set<Integer> startings = new HashSet<>();
		if (sub.length() == 0 || input.length() < sub.length()) {
			return startings;
		}
		for (int i = 0; i <= input.length() - sub.length(); i++) {
			int j = 0;
			while (j < sub.length() && input.charAt(i + j) == sub.charAt(j)) {
				j++;
			}
			if (j == sub.length()) {
				startings.add(i);
			}
		}
		return startings;
	}

	public String compress(String input) {
		if (input == null || input.isEmpty()) {
			return input;
		}
		char[] array = input.toCharArray();
		return encode(array);
	}

	private String encode(char[] input) {
		// Step1: deal with the cases where the adjacent occurance of the letters >= 2
		// [0, slow) are processed chars to keep
		// [0, fast) are processed chars
		int slow = 0;
		int fast = 0;
		// newLength record the length of output string
		int newLength = 0;
		while (fast < input.length) {
			// record the beginning of every substring with repeated chars
			int begin = fast;
			while (fast < input.length && input[fast] == input[begin]) {
				fast++;
			}
			// copy one char to input[slow] and move slow
			input[slow++] = input[begin];
			// now fast - begin is the length of this substring, >= 1
			// if fast - begin == 1, do the encode later, in the meantime record the length
			// it takes in the new string
			if (fast - begin == 1) {
				newLength += 2;
			} else {
				// copyDigits copies the count of current char into result string and return the
				// length of the digit part
				int len = copyDigits(input, slow, fast - begin);
				// slow should move to slow + len after the copy
				slow += len;
				newLength += len + 1;
			}
		}
		// step 2: deal with the cases where the adjacent occurance of the letters == 1
		char[] result = new char[newLength];
		// copy from right to left: (fast, originalSlow - 1] are the processed chars;
		// (slow, newLength - 1] are chars to be kept in result string
		fast = slow - 1;
		slow = newLength - 1;
		while (fast >= 0) {
			// if find a digit, copy the digit and all digit before it to result
			if (Character.isDigit(input[fast])) {
				while (fast < input.length && Character.isDigit(input[fast])) {
					result[slow--] = input[fast--];
				}
			} else { // if not a digit, this is a char to be encoded
				result[slow--] = '1';
			}
			// now input[fast] must be a char and its count is already encoded in the
			// result, so we simply copy the char to the result
			result[slow--] = input[fast--];
		}
		return new String(result);
	}

	// copy “count” as digits into “input”, starting at “index”
	// return the length of the copied digit substring
	private int copyDigits(char[] input, int index, int count) {
		// count the number of digits, namely the length of this digit substring
		int len = 0;
		for (int i = count; i > 0; i /= 10) {
			index++;
			len++;
		}
		// copy
		for (int i = count; i > 0; i /= 10) {

			int digit = i % 10;
			input[--index] = (char) ('0' + digit);
		}
		return len;
	}

	public String deCompress(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}
		char[] array = input.toCharArray();
		return deCompLong(array, deCompShort(array));
	}

	// deComp digits <= 2 and return the length of processed part
	// array.length >= 2
	public int deCompShort(char[] input) {
		int end = 0;
		// copy from left to right
		for (int i = 0; i < input.length; i += 2) {
			int digit = getDigit(input[i + 1]);
			if (digit >= 0 && digit <= 2) {
				// copy digit of input[i]
				for (int j = 0; j < digit; j++) {
					input[end++] = input[i];
				}
			} else { // for other case, copy the original chars
				input[end++] = input[i];
				input[end++] = input[i + 1];
			}
		}
		return end;
	}

	public String deCompLong(char[] input, int length) {
		// find the new length of the decompressed string
		int newLength = length;
		for (int i = 0; i < length; i++) {
			int digit = getDigit(input[i]);
			if (digit > 2 && digit <= 9) {
				newLength += digit - 2;
			}
		}
		// create array for result
		char[] result = new char[newLength];
		// scan from right to left
		int end = newLength - 1;
		for (int i = length - 1; i >= 0; i--) {
			int digit = getDigit(input[i]);
			if (digit > 2 && digit <= 9) {
				for (int j = 0; j < digit - 1; j++) {
					result[end--] = input[i - 1];
				}
			} else {
				result[end--] = input[i];
			}
		}
		return new String(result);
	}

	public int getDigit(char input) {
		return input - '0';
	}

	public int longestSub(String input) {
		// check empty
		if (input.length() == 0) {
			return 0;
		}
		// [slow, fast] contains a substring without repeating characters in processed
		// range
		int slow = 0;
		// use a HashSet to store the characters within [slow, fast]
		int longest = 0;
		Set<Character> set = new HashSet<Character>();
		for (int fast = 0; fast < input.length(); fast++) {
			while (fast < input.length() && !set.contains(input.charAt(fast))) {
				set.add(input.charAt(fast));
				fast++;
			}
			// so far no repeating char is found in [slow, fast - 1]
			// update longest with fast - slow if necessary
			longest = fast - slow > longest ? fast - slow : longest;
			// find the char == input.charAt(fast) and move slow to its next
			if (fast < input.length()) {
				while (slow < input.length() && input.charAt(slow) != input.charAt(fast)) {
					slow++;
					set.remove(input.charAt(slow));
				}
				slow++;
			}
		}
		return longest;
	}

	public int longest(String input) {
		int longest = 0;
		int slow = 0;
		int fast = 0;
		Set<Character> set = new HashSet<Character>();
		while (fast < input.length()) {
			if (!set.contains(input.charAt(fast))) {
				set.add(input.charAt(fast));
				longest = Math.max(longest, ++fast - slow);
			} else {
				set.remove(input.charAt(slow++));
			}
		}
		return longest;
	}

	public List<Integer> allAnagrams(String sh, String lo) {
	    // Write your solution here
	    List<Integer> result = new ArrayList<>();
		if (lo.length() < sh.length()) {
			return result;
	}
	int slow = 0;
	int fast = 0;
		Map<Character, Integer> countMap = new HashMap<>();
		// store sh info into countMap
		for (int i = 0; i < sh.length(); i++) {
			char ch = sh.charAt(i);
			Integer count = countMap.get(ch);
			if (count == null) {
				countMap.put(ch, 1);
	} else {
		countMap.put(ch, count + 1);
	}
	}
	// check [0, sh.length() - 1] in lo
	while (fast <= sh.length() - 1) {
		char ch = lo.charAt(fast);
		Integer count = countMap.get(ch);
		if (count != null) {
			countMap.put(ch, count - 1);
	}
	fast++;
	}
	
	while (fast < lo.length()) {
		if (allValueZero(countMap)) {
			result.add(slow);
	}
	if (fast + 1 < lo.length()) {
		char ch = lo.charAt(++fast);
		Integer count = countMap.get(ch);
		if (count != null) {
		countMap.put(ch, count - 1);
	}
	ch = lo.charAt(++slow);
	count = countMap.get(ch);
	if (count != null) {
		countMap.put(ch, count  + 1);
	}
	}
	}
	return result;
	  }

	public boolean allValueZero(Map<Character, Integer> map) {
		for (Map.Entry<Character, Integer> entry: map.entrySet()) {
			if (entry.getValue() != 0) {
		return false;
	}
	}
	return true;
	}

	public boolean match(String input, String pattern) {
		// sanity check
		if (input.length() == 0 && pattern.length() == 0) {
			return true;
	}
	if (input.length() < pattern.length()) {
		return false;
	}
	return match(input, pattern, 0, 0);
	}


	public boolean match(String input, String pattern, int inputStart, int patternStart) {
		// base case
		if (patternStart == pattern.length() && inputStart == input.length()) {
			return true; 
		} else if (patternStart >= pattern.length() || inputStart >= input.length()) {
			return false;
		}
	// case1: pattern start is digit
	if (Character.isDigit(pattern.charAt(patternStart))) {
		// find the end of this digit section
		int end = patternStart;
		while (end < pattern.length() && Character.isDigit(pattern.charAt(end))) {
			end++;
	}
	// convert the digits char into a number
	int num = getNum(pattern, patternStart, end);
	if (input.length() - inputStart < num) {
		return false;
	} else {
		return match(input, pattern, inputStart + num, end);
	}
	} else { // case2: pattern start is char
		if (pattern.charAt(patternStart) != input.charAt(inputStart)) {
			return false;
	} else {
		return match(input, pattern, inputStart + 1, patternStart + 1);
	}
	}
	}
	
	public int getNum(String pattern, int start, int end) {
		int num = 0;
		for (int i = start; i < end; i++) {
		num = (pattern.charAt(i) - '0') + num * 10; 
	}
	return num;
	}

	
	public boolean allUnique(String word) {
		if (word.length() <= 1) {
			return true;
	}
	int[] found = new int[8];
	for (int i = 0; i < word.length(); i++) {
		char ch = word.charAt(i);
		int index = ch / 32;
		int bit = ch % 32;
		if (((found[index] >> bit) & 1) == 1) {
			return false;
	} else {
		found[index] = found[index] | (1 << bit);
	}
	} 
	return true;
	}

	public boolean canBreak(String input, String[] dict) {
		boolean[] M = new boolean[input.length()];
		// base case
		M[0] = isFound(input.substring(0, 1), dict);
		// i > 0
		for (int i = 1; i < M.length; i++) {
	if (isFound(input.substring(0, i + 1), dict)) {
	M[i] = true;
} else {
	for (int j = 0; j < i; j++) {
	if (M[j] == true && isFound(input.substring(j + 1, i + 1), dict)) {
	M[i] = true;
	break;
}
}
}
}
return M[M.length - 1];
}

	public boolean isFound(String input, String[] dict) {
		for (String str : dict) {
		if (str.equals(input)) {
		return true;
	}
	}
	return false;
	}
	
	public int editDistance(String one, String two) {
		int[][] M = new int[one.length() + 1][two.length() + 1];
		for (int i = 0; i <= one.length(); i++) {
		for (int j = 0; j <= two.length(); j++) {
		if (i == 0 && j == 0) { // base case
			M[i][j] = 0;
	} else if (i == 0) {
		M[i][j] = j;
	} else if (j == 0) {
		M[i][j] = i;
	} else { // i > 0, j > 0
		int replace = M[i - 1][j - 1] + (one.charAt(i - 1) == two.charAt(j - 1) ? 0 : 1);
		int delete = M[i - 1][j] + 1;
		int insert = M[i][j - 1] + 1;
		M[i][j] = Math.min(Math.min(replace, delete), insert);
	}
	}
	}
	return M[one.length()][two.length()];
	}
	
	public List<String> subSetsII(String input) {
		List<String> result = new ArrayList<>();
		if (input == null) {
		return result;
	}
	StringBuilder solu = new StringBuilder();
	char[] array = input.toCharArray();
	Arrays.sort(array);
	dfs(array, 0, solu, result);
	return result;
	}

	public void dfs(char[] array, int index, StringBuilder solu, List<String> result) {
		// base case
		if (index == array.length) {
		result.add(solu.toString());
		return;
	}
	// add char at index
	solu.append(array[index]);
	dfs(array, index + 1, solu, result);
	solu.deleteCharAt(solu.length() - 1);

	// skipping the following levels with input.charAt(i) = input.charAt(index) ; effectively take several branches that don’t add this char
	while (index < array.length - 1 && array[index] == array[index  + 1]) {
		index++;
	}
	// do not add
	dfs(array, index + 1, solu, result);
	}
	
	public List<String> subSetsII(String set, int k) {
		List<String> result = new ArrayList<>();
		// check null, set length < k etc
		if (set == null || k < 0 || set.length() < k) {
		return result;
	}
	char[] array = set.toCharArray();
	Arrays.sort(array);
	StringBuilder solu = new StringBuilder();
	dfsSubSets(array, k, 0, solu, result);
	return result;
	}
	
	public void dfsSubSets(char[] array, int k, int index, StringBuilder solu, List<String> result) {
		// base case
		if (solu.length() == k) {
		result.add(solu.toString());
		return;
	}
	if (index == array.length) {
		return;
	}
	// add array[index]
	solu.append(array[index]);
	dfsSubSets(array, k, index + 1, solu, result);
	solu.deleteCharAt(solu.length() - 1);

	// avoid adding any array[index] after the following branch
	while (index < array.length - 1 && array[index + 1] == array[index]) {
	index++;
	}
	dfsSubSets(array, k, index + 1, solu, result);
	}
	
	public List<String> validParentheses(int l, int m, int n) {
		List<String> result = new ArrayList<>();
		StringBuilder solu = new StringBuilder();
		Deque<Character> stack = new ArrayDeque<>();
		Map<Character, Integer> left = getCountMap(l, m, n);
		dfsParatheses(left, stack, solu, result);
		return result;
	}

	public void dfsParatheses(Map<Character, Integer> left, Deque<Character> stack, StringBuilder solu, List<String> result) {
		// base case
		if (allZero(left) && stack.isEmpty()) {
			result.add(solu.toString());
			return;
	}
	// put left para
	for (Character ch : left.keySet()) {
		Integer count = left.get(ch);
		if (count > 0) {
		stack.offerFirst(ch);
		left.put(ch, count - 1);
		solu.append(ch);
		dfsParatheses(left, stack, solu, result);
		solu.deleteCharAt(solu.length() - 1);
		left.put(ch, count);
		stack.pollFirst();
	}
	}
	// put right para
	if (!stack.isEmpty()) {
		Character ch = stack.pollFirst();
		solu.append(getMatch(ch));
		dfsParatheses(left, stack, solu, result);
		solu.deleteCharAt(solu.length() - 1);
		stack.offerFirst(ch);
	}
	}
	
	public Map<Character, Integer> getCountMap(int l, int m, int n) {
		Map<Character, Integer> map = new HashMap<>();
		map.put('(', l);
		map.put('[', m);
		map.put('{', n);
		return map;
	}

	public boolean allZero(Map<Character, Integer> map) {
		for (Integer count : map.values()) {
		if (count != 0) {
		return false;
	}
	}
	return true;
	}
	
	Character getMatch(Character ch) {
		if (ch.equals('(')) {
		return ')';
	} else if (ch.equals('[')) {
		return ']';
	} else if (ch.equals('{')) {
		return '}';
	} else {
		return null;
	}
	}
}
