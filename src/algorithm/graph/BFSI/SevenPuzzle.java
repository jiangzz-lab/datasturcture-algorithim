package algorithm.graph.BFSI;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.ArrayDeque;

// solve the "Seven Puzzle" problem 
public class SevenPuzzle {
	static class State {
		static final int rows = 2;
		static final int cols = 4;
		int[] values;
		int indexOfZero;

		public State(int[] values) {
			this.values = new int[8];
			for (int i = 0; i < 8; i++) {
				this.values[i] = values[i];
				if (values[i] == 0) {
					indexOfZero = i;
				}
			}
		}

		List<State> findNeighbors() {
			List<State> neighbors = new ArrayList<>();
			int x = indexOfZero / cols;
			int y = indexOfZero % cols;
			int[] dx = new int[] { -1, 0, 1, 0 };
			int[] dy = new int[] { 0, -1, 0, 1 };
			for (int i = 0; i < 4; i++) {
				int nextX = x + dx[i];
				int nextY = y + dy[i];
				if (valid(nextX, nextY)) {
					int newIndex = nextX * cols + nextY;
					neighbors.add(new State(swap(values, indexOfZero, newIndex)));
				}
			}
			return neighbors;
		}

		boolean valid(int nextX, int nextY) {
			return nextX >= 0 && nextX < rows && nextY >= 0 && nextY < cols;
		}

		int[] swap(int[] values, int i, int j) {
			int[] result = Arrays.copyOf(values, values.length);
			int temp = result[i];
			result[i] = result[j];
			result[j] = temp;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (!(obj instanceof State)) {
				return false;
			}
			State another = (State) obj;
			if (this.values.length != another.values.length) {
				return false;
			}
			for (int i = 0; i < this.values.length; i++) {
				if (this.values[i] != another.values[i]) {
					return false;
				}
			}
			return true;
		}

		@Override
		public int hashCode() {
			int sum = 0;
			int factor = 13;
			for (int num : values) {
				sum += num * factor;
				factor *= factor;
			}
			return sum;
		}

		boolean isTarget() {
			for (int i = 0; i < 8; i++) {
				if (values[i] != i) {
					return false;
				}
			}
			return true;
		}
	}

	public int numOfSteps(int[] values) {
		Queue<State> frontier = new ArrayDeque<>();
		Set<State> generated = new HashSet<>();
		State init = new State(values);
		if (init.isTarget()) {
			return 0;
		}

		frontier.offer(init);
		generated.add(init);
		int steps = 0;

		while (!frontier.isEmpty()) {
			int size = frontier.size();
			steps++;
			while (size-- > 0) {
				State curr = frontier.poll();
				List<State> neighbors = curr.findNeighbors();
				for (State nei : neighbors) {
					if (nei.isTarget()) {
						return steps;
					}
					if (generated.add(nei)) {
						frontier.offer(nei);
					}
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		SevenPuzzle solu = new SevenPuzzle();
		int[] input1 = new int[] { 0, 1, 2, 3, 4, 5, 6, 7 };
		int[] input2 = new int[] { 4, 1, 2, 3, 5, 0, 6, 7 };
		int[] input3 = new int[] { 1, 2, 3, 4, 5, 6, 7, 0 };
		int[] input4 = new int[] { 6, 7, 3, 5, 4, 2, 1, 0};
		System.out.println(solu.numOfSteps(input1));
		System.out.println(solu.numOfSteps(input2));
		System.out.println(solu.numOfSteps(input3));
		System.out.println(solu.numOfSteps(input4));
	}
}
