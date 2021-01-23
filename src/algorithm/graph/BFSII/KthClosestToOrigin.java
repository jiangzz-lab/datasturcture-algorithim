package algorithm.graph.BFSII;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.PriorityQueue;

public class KthClosestToOrigin {
	static class Element implements Comparable<Element> {
		int A;
		int B;
		int C;
		int value;

		public Element(int A, int B, int C, int value) {
			this.A = A;
			this.B = B;
			this.C = C;
			this.value = value;
		}

		@Override
		public int compareTo(Element another) {
			if (this.value == another.value) {
				return 0;
			}
			return this.value < another.value ? -1 : 1;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (!(obj instanceof Element)) {
				return false;
			}
			Element another = (Element) obj;
			return this.A == another.A && this.B == another.B && this.C == another.C;
		}

		@Override
		public int hashCode() {
			return A * 101 + B * 10 + C;
		}
	}

	public List<Integer> closest(int[] a, int[] b, int[] c, int k) {
		PriorityQueue<Element> minHeap = new PriorityQueue<>();
		Set<Element> generated = new HashSet<>();
		Element start = new Element(0, 0, 0, getSquareDistance(a[0], b[0], c[0]));
		minHeap.offer(start);
		generated.add(start);
		while (k-- > 1) {
			Element curr = minHeap.poll();
			List<Element> neighbors = getNeighbors(curr, a, b, c);
			for (Element nei : neighbors) {
				if (generated.add(nei)) {
					minHeap.offer(nei);
				}
			}
		}
		Element kth = minHeap.peek();
		List<Integer> result = new ArrayList<>();
		result.add(a[kth.A]);
		result.add(b[kth.B]);
		result.add(c[kth.C]);
		return result;
	}

	List<Element> getNeighbors(Element e, int[] a, int[] b, int[] c) {
		int A = e.A;
		int B = e.B;
		int C = e.C;

		List<Element> result = new ArrayList<>();
		if (A + 1 < a.length) {
			result.add(new Element(A + 1, B, C, getSquareDistance(a[A + 1], b[B], c[C])));
		}
		if (B + 1 < b.length) {
			result.add(new Element(A, B + 1, C, getSquareDistance(a[A], b[B + 1], c[C])));
		}
		if (C + 1 < c.length) {
			result.add(new Element(A, B, C + 1, getSquareDistance(a[A], b[B], c[C + 1])));
		}
		return result;
	}

	int getSquareDistance(int x, int y, int z) {
		return x * x + y * y + z * z;
	}

	public static void main(String[] args) {
		KthClosestToOrigin solu = new KthClosestToOrigin();
		int[] a = new int[] { 1, 2, 3 };
		int[] b = new int[] { 2, 4 };
		int[] c = new int[] { 1, 2 };
		List<Integer> result = solu.closest(a, b, c, 10);
		for (Integer num : result) {
			System.out.print(num + " ");
		}
	}
}
