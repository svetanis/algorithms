package com.svetanis.algorithms.twopointers.quadruple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Two pairs with equal products
//
// Input: an array of DISTINCT integers.
// Return: quadruples [a, b, c, d] -- (a, b) one pair of numbers, (c, d) an earlier pair --
// with a * b == c * d.
//
// The one idea: walk every pair once, and let a map remember, for each product, the FIRST
// pair that made it. A later pair with the same product is reported against that first
// pair. So a product made by m pairs gives m - 1 quadruples, not every one of the
// m * (m - 1) / 2 ways to choose two of those pairs.
// With distinct values, two pairs with the same nonzero product share no number:
// a * b == a * c forces b == c. A zero breaks that -- (0, x) and (0, y) both make 0, and the
// quadruple holds the one 0 twice.
//
// Time: O(n^2) -- every pair once, a map lookup each.
// Space: O(n^2) for the map of products.

public final class AllQuadruplesProductHashing {

	public static List<List<Integer>> quadruples(int[] a) {
		int n = a.length;
		Map<Integer, int[]> map = new HashMap<>();                       // product -> the first pair that made it
		Set<List<Integer>> set = new LinkedHashSet<>();
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {                            // FIX the pair (a[i], a[j])
				int prod = a[i] * a[j];
				if (map.containsKey(prod)) {
					int[] p = map.get(prod);
					set.add(List.of(a[i], a[j], p[0], p[1]));            // FOUND: reported against the first pair
				} else {
					map.put(prod, new int[] { a[i], a[j] });             // RECORD the first pair for this product
				}
			}
		}
		return new ArrayList<>(set);
	}

	public static void main(String[] args) {
		int[] a = { 3, 4, 7, 1, 2, 9, 8 };
		System.out.println(quadruples(a)); // [[1, 8, 4, 2]]

		int[] a1 = { 1, 6, 3, 9, 2, 10 };
		System.out.println(quadruples(a1)); // [[3, 2, 1, 6], [9, 2, 6, 3]]

		int[] a2 = { 1, 2, 3, 4, 6, 12 };
		System.out.println(quadruples(a2)); // [[2, 3, 1, 6], [2, 6, 1, 12], [3, 4, 1, 12], [4, 6, 2, 12]] -- (3, 4) and (2, 6) are both reported against (1, 12), not against each other
	}
}
