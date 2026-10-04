package com.svetanis.algorithms.backtracking.combinations.sum;

import java.util.ArrayList;
import java.util.List;

// 216. Combination Sum III
//
// Find every combination of k numbers that sums to n, using only the numbers 1 to 9, each at
// most once. No combination twice; any order.
//
// Two methods, the same answers:
//   combinationSum           start-index over 1..9: the next call starts at candidate + 1, so
//                            numbers only grow along a combination; it records a combination
//                            only when both the sum and the count are right
//   combinationSumTakeOrSkip take-or-skip: decide each number 1..9 in turn, counting the sum down
//                            to 0; it stops early once the number is bigger than what is left
//                            or k numbers are already taken

public final class CombinationSumIII {
	// Time Complexity: O(k * 2^9), at most 2^9 = 512 sets of the numbers 1..9, each answer copied
	// in k steps -- a constant, whatever k and n are
	// Space Complexity: O(k) besides the output, the recursion depth and the combination built

	public static List<List<Integer>> combinationSum(int k, int n) {
		List<List<Integer>> combinations = new ArrayList<>();
		List<Integer> combination = new ArrayList<>();
		dfs(n, 1, 0, k, combination, combinations);
		return combinations;
	}

	private static void dfs(int target, int index, int sum, int k, //
			List<Integer> combination, List<List<Integer>> combinations) {
		if (sum == target && combination.size() == k) { // both must hold
			combinations.add(new ArrayList<>(combination));
			return;
		}
		for (int candidate = index; candidate <= 9; candidate++) {
			if (sum + candidate <= target && combination.size() < k) { // room in sum and in count
				combination.add(candidate); // choose
				dfs(target, candidate + 1, sum + candidate, k, combination, combinations); // larger next
				combination.remove(combination.size() - 1); // undo
			}
		}
	}

	public static List<List<Integer>> combinationSumTakeOrSkip(int k, int n) {
		List<List<Integer>> combinations = new ArrayList<>();
		List<Integer> combination = new ArrayList<>();
		takeOrSkip(1, n, k, combination, combinations);
		return combinations;
	}

	// left: how much of n is still to reach
	private static void takeOrSkip(int index, int left, int k, //
			List<Integer> combination, List<List<Integer>> combinations) {
		if (left == 0) { // the sum is reached; an answer only with exactly k numbers
			if (combination.size() == k) {
				combinations.add(new ArrayList<>(combination));
			}
			return;
		}
		if (index > 9 || index > left || combination.size() >= k) { // nothing more can fit
			return;
		}
		combination.add(index); // TAKE index
		takeOrSkip(index + 1, left - index, k, combination, combinations);
		combination.remove(combination.size() - 1); // SKIP index: undo the take
		takeOrSkip(index + 1, left, k, combination, combinations);
	}

	public static void main(String[] args) {
		System.out.println(combinationSum(3, 7)); // [[1, 2, 4]]
		System.out.println(combinationSum(3, 9)); // [[1, 2, 6], [1, 3, 5], [2, 3, 4]]
		System.out.println(combinationSum(4, 1)); // []
		System.out.println(combinationSumTakeOrSkip(3, 7)); // [[1, 2, 4]]
		System.out.println(combinationSumTakeOrSkip(3, 9)); // [[1, 2, 6], [1, 3, 5], [2, 3, 4]]
		System.out.println(combinationSumTakeOrSkip(4, 1)); // []
	}
}
