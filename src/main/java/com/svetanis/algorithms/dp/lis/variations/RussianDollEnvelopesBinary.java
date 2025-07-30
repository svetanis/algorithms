package com.svetanis.algorithms.dp.lis.variations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 354. Russian Doll Envelopes

public final class RussianDollEnvelopesBinary {
	// Time Complexity: O(n log n)
	// Space Complexity: O(n)

	public static int rde(int[][] envelopes) {
		// sort dolls by width in ascending order
		// if widths are equal sort dolls by height
		Arrays.sort(envelopes, (a, b) -> a[0] == b[0] 
				? b[1] - a[1] : a[0] - b[0]);
		List<Integer> list = new ArrayList<>();
		list.add(envelopes[0][1]);
		for (int i = 1; i < envelopes.length; i++) {
			int height = envelopes[i][1];
			if (height > list.get(list.size() - 1)) {
				list.add(height);
			} else {
				int index = upperBound(list, height);
				list.set(index, height);
			}
		}
		return list.size();
	}

	private static int upperBound(List<Integer> list, int target) {
		int low = 0;
		int high = list.size();
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (list.get(mid) >= target) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

	public static void main(String[] args) {
		int[][] g1 = { { 5, 4 }, { 6, 4 }, { 6, 7 }, { 2, 3 } };
		System.out.println(rde(g1)); // 3: 2,3 -> 5,4 -> 6,7
		int[][] g2 = { { 1, 1 }, { 1, 1 }, { 1, 1 } };
		System.out.println(rde(g2)); // 1
	}
}