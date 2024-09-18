package com.svetanis.algorithms.backtracking.deduplication;

import static com.svetanis.java.base.collect.Lists.newList;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

// given an array of distinct integers and a target
// return a list of all unique combinations of candidates
// where the chosen numbers sum to target. 
// the same number may be chosen from candidates an 
// unlimited number of times. two combinations are unique
// if the frequency of at least one of the chosen numbers
// is different. 

public final class CombinationSum {

	public static ImmutableList<ImmutableList<Integer>> combinationSum(int target, List<Integer> candidates) {
		List<Integer> path = new ArrayList<>();
		List<ImmutableList<Integer>> list = new ArrayList<>();
		dfs(target, 0, 0, candidates, path, list);
		return newList(list);
	}

	private static void dfs(int target, int index, int sum, List<Integer> candidates, List<Integer> path,
			List<ImmutableList<Integer>> list) {
		if (sum == target) {
			list.add(newList(path));
			return;
		}

		for (int i = index; i < candidates.size(); i++) {
			int candidate = candidates.get(i);
			if (sum + candidate <= target) {
				path.add(candidate);
				int next = i == 0 ? 0 : i;
				dfs(target, next, sum + candidate, candidates, path, list);
				path.remove(path.size() - 1);
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(combinationSum(7, asList(2, 3, 6, 7)));// [2,2,3],[7]
		System.out.println(combinationSum(8, asList(2, 3, 5))); // [2,2,2,2],[2,3,3],[3,5]
		System.out.println(combinationSum(1, asList(2))); // []
		System.out.println(combinationSum(1, asList(1))); // [1]
		System.out.println(combinationSum(2, asList(1))); // [1,1]
	}
}