package javabasic;

import java.util.List;
import java.util.ArrayList;

public class MyMoney {
	public List<List<Integer>> combinations(int target, int[] coins) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> solution = new ArrayList<>();
		combinations(target, coins, 0, solution, result);
		return result;
	}

	public void combinations(int moneyLeft, int[] coins, int index, List<Integer> solution,
			List<List<Integer>> result) {
		// base case: index == coins.length - 1;
		if (index == coins.length - 1) {
			if (moneyLeft % coins[index] == 0) {
				solution.add(moneyLeft / coins[index]);
				result.add(new ArrayList<Integer>(solution));
				solution.remove(solution.size() - 1);
			}
			return;
		}
		// calculate the max number of coins[index]
		int max = moneyLeft / coins[index];
		for (int i = 0; i <= max; i++) {
			solution.add(i);
			combinations(moneyLeft - i * coins[index], coins, index + 1, solution, result);
			solution.remove(solution.size() - 1);
		}
	}

}
