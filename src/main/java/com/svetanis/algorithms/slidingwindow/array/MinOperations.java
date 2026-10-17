package com.svetanis.algorithms.slidingwindow.array;

import java.util.Arrays;

// 1658. Minimum Operations to Reduce X to Zero 

// Takes a (every number at least 1) and x. Numbers are removed one at a
// time from either end of a; returns the fewest removals whose numbers
// add up to exactly x, or -1 if no removals do.

// Whatever is removed from the two ends, what stays is one unbroken
// block in the middle, and it sums to total - x. So the fewest removals
// is n minus the LONGEST block summing to exactly total - x. With every
// number positive, a window finds it: shrink while the sum is too big,
// and measure when it is exactly right. When x is the whole total the
// block is empty and every number is removed; when x is more than the
// total, total - x is negative, nothing matches, and the answer is -1.

// Time: O(n) -- one pass for the total, then each index enters the
//   window once and leaves at most once
// Space: O(1) -- the running sum, the target and two indices

// Sibling: slidingwindow.fixed.MaxScore (1423) also takes from the two
// ends and turns it into the block left in the middle, but there the
// count taken is fixed at k, so its window has a fixed size.

public final class MinOperations {
	// Time Complexity: O(n)

	public static int minOperations(int[] a, int x) {
		int n = a.length;
		int sum = 0;
		int left = 0;
		int min = Integer.MAX_VALUE;
		int target = Arrays.stream(a).sum() - x; // what the block left in the middle must sum to
		for (int right = 0; right < n; right++) {
			sum += a[right]; // ADD
			while (left <= right && sum > target) { // SHRINK: while the block sums to too much
				sum -= a[left++]; // REMOVE
			}
			if (sum == target) {
				int len = right - left + 1; // MEASURE: the block that stays
				min = Math.min(min, n - len); // everything else is removed
			}
		}
		return min == Integer.MAX_VALUE ? -1 : min; // RETURN: -1 when no block ever matched
	}

	public static void main(String[] args) {
		int[] a = { 1, 1, 4, 2, 3 };
		System.out.println(minOperations(a, 5)); // 2
		int[] a1 = { 5, 6, 7, 8, 9 };
		System.out.println(minOperations(a1, 4)); // -1
		int[] a2 = { 3, 2, 20, 1, 1, 3 };
		System.out.println(minOperations(a2, 10)); // 5
	}
}
