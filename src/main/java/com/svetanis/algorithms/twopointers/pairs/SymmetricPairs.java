package com.svetanis.algorithms.twopointers.pairs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Symmetric Pairs
//
// Input: an array of pairs {a, b}; the FIRST numbers of the pairs are all different.
// Return: every pair {a, b} whose mirror {b, a} also occurs, listed when the later of
// the two is reached, as the earlier one. A pair {a, a} is not its own mirror.
// Convention the code follows: the first numbers are distinct, so the map below can be
// keyed by them. With a repeated first number the map keeps one partner per key and a
// symmetric pair can be missed: {1, 2}, {3, 1}, {1, 3} lists nothing. The source
// statement would settle whether first numbers can repeat.
//
// The one idea: keep a map from first number to second number for the pairs seen so
// far. The mirror of {a, b} would be stored under key b with value a, so one lookup
// says whether it came earlier. A pair is stored only when no stored pair starts with
// its second number b; with distinct first numbers the skipped pair can never be
// matched later anyway, since its mirror would have to start with b again.
//
// Time: O(n) -- one lookup per pair.
// Space: O(n) -- the map.

public final class SymmetricPairs {

	public static List<int[]> pairs(int[][] pairs) {
		Map<Integer, Integer> map = new HashMap<>();     // first -> second, for the pairs seen
		List<int[]> list = new ArrayList<>();
		for (int[] pair : pairs) {
			int left = pair[0];
			int right = pair[1];
			if (map.containsKey(right)) {
				if (map.get(right) == left) {
					list.add(new int[] { right, left }); // FOUND: the earlier pair was {right, left}
				}
			} else {
				map.put(left, right);                    // SEE this pair for the ones after it
			}
		}
		return list;
	}

	public static void main(String[] args) {
		int[][] pairs = { { 11, 20 }, { 30, 40 }, { 5, 10 }, { 40, 30 }, { 10, 5 } };
		System.out.println(show(pairs(pairs))); // [[30, 40], [5, 10]]
	}

	// the pairs as text, e.g. [[30, 40], [5, 10]]
	private static String show(List<int[]> pairs) {
		List<String> out = new ArrayList<>();
		for (int[] p : pairs) {
			out.add(Arrays.toString(p));
		}
		return out.toString();
	}
}
