package com.svetanis.algorithms.matrix;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 2975. Maximum Square Area by Removing Fences From a Field

public final class MaxAreaSquare {
	// Time Complexity: O(h^2 + v^2)
	// Space Complexity: O(h^2 + v^2)

	private static final int MOD = (int) 1e9 + 7;

	public static int maxSquareArea(int m, int n, 
			int[] hFences, int[] vFences) {
		int[] hpositions = positions(hFences, m);
		int[] vpositions = positions(vFences, n);
		Set<Integer> vedges = edges(vpositions);
		long max = 0;
		for (int i = 0; i < hpositions.length; i++) {
			for (int j = i + 1; j < hpositions.length; j++) {
				int height = hpositions[j] - hpositions[i];
				if (vedges.contains(height)) {
					max = Math.max(max, height);
				}
			}
		}
		return max == 0 ? -1 : (int) ((max * max) % MOD);
	}

	private static int[] positions(int[] fences, int boundary) {
		int n = fences.length;
		int[] positions = new int[n + 2];
		positions[0] = 1;
		for (int i = 0; i < fences.length; i++) {
			positions[i + 1] = fences[i];
		}
		positions[n + 1] = boundary;
		Arrays.sort(positions);
		return positions;
	}

	private static Set<Integer> edges(int[] positions) {
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < positions.length; i++) {
			for (int j = i + 1; j < positions.length; j++) {
				set.add(positions[j] - positions[i]);
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