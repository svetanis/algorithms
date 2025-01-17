package com.svetanis.algorithms.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 15. 3Sum
// com.svetanis.datastructures.array.triplet.AllUniqueTripletsGivenSumSorted

public final class ThreeSum15 {
	// Time complexity: O(n^2)

	public static List<List<Integer>> triplets(int[] a) {
		Arrays.sort(a);
		int n = a.length;
		List<List<Integer>> list = new ArrayList<>();
		for (int i = 0; i < n - 2 && a[i] <= 0; ++i) {
			if (i > 0 && a[i] == a[i - 1]) {
				continue;
			}
			int left = i + 1;
			int right = n - 1;
			while (left < right) {
				int sum = a[i] + a[left] + a[right];
				if (sum < 0) {
					left++;
				} else if (sum > 0) {
					right--;
				} else {
					list.add(Arrays.asList(a[i], a[left], a[right]));
					// skip duplicates on the left
					while (left < right && a[left] == a[left + 1]) {
						left++;
					}
					// skip duplicates on the right
					while (left < right && a[right] == a[right - 1]) {
						right--;
					}
					left++;
					right--;
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		int[] a = { -1, 0, 1, 2, -1, -4 };
		System.out.println(triplets(a)); // [[-1,-1,2],[-1,0,1]]

		int[] a1 = { 0, 1, 1 };
		System.out.println(triplets(a1)); // []

		int[] a2 = { 0, 0, 0 };
		System.out.println(triplets(a2)); // [[0,0,0]]
	}
}