package algorithm.graph.BFSI;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.ArrayDeque;

public class CourseScheduleII {
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		// the adjacency list representation of prerequisites
		List<List<Integer>> graph = new ArrayList<>(numCourses);
		for (int i = 0; i < numCourses; i++) {
			graph.add(new ArrayList<>());
		}
		for (int[] dependency : prerequisites) {
			int x = dependency[0];
			int y = dependency[1];
			graph.get(y).add(x);
		}
		return topologicalSort(graph);
	}

	private int[] topologicalSort(List<List<Integer>> graph) {
		int numCourses = graph.size();
		int[] topologicalOrder = new int[numCourses];
		int[] incomingEdges = new int[numCourses];
		for (int x = 0; x < numCourses; x++) {
			// count the incoming edges of y
			for (int y : graph.get(x)) {
				incomingEdges[y]++;
			}
		}

		// init: put nodes without incoming edges to queue
		Queue<Integer> queue = new ArrayDeque<>();
		for (int x = 0; x < numCourses; x++) {
			if (incomingEdges[x] == 0) {
				queue.offer(x);
			}
		}

		int numExpanded = 0;
		while (!queue.isEmpty()) {
			int x = queue.poll();
			topologicalOrder[numExpanded++] = x;
			for (int y : graph.get(x)) {
				if (--incomingEdges[y] == 0) {
					queue.offer(y);
				}
			}

		}
		return numExpanded == numCourses ? topologicalOrder : new int[] {};
	}
	
	public static void main(String[] args) {
		CourseScheduleII solu = new CourseScheduleII();
		int[][] prerequisites = new int[1][];
		prerequisites[0] = new int[] {1, 0};
		System.out.print(Arrays.toString(solu.findOrder(2, prerequisites)));
	}

}
