package com.svetanis.algorithms.backtracking.additionalstates;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;

import java.util.List;
import java.util.Set;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

// given a set of distinct numbers
// find all of its permutations

// Permutation is defined as the re-arranging 
// of the elements of the set

// if a set has 'n' distinct elements
// it will have n! permutations

public final class PermutationsBacktracking {
	// Time Complexity: O(n * n!)
	// Space Complexity: O(n * n!)

	public static ImmutableList<String> permutations(String s) {
		List<String> list = newArrayList();
		List<Character> path = newArrayList();
		Set<Character> visited = newHashSet();
		dfs(s, visited, path, list);
		return newList(list);
	}

	private static void dfs(String s, Set<Character> visited, 
		List<Character> path,	List<String> list) {
		if (path.size() == s.length()) {
			list.add(Joiner.on("").join(path));
			return;
		}
		for (char c : s.toCharArray()) {
			if (!visited.contains(c)) {
				path.add(c);
				visited.add(c);
				dfs(s, visited, path, list);
				path.remove(path.size() - 1);
				visited.remove(c);
			}
		}
	}

	public static void main(String[] args) {
		print(permutations("abc"));
	}
}
