package com.svetanis.algorithms.matrix;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 2975. Maximum Square Area by Removing Fences From a Field
//
// Input: m, n -- a field with corners (1, 1) and (m, n); hFences -- the rows of horizontal fences
// running across it; vFences -- the columns of vertical fences. The four border fences, at rows 1
// and m and columns 1 and n, cannot be removed; any other fence can. Returns the largest area of a
// square field left after removing some fences, modulo 10^9 + 7, or -1 when no square is possible.
// On LeetCode: 3 <= m, n <= 10^9, 1 <= hFences.length, vFences.length <= 600, 1 < hFences[i] < m,
// 1 < vFences[i] < n, the values of each array distinct.
//
// The one idea: a field is bounded by two horizontal fences still standing, top and bottom, and
// two vertical ones, left and right. Any fence between them can be removed, so any two horizontal
// positions -- the border rows included -- can be a field's top and bottom, and its height is
// their difference; the same for widths. A square needs a height that is also a width. So every
// difference between two vertical positions goes into a set, then every difference between two
// horizontal positions is tried against it, and the largest one found is the side. The side can
// be close to 10^9, so it is held in a long and squared before the modulo.
//
// Time: O(h^2 + v^2) -- every pair of positions on each side, h = hFences.length + 2,
// v = vFences.length + 2; sorting costs less.
// Space: O(h + v^2) -- only the vertical gaps go into a set.

public final class MaxAreaSquare {

	private static final int MOD = (int) 1e9 + 7;

	public static int maxSquareArea(int m, int n, 
			int[] hFences, int[] vFences) {
		int[] hpositions = positions(hFences, m); // ROWS a field's top or bottom can sit on
		int[] vpositions = positions(vFences, n); // COLUMNS a field's left or right can sit on
		Set<Integer> vedges = edges(vpositions); // WIDTHS: every possible field width
		long max = 0;
		for (int i = 0; i < hpositions.length; i++) {
			for (int j = i + 1; j < hpositions.length; j++) { // EVERY pair of horizontal positions
				int height = hpositions[j] - hpositions[i];
				if (vedges.contains(height)) { // SQUARE: this height is also a width
					max = Math.max(max, height);
				}
			}
		}
		return max == 0 ? -1 : (int) ((max * max) % MOD); // SQUARE in a long, then the modulo
	}

	private static int[] positions(int[] fences, int boundary) {
		int n = fences.length;
		int[] positions = new int[n + 2];
		positions[0] = 1; // BORDER at 1
		for (int i = 0; i < fences.length; i++) {
			positions[i + 1] = fences[i];
		}
		positions[n + 1] = boundary; // BORDER at m or n
		Arrays.sort(positions); // SORT: a later position minus an earlier one is > 0
		return positions;
	}

	private static Set<Integer> edges(int[] positions) {
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < positions.length; i++) {
			for (int j = i + 1; j < positions.length; j++) {
				set.add(positions[j] - positions[i]); // GAP between two fences kept standing
			}
		}
		return set;
	}

	public static void main(String[] args) {
		int[] hfences1 = { 2, 3 };
		int[] vfences1 = { 2 };
		System.out.println(maxSquareArea(4, 3, hfences1, vfences1)); // 4

		int[] hfences2 = { 2 };
		int[] vfences2 = { 4 };
		System.out.println(maxSquareArea(6, 7, hfences2, vfences2)); // -1
	}
}