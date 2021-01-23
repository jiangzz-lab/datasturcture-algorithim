package javabasic;

import java.util.Iterator;
import java.util.Deque;
import java.util.ArrayDeque;

public class PreOrderIterator implements Iterator<TreeNode> {
	Deque<TreeNode> stack;

	// constructor
	PreOrderIterator(TreeNode root) {
		stack = new ArrayDeque<>();
		if (root != null) {
			stack.offerFirst(root);
		}
	}

// return next TreeNode if any
// return null if none
	@Override
	public TreeNode next() {
		if (hasNext()) {
			TreeNode top = stack.pollFirst();
			// push left, right child into stack
			if (top.right != null) {
				stack.offerFirst(top.right);
			}
			if (top.left != null) {
				stack.offerFirst(top.left);
			}
			return top;
		}
		return null;
	}

// return true if there is next TreeNode
	public boolean hasNext() {
		return !stack.isEmpty();
	}

}
