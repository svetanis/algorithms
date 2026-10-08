package com.svetanis.algorithms.twopointers.pairs;

import static java.lang.Math.abs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Pairs of a Number and Its Negative -- counting absolute values
//
// Input: a list of distinct integers.
// Return: every pair {x, -x}, x > 0, where both x and -x occur, in the order in which
// the first of the two appears in the list.
// Convention the code follows: the values are distinct. Then x and -x are the only
// numbers with absolute value x, and a count of 2 means both occur. With repeats a count
// of 2 can also be two copies of x: {4, 4} lists {4, -4}. The source statement would
// settle whether values can repeat.
//
// The one idea: x and -x share one absolute value, so count the absolute values; an
// absolute value counted twice is a pair.
//
// Sibling: PairsPosNegSet -- a set of the negatives, looked up from each positive;
//   lists the pairs in the order of the positives
//
// Time: O(n) -- one pass to count, one over the counts.
// Space: O(n) -- the map.

public final class PairsPosNegHashing {

	public static List<int[]> pairs(List<Integer> list) {
		Map<Integer, Integer> map = new LinkedHashMap<>();    // |x| -> its count, in first-seen order
		for (int x : list) {
			map.put(abs(x), map.getOrDefault(abs(x), 0) + 1); // COUNT each absolute value
		}
		List<int[]> pairs = new ArrayList<>();
		for (Map.Entry<Integer, Integer> e : map.entrySet()) {
			if (e.getValue() == 2) {
				pairs.add(pair(e.getKey()));                  // FOUND: x and -x both occur
			}
		}
		return pairs;
	}

	private static int[] pair(int num) {
		int neg = -1 * num;
		return new int[] { num, neg };
	}

	public static void main(String[] args) {
		List<Integer> list = List.of(4, 8, 9, -4, 1, -1, -8, -9, 5);
		System.out.println(show(pairs(list))); // [[4, -4], [8, -8], [9, -9], [1, -1]]
	}

	// the pairs as text, e.g. [[4, -4], [8, -8]]
	private static String show(List<int[]> pairs) {
		List<String> out = new ArrayList<>();
		for (int[] p : pairs) {
			out.add(Arrays.toString(p));
		}
		return out.toString();
	}
}
