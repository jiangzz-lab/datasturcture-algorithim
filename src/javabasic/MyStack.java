package javabasic;

import java.util.LinkedList;
import java.util.Deque;

public class MyStack {
	public static void sort(LinkedList<Integer> s1) {
		if (s1 == null || s1.size() <= 1) {
			return;
		}
		LinkedList<Integer> s2 = new LinkedList<Integer>();
		sort(s1, s2);
	}

	private static void sort(Deque<Integer> input, Deque<Integer> buffer) {
		// Method 1
		// input : unsorted elements
		// buffer: (top part) buffer, (bottom part) sorted elements
		// step1: sort in ascending order and store result in buffer
		while (!input.isEmpty()) {
			int curMin = Integer.MAX_VALUE;
			int count = 0;
			// move elements from input to buffer and record the curMin and its count
			while (!input.isEmpty()) {
				int cur = input.pollFirst();
				if (cur < curMin) {
					curMin = cur;
					count = 1;
				} else if (cur == curMin) {
					count++;
				}
				buffer.offerFirst(cur);
			}
// pop elements out of buffer, if the top is larger than curMin, push it back into input
			while (!buffer.isEmpty() && buffer.peekFirst() >= curMin) {
				int tmp = buffer.pollFirst();
				if (tmp != curMin) {
					input.offerFirst(tmp);
				}
			}
// push curMin into buffer
			while (count-- > 0) {
				buffer.offerFirst(curMin);
			}
		}
// now elements in buffer is in descending order from top to bottom
// step2: move all elements back to input. Then they will be in ascending order from top to bottom
		while (!buffer.isEmpty()) {
			input.offerFirst(buffer.pollFirst());
		}
	}

}
