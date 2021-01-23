package javabasic;

import java.util.List;

import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.LinkedList;

import javabasic.TreeNode;
import java.util.Set;
import java.util.HashSet;

public class MyTree {
	// delete target in tree root, return the new root
	public TreeNode delete(TreeNode root, int target) {
		if (root == null) {
			return null;
		}
		if (target < root.key) {
			root.left = delete(root.left, target);
			return root;
		} else if (target > root.key) {
			root.right = delete(root.right, target);
			return root;
		}
		// the next logic is to take care of the case root.key == target
		if (root.left == null) { // case1, 3 -- replace root with root.right
			return root.right;
		} else if (root.left != null && root.right == null) {
			// case2 -- replace root with root.left
			return root.left;
		}

		// the next logic is for case4 root.left != null && root.right != null
		// 4a -- replace root.right with root.right.right
		if (root.right.left == null) {
			root.right = root.right.right;
			return root;
		}
		// 4b -- find the smallest in root.right.left
		TreeNode prev = root.right;
		TreeNode cur = root.right.left;
		while (cur.left != null) {
			prev = cur;
			cur = cur.left;
		}
		// now cur is the smallest, prev is its parent
		// ask prev to take over cur.right
		prev.left = cur.right;
		// cur shall take over root.left, root.right
		cur.left = root.left;
		cur.right = root.right;
		return cur;
	}

	public List<Integer> inOrderRec(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		inOrder(root, list);
		return list;
	}

	private void inOrder(TreeNode root, List<Integer> list) {
		// base case
		if (root == null) {
			return;
		}
		inOrder(root.left, list);
		list.add(root.key);
		inOrder(root.right, list);
	}

	public List<Integer> preOrder(TreeNode root) {
		// create a list to store the result
		List<Integer> list = new ArrayList<>();
		// check null
		if (root == null) {
			return list;
		}
		// root != null, create a stack as buffer
		Deque<TreeNode> stack = new ArrayDeque<>();
		stack.offerFirst(root);
		while (!stack.isEmpty()) {
			// pop the top node
			// add its key into list and its children into stack
			TreeNode node = stack.pollFirst();
			list.add(node.key);
			if (node.right != null) {
				stack.offerFirst(node.right);
			}
			if (node.left != null) {
				stack.offerFirst(node.left);
			}
		}
		return list;
	}

	public List<Integer> inOrder(TreeNode root) {
		// create list for result
		List<Integer> list = new ArrayList<>();
		// check null
		if (root == null) {
			return list;
		}
		// create stack as buffer
		Deque<TreeNode> stack = new ArrayDeque<>();
		stack.offerFirst(root);
		TreeNode nxt = root.left;
		while (!stack.isEmpty() || nxt != null) {
			if (nxt != null) {
				stack.offerFirst(nxt);
				nxt = nxt.left;
			} else {
				TreeNode node = stack.pollFirst();
				list.add(node.key);
				nxt = node.right;
			}
		}
		return list;
	}

	public List<Integer> postOrder(TreeNode root) {
		// create result list
		List<Integer> list = new ArrayList<>();
		// check null
		if (root == null) {
			return list;
		}
		// create a stack as buffer
		Deque<TreeNode> stack = new ArrayDeque<>();
		stack.offerFirst(root);
		TreeNode prev = null;
		while (!stack.isEmpty()) {
			TreeNode cur = stack.peekFirst();
			if (prev == null || prev.left == cur || prev.right == cur) { // case1
				// push the left child if there is any
				if (cur.left != null) {
					stack.offerFirst(cur.left);
				} else if (cur.right != null) { // no left child -- push the right child if there is any
					stack.offerFirst(cur.right);
				} else { // no child -- pop and add key to the list
					stack.pollFirst();
					list.add(cur.key);
				}
			} else if (cur.left == prev) { // case2
				// push the right child if there is any
				if (cur.right != null) {
					stack.offerFirst(cur.right);
				} else { // no right child -- left child already in list in a previous iteration, pop
					stack.pollFirst();
					list.add(cur.key);
				}
			} else { // case3: cur.right == prev
				// back from right child -- pop
				stack.pollFirst();
				list.add(cur.key);
			}
			prev = cur;
		}
		return list;
	}

	public List<List<Integer>> layerByLayer(TreeNode root) {
		// create result list
		List<List<Integer>> result = new ArrayList<>();
		// check null
		if (root == null) {
			return result;
		}
		// define a buffer queue
		Queue<TreeNode> buffer = new LinkedList<>();
		// init: root node in the buffer
		buffer.offer(root);
		int layerIndex = 0;
		while (!buffer.isEmpty()) {
			int size = buffer.size();
			List<Integer> layer = new ArrayList<>();
			while (size-- > 0) {
				TreeNode front = buffer.poll();
				layer.add(front.key);
				if (front.left != null) {
					buffer.offer(front.left);
				}
				if (front.right != null) {
					buffer.offer(front.right);
				}
			}
			result.add(layer);
			layerIndex++;
		}
		return result;
	}

	public boolean isCompleted(TreeNode root) {
		// check null
		if (root == null) {
			return true;
		}
		// create FIFO queue for traversal; hasBubble as flag
		// init: root node in the queue; hasBubble false
		Queue<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);
		boolean hasBubble = false;
		// traverse
		while (!queue.isEmpty()) {
			TreeNode cur = queue.poll();
			if (cur.left == null) {
				hasBubble = true;
			} else if (hasBubble) {
				return false;
			} else { // cur.left != null, hasBubble == false
				queue.offer(cur.left);
			}
			if (cur.right == null) {
				hasBubble = true;
			} else if (hasBubble) {
				return false;
			} else { // cur.right != null, hasBubble == false
				queue.offer(cur.right);
			}
		}
		return true;
	}

	public TreeNode buildTree(Integer[] array) {
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

	public List<Integer> zigZag(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		Deque<TreeNode> deque = new ArrayDeque<>();
		deque.offerFirst(root);
		int level = 0;
		while (!deque.isEmpty()) {
			int size = deque.size();
			while (size-- > 0) {
				if (level % 2 == 0) {
					TreeNode cur = deque.pollLast();
					result.add(cur.key);
					if (cur.right != null) {
						deque.offerFirst(cur.right);
					}
					if (cur.left != null) {
						deque.offerFirst(cur.left);
					}
				} else {
					TreeNode cur = deque.pollFirst();
					result.add(cur.key);
					if (cur.left != null) {
						deque.offerLast(cur.left);
					}
					if (cur.right != null) {
						deque.offerLast(cur.right);
					}
				}
			}
			level++;
		}
		return result;
	}

	public boolean exist(TreeNode root, int target) {
		Set<Integer> sumSet = new HashSet<>();
		return exist(root, target, 0, sumSet);
	}

	public boolean exist(TreeNode root, int target, int prefixSum, Set<Integer> sumSet) {
		// base case
		if (root == null) {
			return false;
		}
		// check whether a path exists in root-to-curr
		prefixSum += root.key;
		if (prefixSum == target || sumSet.contains(prefixSum - target)) {
			return true;
		}
		sumSet.add(prefixSum);
		boolean leftRes = exist(root.left, target, prefixSum, sumSet);
		boolean rightRes = exist(root.right, target, prefixSum, sumSet);
		sumSet.remove(prefixSum);
		return leftRes || rightRes;
	}

	public TreeNode flatten(TreeNode root) {
		TreeNode dummyHead = new TreeNode(-1);
		TreeNode[] prev = new TreeNode[] { dummyHead };
		flattenHelper(root, prev);
		return dummyHead.right;
	}

	void flattenHelper(TreeNode root, TreeNode[] prev) {
		// just return if root is null
		if (root == null) {
			return;
		}
		// link current node after prev
		TreeNode left = root.left;
		TreeNode right = root.right;
		prev[0].right = root;
		prev[0].left = null;
		prev[0] = root;
		flattenHelper(left, prev);
		flattenHelper(right, prev);
	}
	
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
