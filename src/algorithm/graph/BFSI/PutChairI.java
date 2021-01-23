package algorithm.graph.BFSI;

import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


public class PutChairI {
	private static final char EQUIP = 'E';
	private static final char OB = 'O';

	public List<Integer> putChair(char[][] gym) {
		int M = gym.length;
		int N = gym[0].length;
		
		// use a matrix to record the sum of shortest path cost
		// from each cell to all the ‘E’ cells
	int[][] cost = new int[M][N];
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
		if (EQUIP == gym[i][j]) {
			// use BFS to calculate the shortest path cost from each of the equipments to all the other reachable cells
			// and add the cost to each corresponding cell
		if (!addCost(cost, gym, i, j)) {
		return Arrays.asList(-1, -1);
	}
	}
	}
	}

	// find the cell with smallest sum of shorted path costs
	// to all the ‘E’ cells
	List<Integer> result = null;
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			// if slot <i, j> is available for a chair
			// update the result if cost[i][j] is smaller
		if (EQUIP != gym[i][j] && OB != gym[i][j]) {
		if (result == null) {
		result = Arrays.asList(i ,j);
	} else if (cost[i][j] < cost[result.get(0)][result.get(1)]) {
		result.set(0, i);
		result.set(1, j);
	}
	}
	}
	}
	return result != null ? result : Arrays.asList(-1, -1);
	}

	// calculate the path cost from <i, j> to any other <k, l> and add it into cost[k][l]

	private boolean addCost(int[][] cost, char[][] gym, int i, int j) {
		// use a boolean matrix to make sure each cell will be visited
		// no more than once
		boolean[][] visited = new boolean[gym.length][gym[0].length];
		// record the path cost for breadth-first-search
		int pathCost  = 1;
		Queue<Pair> queue = new LinkedList<>();
		visited[i][j] = true;
		queue.offer(new Pair(i, j));
		while (!queue.isEmpty()) {
		int size = queue.size();
		// the following for loop is taking care of next level
		for (int l = 0; l < size; l++) {
		Pair cur = queue.poll();
		List<Pair> neis = getNeis(cur, gym);
		for (Pair nei : neis) {
		if (!visited[nei.i][nei.j]) {
		visited[nei.i][nei.j] = true;
		cost[nei.i][nei.j] += pathCost;
		queue.offer(nei);
	}
	}
	}
	// advance the pathCost by 1 for each level
	pathCost++;
	}
	// if there exists another ‘E’ cell not reachable from the path start ‘E’ cell, we return false
	for (int l = 0; l < gym.length; l++) {
		for (int m = 0; m < gym[0].length; m++) {
		if (!visited[l][m] && EQUIP == gym[l][m]) {
		return false;
	}
	}
	}
	return true;
	}

	private List<Pair> getNeis(Pair cur, char[][] gym) {
		int x = cur.i;
		int y = cur.j;
		int M = gym.length;
		int N = gym[0].length;
		List<Pair> neis = new ArrayList<>();
		if (x  + 1 < M && OB != gym[x + 1][y]) {
		neis.add(new Pair(x + 1, y));
	}
	if (y  + 1 < N && OB != gym[x][y + 1]) {
		neis.add(new Pair(x, y + 1));
	}
	if (x - 1 >= 0 && OB != gym[x - 1][y]) {
		neis.add(new Pair(x - 1, y));
	}
	if (y - 1 >= 0 && OB != gym[x][y - 1]) {
		neis.add(new Pair(x, y - 1));
	}
	return neis;
	}

	static class Pair {
		int i;
		int j;
		
		Pair(int i, int j) {
		this.i = i;
		this.j = j;
	}
	}
	
	public static void main(String[] args) {
		char[][] gym = new char[3][];
		gym[0] = new char[] {'E', 'O', 'C'};
		gym[1] = new char[] {'C', 'E',  'C'};
		gym[2] = new char[] {'C',  'C',  'C'};
		PutChairI solu = new PutChairI();
		List<Integer> result = solu.putChair(gym);
		for (Integer co : result) {
			System.out.print(co + " ");
		}
	}

}
