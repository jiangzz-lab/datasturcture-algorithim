package javabasic;

import java.util.Queue;
import java.util.ArrayDeque;
import java.util.List;

public class MyGraph {
	// assumption: graph != null
	// assumption: graph != null
	public boolean isBipartite(List<GraphNode> graph) {
		// check empty
		if (graph.size() == 0) {
			return true;
		}
		int[] color = new int[graph.size()];
		for (GraphNode node : graph) {
			if (!isBipartite(node, graph, color)) {
				return false;
			}
		}
		return true;
	}

	public boolean isBipartite(GraphNode node, List<GraphNode> graph, int[] color) {
		int index = graph.indexOf(node);
		if (color[index] != 0) {
			return true;
		}
		// create FIFO queue for traversal;
		Queue<GraphNode> queue = new ArrayDeque<>();
		queue.offer(node);
		color[index] = 1;
		// traversal
		while (!queue.isEmpty()) {
			GraphNode cur = queue.poll();
			int curIndex = graph.indexOf(cur);
			for (GraphNode neighbor : cur.neighbors) {
				// check color of neighbors
				int neighborIndex = graph.indexOf(neighbor);
				if (color[neighborIndex] == 0) {
					queue.offer(neighbor);
					color[neighborIndex] = -color[curIndex];
				} else if (color[neighborIndex] == color[curIndex]) {
					return false;
				}
			}
		}
		return true;
	}
	

}
