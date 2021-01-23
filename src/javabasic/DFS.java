package javabasic;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Set;
import java.util.HashSet;

import java.util.Random;

public class DFS {
	// find combinations of factors to produce target
	public List<List<Integer>> factorComb(int target) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> solu = new ArrayList<>();
		List<Integer> factors = findFactors(target);
		dfsFactors(target, factors, 0, solu, result);
		return result;
	}

	public void dfsFactors(int rest, List<Integer> factors, int index, List<Integer> solu, List<List<Integer>> result) {
		// base case
		if (index == factors.size()) {
			if (rest == 1) {
				result.add(new ArrayList<>(solu));
			}
			return;
		}
		// try all possible occurrence of index-th factor
		// 0 occurrence
		dfsFactors(rest, factors, index + 1, solu, result);
		int factor = factors.get(index);
		int size = solu.size();
		while (rest % factor == 0) { // The solution won’t be valid if rest % factor != 0
			solu.add(factor);
			rest /= factor;
			dfsFactors(rest, factors, index + 1, solu, result);
		}
		// solu is not reset inside the while loop since the solution in current
		// iteration is based on previous ones
		// solu.subList(size, solu.size()).clear();
		solu.subList(size, solu.size()).clear();
	}

	List<Integer> findFactors(int target) {
		List<Integer> fac = new ArrayList<>();
		for (int i = 2; i < target - 1; i++) {
			if (target % i == 0) {
				fac.add(i);
			}
		}
		return fac;
	}

	public List<String> allPermutationsOfSubsets(String set) {
		List<String> result = new ArrayList<>();
		if (set == null) {
			return result;
		}
		char[] array = set.toCharArray();

		dfsPermutations(array, 0, result);
		return result;
	}

	public void dfsPermutations(char[] array, int index, List<String> result) {
		result.add(new String(array, 0, index));
		if (index == array.length) {
			return;
		}
		for (int i = index; i < array.length; i++) {
			swap(array, i, index);
			dfsPermutations(array, index + 1, result);
			swap(array, i, index);
		}
	}

	public void swap(char[] array, int i, int j) {
		char temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public int[] keepDistance(int k) {
		List<int[]> result = new ArrayList<>();
		int[] solu = new int[2 * k];
		dfsDistance(k, 0, solu, result);
		if (result.size() == 0) {
			return null;
		} else {
			return result.get(0);
		}
	}

	public void dfsDistance(int k, int index, int[] solu, List<int[]> result) {
		// if at least one solution is found, do not need to find more
		if (result.size() > 0) {
			return;
		}
		// base case: a solution found
		if (index == k) {
			result.add(Arrays.copyOf(solu, solu.length));
			return;
		}
		// num = index + 1; try places from 0 to 2k - (num + 2)
		int num = index + 1;
		for (int j = 0; j <= 2 * k - (num + 2); j++) {
			if (solu[j] == 0 && solu[j + num + 1] == 0) {
				solu[j] = num;
				solu[j + num + 1] = num;
				dfsDistance(k, index + 1, solu, result);
				solu[j] = 0;
				solu[j + num + 1] = 0;
			}
		}
	}

	public List<String> Restore(String ip) {
		List<String> result = new ArrayList<>();
		if (ip == null || ip.length() < 4) {
			return result;
		}
		StringBuilder solu = new StringBuilder();
		dfsIP(ip, 0, 0, solu, result);
		return result;
	}

	public void dfsIP(String ip, int index, int dots, StringBuilder solu, List<String> result) {
		// base case
		if (solu.length() >= 3 && solu.charAt(solu.length() - 3) == '.' && solu.charAt(solu.length() - 2) == '0'
				&& solu.charAt(solu.length() - 1) != '.') {
			return;
		}
		if (index == ip.length()) {
			if (dots == 3 && solu.charAt(solu.length() - 1) != '.' && getNumber(solu) <= 255) {
				result.add(solu.toString());
			}
			return;
		}
		int lastNum = getNumber(solu);
		if (lastNum < 255) {
			solu.append(ip.charAt(index));
			dfsIP(ip, index + 1, dots, solu, result);
			solu.deleteCharAt(solu.length() - 1);
		}
		if (lastNum <= 255 && solu.length() > 0 && solu.charAt(solu.length() - 1) != '.') {
			solu.append('.');
			dfsIP(ip, index, dots + 1, solu, result);
			solu.deleteCharAt(solu.length() - 1);
		}
	}

	public int getNumber(StringBuilder solu) {
		int num = 0;
		int index = solu.length() - 1;
		while (index >= 0 && solu.charAt(index) != '.') {
			index--;
		}
		index++;
		while (index < solu.length()) {
			num = 10 * num + getDigit(solu.charAt(index++));
		}
		return num;
	}

	public int getDigit(char ch) {
		return (int) (ch - '0');
	}

	public List<String> subSets(String set) {
		List<String> result = new ArrayList<>();
		if (set == null) {
			return result;
		}
		char[] array = set.toCharArray();
		Arrays.sort(array);
		StringBuilder solu = new StringBuilder();
		dfsSubSets(array, 0, solu, result);
		return result;
	}

	public void dfsSubSets(char[] array, int level, StringBuilder solu, List<String> result) {
		// base case
		if (level == array.length) {
			result.add(solu.toString());
			return;
		}
		// add array[level]
		solu.append(array[level]);
		dfsSubSets(array, level + 1, solu, result);
		solu.deleteCharAt(solu.length() - 1);

		// not add
		// next level is the first level to make array[next] != array[level]
		int next = level + 1;
		while (next < array.length && array[next] == array[level]) {
			next++;
		}
		dfsSubSets(array, next, solu, result);
	}

	public List<String> subSequences(String set) {
		if (set == null) {
			return new ArrayList<String>();
		}
		Set<String> result = new HashSet<>();
		char[] array = set.toCharArray();
		StringBuilder solu = new StringBuilder();
		dfsSubSequences(array, 0, solu, result);
		return new ArrayList(result);
	}

	public void dfsSubSequences(char[] array, int level, StringBuilder solu, Set<String> result) {
		// base case
		if (level == array.length) {
			result.add(solu.toString());
			return;
		}
		// add array[level]
		solu.append(array[level]);
		dfsSubSequences(array, level + 1, solu, result);
		solu.deleteCharAt(solu.length() - 1);

		// not add
		// next level is the first level to make array[next] != array[level]
		int next = level + 1;
		while (next < array.length && array[next] == array[level]) {
			next++;
		}
		dfsSubSequences(array, next, solu, result);
	}

	public int[][] maze(int n) {
		int[][] maze = new int[n][n];
		for (int[] array : maze) {
		Arrays.fill(array, 1);
	}
	maze[0][0] = 0;
		generate(maze, 0, 0);
		return maze;
	}
	enum Dir {
		WEST(-1, 0), SOUTH(0, -1), EAST(1, 0), NORTH(0, 1);
		
		int deltaX;
		int deltaY;

		Dir (int x, int y) {
		deltaX = x;
		deltaY = y;
	}

	public int moveX(int x, int step) {
		return x + deltaX * step;
	}

	public int moveY(int y, int step) {
		return y + deltaY * step;
	}
	}

	void generate(int[][] maze, int x, int y) {
		// generate a random array of directions
		Dir[] dirs = Dir.values();
	shuffle(dirs);
	for (Dir dir : dirs) {
		int nextX = dir.moveX(x, 2);
		int nextY = dir.moveY(y, 2);
		if (validWall(maze, nextX, nextY)) {
			maze[nextX][nextY] = 0;
			maze[dir.moveX(x, 1)][dir.moveY(y, 1)] = 0;
			generate(maze, nextX, nextY);
	}
	}
	}
	
	boolean validWall(int[][] maze, int x, int y) {
		return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 1;
	}


	void shuffle(Dir[] dirs) {
		for (int i = 0; i < dirs.length; i++) {
		Random rand = new Random();
		int target = rand.nextInt(dirs.length);
		swap(dirs, i, target);
	}
	}

	void swap(Dir[] array, int i, int j) {
		Dir temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}
