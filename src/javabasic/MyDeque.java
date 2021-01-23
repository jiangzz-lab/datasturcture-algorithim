package javabasic;

import java.util.Deque;
import java.util.ArrayDeque;

public class MyDeque {
	// use 3 stacks
	private Deque<Integer> front = new ArrayDeque<>();
	private Deque<Integer> back = new ArrayDeque<>();
	private Deque<Integer> buffer = new ArrayDeque<>();

	public void offerFirst(int element) {
		front.offerFirst(element);
	}

	public void offerLast(int element) {
		back.offerFirst(element);
	}

	public Integer pollFirst() {
		moveToFront();
		return front.isEmpty() ? null : front.pollFirst();
	}

	public Integer pollLast() {
		moveToBack();
		return back.isEmpty() ? null : back.pollFirst();
	}

	public Integer peekFirst() {
		moveToFront();
		return front.isEmpty() ? null : front.peekFirst();
	}

	public Integer peekLast() {
		moveToBack();
		return back.isEmpty() ? null : back.peekFirst();
	}

	public int size() {
		return front.size() + back.size();
	}

	public boolean isEmpty() {
		return front.isEmpty() && back.isEmpty();
	}

	private void moveToFront() {
		if (front.isEmpty()) {
			// step1: move s2.size() / 2 elements from s2 to s3
			for (int i = back.size() / 2; i > 0; i--) {
				buffer.offerFirst(back.pollFirst());
			}
			// step2: move the rest in s2 to s1
			while (!back.isEmpty()) {
				front.offerFirst(back.pollFirst());
			}
			// step3: move elements from s3 to s2
			while (!buffer.isEmpty()) {
				back.offerFirst(buffer.pollFirst());
			}
		}
	}

	private void moveToBack() {
		if (back.isEmpty()) {
			for (int i = front.size() / 2; i > 0; i--) {
				buffer.offerFirst(front.pollFirst());
			}
			while (!front.isEmpty()) {
				back.offerFirst(front.pollFirst());
			}
			while (!buffer.isEmpty()) {
				front.offerFirst(buffer.pollFirst());
			}
		}
	}

}
