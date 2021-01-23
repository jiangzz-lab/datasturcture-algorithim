package algorithm.tree;

import datastructure.tree.TreeNode;

public class Search {
	public TreeNode search(TreeNode root, int key) {
		if (root == null) {
			return null;
		}
		if (root.key == key) {
			return root;
		}
		TreeNode leftRes = search(root.left, key);
		TreeNode rightRes = search(root.right, key);
		return leftRes != null ? leftRes : rightRes; 
	}
}
