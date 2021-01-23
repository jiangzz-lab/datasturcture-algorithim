package javabasic;

import java.util.Deque;
import java.util.ArrayDeque;

public class MyQueue {
	Deque<Integer> s1 = new ArrayDeque<>();
	Deque<Integer> s2 = new ArrayDeque<>();

	public Integer poll() {
		if (isEmpty()) {
			return null;
		}
		if (s2.isEmpty()) {
			while (!s1.isEmpty()) {
				s2.offerFirst(s1.pollFirst());
			}
		}
		return s2.pollFirst();
	}

	public Integer peek() {
		if (isEmpty()) {
			return null;
		}
		if (s2.isEmpty()) {
			while (!s1.isEmpty()) {
				s2.offerFirst(s1.pollFirst());
			}
		}
		return s2.peekFirst();
	}

	public void offer(int element) {
		s1.offerFirst(element);
	}

	public int size() {
		return s1.size() + s2.size();
	}

	public boolean isEmpty() {
		return s1.isEmpty() && s2.isEmpty();
	}

}
