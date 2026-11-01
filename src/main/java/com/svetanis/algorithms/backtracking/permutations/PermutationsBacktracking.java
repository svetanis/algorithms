package com.svetanis.algorithms.backtracking.permutations;

import java.util.ArrayList;
import java.util.List;

// No LeetCode number: LeetCode 46 asks the same for an array of numbers (see Permutations46).
//
// Input: a string s. Assumed distinct characters, as in 46; with repeated characters it still
// returns n! entries, one per ordering of the POSITIONS, so equal strings repeat. Returns every
// ordering of the characters of s, in the order the search finds them.
//
// The one idea: fill the positions of the answer left to right. permutation holds the
// characters placed so far, and visited[i] says whether s.charAt(i) is among them. At each
// position try every character not yet used: place it, fill the rest, then take it back so the
// next character can try the same position. When all n positions are filled, join the
// characters into a string. The first position has n choices, the next n - 1, and so on: n!.
//
// Time: O(n * n!) -- n! orderings, each joined in O(n); the tree has fewer than 3 * n! nodes,
// each scanning the n flags.
// Space: O(n) beyond the answer -- recursion depth n and the flags; the answer holds n! strings of length n.
//
// Siblings: Permutations46 is the same walk on an array of numbers. PermutationsImmutable
// builds each ordering by cutting the chosen character out of the remaining string instead
// of keeping flags; PermutationsMutableBacktrack swaps characters inside one array.

public final class PermutationsBacktracking {

	public static List<String> permutations(String s) {
		List<String> permutations = new ArrayList<>();
		List<Character> permutation = new ArrayList<>();
		// indexed by POSITION, not by value -- two equal characters are still two
		// distinct elements, and only an index can tell them apart
		boolean[] visited = new boolean[s.length()];
		dfs(s, visited, permutation, permutations);
		return permutations;
	}

	private static void dfs(String s, boolean[] visited,
			List<Character> permutation, List<String> permutations) {
		if (permutation.size() == s.length()) {
			permutations.add(join(permutation)); // RECORD: the characters placed, as one string
			return;
		}
		for (int i = 0; i < s.length(); i++) {
			if (!visited[i]) { // SKIP: this character is already placed
				permutation.add(s.charAt(i)); // PLACE: it takes the next position
				visited[i] = true;
				dfs(s, visited, permutation, permutations);
				permutation.remove(permutation.size() - 1); // UNDO: take it back
				visited[i] = false;
			}
		}
	}

	private static String join(List<Character> chars) {
		StringBuilder sb = new StringBuilder();
		for (char c : chars) {
			sb.append(c);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(permutations("abc")); // [abc, acb, bac, bca, cab, cba]
	}
}
