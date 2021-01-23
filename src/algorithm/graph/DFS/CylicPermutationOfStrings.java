package algorithm.graph.DFS;

public class CylicPermutationOfStrings {
	public boolean canForm(String[] array) {
		return dfs(array, 1);
	}

	boolean dfs(String[] array, int level) {
		// base case
		if (level == array.length) {
			return isValid(array[level - 1], array[0]);
		}
		// try all valid branches
		for (int i = level; i < array.length; i++) {
			if (isValid(array[level - 1], array[i])) {
				swap(array, level, i);
				if (dfs(array, level + 1)) {
					return true;
				}
				swap(array, level, i);
			}
		}
		return false;
	}

	boolean isValid(String one, String two) {
		return one.charAt(one.length() - 1) == two.charAt(0);
	}

	void swap(String[] array, int i, int j) {
		String temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static void main(String[] args) {
		CylicPermutationOfStrings solu = new CylicPermutationOfStrings();
		System.out.println(solu.canForm(new String[] {"ab", "ca", "bc"}));
		System.out.println(solu.canForm(new String[] {"ab", "ca", "bcb", "bd","dc"}));
		System.out.println(solu.canForm(new String[] {"ab", "ca", "bd"}));
	}
}
