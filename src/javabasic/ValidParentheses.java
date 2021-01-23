package javabasic;

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;

public class ValidParentheses {
	private static final char[] PS = new char[] { '(', ')', '<', '>', '{', '}' };
	
	public List<String> validParentheses(int l, int m, int n) {
		int[] remain = new int[] { l, l, m, m, n, n};
		int targetLen = 2 * l + 2 * m + 2 * n;
		StringBuilder solu = new StringBuilder();
		Deque<Character> stack = new ArrayDeque<>();
		List<String> result = new ArrayList<>();
		helper(solu, stack, remain, targetLen, result);
		return result;
	}
	
	private void helper(StringBuilder solu, Deque<Character> stack, int[] remain, int targetLen, List<String> result) {
		if (solu.length() == targetLen) {
		result.add(solu.toString());
		return;
	}
	for (int i = 0; i < remain.length; i++) {
		if (i % 2 == 0) {
		if (remain[i] > 0 && (stack.isEmpty() || PS[i] <= stack.peekFirst())) {
		solu.append(PS[i]);
		stack.offerFirst(PS[i]);
		remain[i]--;
		helper(solu, stack, remain, targetLen, result);
		solu.deleteCharAt(solu.length() - 1);
		stack.pollFirst();
		remain[i]++;
	}
	} else {
		if (!stack.isEmpty() && stack.peekFirst() == PS[i - 1]) {
		solu.append(PS[i]);
		stack.pollFirst();
		remain[i]--;
		helper(solu, stack, remain, targetLen, result);
		solu.deleteCharAt(solu.length() - 1);
		stack.offerFirst(PS[i - 1]);
		remain[i]++;
	}
	}
	}
	}

}
