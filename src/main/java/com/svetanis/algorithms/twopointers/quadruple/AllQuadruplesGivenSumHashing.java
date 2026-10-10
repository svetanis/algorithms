package com.svetanis.algorithms.twopointers.quadruple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// All unique quadruples with a given sum, by hashing pair sums -- the same problem as 18. 4Sum
//
// Input: an unsorted array of integers, repeats allowed, and a target k.
// Return: every distinct quadruple of VALUES from four different positions summing to k,
// each listed once, its values in ascending order.
//
// The one idea: a quadruple is two pairs. Walk every pair of positions (i, j) once; a
// map from pair sum to EVERY earlier pair with that sum gives all the partners for
// (i, j) at once -- the pairs summing to k - a[i] - a[j]. A partner sharing a position with
// (i, j) is skipped. Every quadruple p < q < r < s is found when (r, s) is reached, because
// (p, q) came earlier and is in the map. The same values can be met through other splits
// and other positions, so each quadruple is sorted and a set keeps one copy. Sums are
// taken in long: four values near 10^9 pass 2^31.
//
// Siblings -- the same problem:
//   twopointers.quadruple.AllQuadruplesGivenSum       -- sort, fix two, converge on the rest; no set
//   twopointers.quadruple.AllQuadruplesGivenSumSubmit -- LC 18, the same with the pair search inline
//
// Time: O(n^2) when pair sums rarely repeat. Every lookup walks all earlier pairs with the
// needed sum, so many equal sums -- many equal numbers -- push it towards O(n^4).
// Space: O(n^2) for the map of pairs.

public final class AllQuadruplesGivenSumHashing {

	public static List<List<Integer>> quadruples(int[] a, int k) {
		int n = a.length;
		Map<Long, List<int[]>> map = new HashMap<>();            // pair sum -> every earlier pair (i, j)
		Set<List<Integer>> set = new LinkedHashSet<>();          // one copy of each quadruple
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {                    // FIX the pair (i, j)
				long sum = (long) a[i] + a[j];
				long target = k - sum;                           // the partner pair must sum to this
				for (int[] p : map.getOrDefault(target, List.of())) {
					if (!isCommon(i, j, p)) {                    // SKIP a partner sharing a position
						set.add(quadruple(a[i], a[j], a[p[0]], a[p[1]])); // FOUND
					}
				}
				map.computeIfAbsent(sum, s -> new ArrayList<>()).add(new int[] { i, j }); // RECORD (i, j) after its lookup
			}
		}
		return new ArrayList<>(set);
	}

	private static boolean isCommon(int i, int j, int[] p) {
		boolean one = i == p[0];
		boolean two = j == p[1];
		boolean three = i == p[1];
		boolean four = j == p[0];
		return one || two || three || four;
	}

	private static List<Integer> quadruple(int w, int x, int y, int z) {
		int[] values = { w, x, y, z };
		Arrays.sort(values);                                     // SORT: one spelling per quadruple
		return List.of(values[0], values[1], values[2], values[3]);
	}

	public static void main(String[] args) {
		int[] a = { 4, 1, 2, -1, 1, -3 };
		System.out.println(quadruples(a, 1)); // [[-3, -1, 1, 4], [-3, 1, 1, 2]]

		int[] a1 = { 2, 0, -1, 1, -2, 2 };
		System.out.println(quadruples(a1, 2)); // [[-1, 0, 1, 2], [-2, 0, 2, 2]]

		int[] a2 = { -3, 1, -1, 2, 0, 4 };
		System.out.println(quadruples(a2, 5)); // [[-1, 0, 2, 4]]

		int[] a3 = { 1_000_000_000, 1_000_000_000, 1_000_000_000, 1_000_000_000 };
		System.out.println(quadruples(a3, -294_967_296)); // [] -- in int the four would wrap to this target
	}
}
