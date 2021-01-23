package algorithm.tree.levelorder;

import datastructure.tree.TreeNode;
import algorithm.tree.levelorder.Construct;
import algorithm.tree.Search;

import java.util.Queue;
import java.util.ArrayDeque;


public class isCousin {
	public boolean isCousin(TreeNode root, TreeNode one, TreeNode two) {
		if (one == root || two == root) {
			return false;
		}
		Queue<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);
		int level1 = 0;
		int level2 = 0;
		int level = 0;
		TreeNode oneParent = null;
		TreeNode twoParent = null;
		while (!queue.isEmpty()) {
			level++;
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode curr = queue.poll();
				if (curr.left != null) {
					queue.offer(curr.left);
				}
				if (curr.right != null) {
					queue.offer(curr.right);
				}
				if (curr.left == one || curr.right == one) {
					level1 = level;
					oneParent = curr;
				}
				if (curr.left == two || curr.right == two) {
					level2 = level;
					twoParent = curr;
				}
			}
		}
		return level1 == level2 && oneParent != twoParent;
	}
	
	public static void main(String[] args) {
		Construct constructor = new Construct();
		Search searcher = new Search(); 
		TreeNode root = constructor.construct(new Integer[] {6, 3, 5, 7, 8, 1, 13});
		TreeNode one = searcher.search(root, 7);
		TreeNode two = searcher.search(root, 1);
		
		isCousin solu = new isCousin();
		System.out.println(solu.isCousin(root, one, two));
		
		one = searcher.search(root, 3);
		two = searcher.search(root, 5);
		System.out.println(solu.isCousin(root, one, two));
		
		one = searcher.search(root, 7);
		two = searcher.search(root, 5);
		System.out.println(solu.isCousin(root, one, two));
	}
}
