package com.svetanis.algorithms.backtracking.permutations;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;

import java.util.List;

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
		List<String> permutations = newArrayList();
		List<Character> permutation = newArrayList();
		// indexed by POSITION, not by value -- two equal characters are still two
		// distinct elements, and only an index can tell them apart
		boolean[] visited = new boolean[s.length()];
		dfs(s, visited, permutation, permutations);
		return newList(permutations);
	}

	private static void dfs(String s, boolean[] visited, 
			List<Character> permutation, List<String> permutations) {
		if (permutation.size() == s.length()) {
			permutations.add(Joiner.on("").join(permutation));
			return;
		}
		for (int i = 0; i < s.length(); i++) {
			if (!visited[i]) {
				permutation.add(s.charAt(i));
				visited[i] = true;
				dfs(s, visited, permutation, permutations);
				permutation.remove(permutation.size() - 1);
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) {
		print(permutations("abc"));
	}
}
