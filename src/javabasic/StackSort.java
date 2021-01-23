package javabasic;

import java.util.LinkedList;

public class StackSort {
	public static void sort(LinkedList<Integer> s1) {
		// stack s2 as buffer
		LinkedList<Integer> s2 = new LinkedList<Integer>();
		// check s1.size() <= 1
		if (s1.size() <= 1) {
			return;
		}
		while (!s1.isEmpty()) {
			// step1: move elements from s1 to s2 and record the min and its count
			int min = Integer.MAX_VALUE;
			int count = 0;
			while (!s1.isEmpty()) {
				// pop one element out of s1
				int value = s1.pollFirst();
				// update min, count when necessary
				if (value == min) {
					count++;
				} else if (value < min) {
					min = value;
					count = 1;
				}
				// push this element into s2
				s2.offerFirst(value);
			}
			// step2: pop elements >= min out of s2 and push the ones > min back to s1
			while (!s2.isEmpty() && s2.peekFirst() >= min) {
				int value = s2.pollFirst();
				// push it back to s1 if value > min
				if (value > min) {
					s1.offerFirst(value);
				}

			}
			// step3: push elements with min value to s2, with the corresponding count
			while (count > 0) {
				s2.offerFirst(min);
				count--;
			}
		}
			// now all elements are in s2 and sorted in descending order
			// transfer them back to s1 one by one
			while (!s2.isEmpty()) {
				s1.offerFirst(s2.pollFirst());
			}
	}
}
