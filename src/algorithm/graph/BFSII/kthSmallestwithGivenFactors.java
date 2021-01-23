package algorithm.graph.BFSII;

import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashSet;

public class kthSmallestwithGivenFactors {
	static class Element implements Comparable<Element>{
		int x;
		int y;
		int z;
		
		public Element(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;;
		}
		
		public long value() {
			return power(3, x) * power(5, y) * power(7, z);
	}

	public long power(int a, int b) {
		return (long) Math.pow(a, b);
	}


	@Override
	public int compareTo(Element another) {
		if (this.value() == another.value()) {
		return 0;
	}
	return this.value() < another.value() ? -1 : 1;
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
	return this.x == another.x && this.y == another.y && this.z == another.z;
	}

	@Override
	public int hashCode() {
		return x * 303 + y * 50 + z * 7;
	}

	}

	public long kth(int k) {
		PriorityQueue<Element> minHeap = new PriorityQueue<Element>();
		Set<Element> generated = new HashSet<>();
		minHeap.offer(new Element(1, 1, 1));
		generated.add(new Element(1, 1, 1));
		while (k-- > 1) {
		Element curr = minHeap.poll();
		List<Element> neighbors = getNeighbors(curr);
		for (Element nei : neighbors) {
			if (generated.add(nei)) {
				minHeap.offer(nei);
			}
	}
	}
	return minHeap.peek().value();
	}

	List<Element> getNeighbors(Element e) {
		List<Element> result = new ArrayList<>();
		int x = e.x;
		int y = e.y;
		int z = e.z;
		
		result.add(new Element(x + 1, y, z));
		result.add(new Element(x, y + 1, z));
		result.add(new Element(x, y, z + 1));
		
		return result;
	}
	
	public static void main(String[] args) {
		kthSmallestwithGivenFactors solu = new kthSmallestwithGivenFactors();
	//	Element e = new Element(1, 1, 1);
	//	System.out.println(e.power(7, 1));
	//	System.out.println(solu.kth(1));
	//	System.out.println(solu.kth(2));
	//	System.out.println(solu.kth(3));
		System.out.println(solu.kth(7));
	}

}
