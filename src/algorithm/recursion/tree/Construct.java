package algorithm.recursion.tree;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import datastructure.tree.TreeNode;

public class Construct {
	public TreeNode construct(int[] in, int[] level) {
		List<Integer> l = getList(level);
		Map<Integer, Integer> indexMap = getIndexMap(in);
		return construct(in, 0, in.length - 1, l, indexMap);
	}

	public TreeNode construct(int[] in, int inLeft, int inRight, List<Integer> level, Map<Integer, Integer> indexMap) {
		// base case
		if (inLeft > inRight) {
		return null;
	}
	TreeNode node = new TreeNode(level.get(0));
	int rootIndex = indexMap.get(node.key);
	List<Integer> leftLevel = new ArrayList<>();
	List<Integer> rightLevel = new ArrayList<>();
	for (Integer num : level) {
		int index = indexMap.get(num);
		if (index < rootIndex) {
		leftLevel.add(num);
	} else if (index > rootIndex) {
		rightLevel.add(num);
	}
	}
	node.left = construct(in, inLeft, rootIndex - 1, leftLevel, indexMap);
	node.right = construct(in, rootIndex  + 1, inRight, rightLevel, indexMap);
	return node;
	}

	List<Integer> getList(int[] array) {
		List<Integer> list = new ArrayList<>();
		for (int num : array) {
		list.add(num);
	}
	return list;
	}

	Map<Integer, Integer> getIndexMap(int[] array) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < array.length; i++) {
		map.put(array[i], i);
	}
	return map;
	}
	
	public static void main(String[] args) {
		Construct solu = new Construct();
		solu.construct(new int[]{1, 6}, new int[] {1, 6});
	}

}
