package algorithm.tree.levelorder;

import java.util.ArrayDeque;
import java.util.Queue;

import datastructure.tree.TreeNode;

public class Construct {
	public TreeNode construct(Integer[] array) {
		// check null, empty
		if (array == null || array.length == 0) {
			return null;
		}
		TreeNode root = new TreeNode(array[0]);
		int index = 0;
		Queue<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			// expand
			TreeNode cur = queue.poll();
			// generate
			if (++index < array.length && array[index] != null) {
				TreeNode left = new TreeNode(array[index]);
				cur.left = left;
				queue.offer(left);
			}
			if (++index < array.length && array[index] != null) {
				TreeNode right = new TreeNode(array[index]);
				cur.right = right;
				queue.offer(right);
			}
		}
		return root;
	}
}
