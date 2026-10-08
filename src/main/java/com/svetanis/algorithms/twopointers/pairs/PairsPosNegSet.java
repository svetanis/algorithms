package com.svetanis.algorithms.twopointers.pairs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Pairs of a Number and Its Negative -- a set of the negatives
//
// Input: a list of distinct integers.
// Return: every pair {x, -x}, x > 0, where both x and -x occur, in the order of the
// positive numbers in the list.
// Convention the code follows: the values are distinct. With repeats a positive is
// listed once per copy, while the set holds each negative once: {3, 3, -3} lists
// {3, -3} twice. The source statement would settle whether values can repeat.
//
// The one idea: split the list into the non-negatives and a set of the negatives; the
// only partner of x is -x, and the set says in one lookup whether it occurs. 0 is never
// paired, since -0 is 0 and 0 does not go into the set.
//
// Sibling: PairsPosNegHashing -- counts absolute values instead; lists the pairs in the
//   order the first of each two appears
//
// Time: O(n) -- one pass to split, one lookup per non-negative.
// Space: O(n) -- the list and the set.

public final class PairsPosNegSet {

	public static List<int[]> pairs(List<Integer> list) {
		List<Integer> positive = new ArrayList<>();
		Set<Integer> negative = new HashSet<>();
		for (int x : list) {
			if (x >= 0) {
				positive.add(x);                   // the non-negatives, in list order
			} else {
				negative.add(x);                   // SEE every negative
			}
		}
		List<int[]> pairs = new ArrayList<>();
		for (int pos : positive) {
			int neg = -1 * pos;                    // the only partner pos can have
			if (negative.contains(neg)) {
				pairs.add(new int[] { pos, neg }); // FOUND: -pos occurs too
			}
		}
		return pairs;
	}

	public static void main(String[] args) {
		List<Integer> list = List.of(4, 8, 9, -4, 1, -1, -8, -9);
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
