package com.svetanis.algorithms.slidingwindow.array;

import static java.lang.Math.min;

// 209. Minimum Size Subarray Sum -- the follow-up, O(n log n)

// Given an array of positive integers and a number target,
// find the length of the smallest contiguous subarray
// whose sum is greater than or equal to target.
// Return 0 if no such subarray exists.
// Takes the numbers (0s are fine, negatives are not) and the
// target >= 1. An empty input returns 0.

// With no negative numbers the running totals never go down. So for
// each start, the pieces start..end read "too small" up to some end and
// "reaches the target" from there on: binary-search that first end.
// prefix has a 0 in front, so prefix[k] is the total of the first k
// numbers, and the piece start..end sums to
// prefix[end + 1] - prefix[start] with no branch for start == 0.
// The loop always hands back one end and cannot tell whether it is a
// "reaches" one, so the same test is asked once more after it.

// Time: O(n log n) -- one search over at most n ends per start
// Space: O(n) -- the prefix row

// Siblings:
//   MinSizeSubArraySum -- the O(n) shrinking window; the same fact
//     read as "adding never lowers the sum, removing never raises it".
//   todo.slidingwindow.MinSizeSubArraySumAtLeastK (862) -- the same
//     question with negative numbers allowed; the running totals can
//     dip, so neither this search nor the window works.

public final class MinSizeSubArraySumBinarySearch {

	public static int minSum(int[] a, int target) {
		int n = a.length;
		int[] prefix = prefix(a); // BUILD: prefix[k] = total of the first k numbers
		int min = n + 1; // KEEP: n + 1 is a length no piece can have
		for (int i = 0; i < n; i++) { // WALK: each start i
			int left = i; // box numbers: the ends i .. n-1
			int right = n - 1;
			while (left < right) { // SEARCH: the first end whose piece reaches the target
				int mid = left + (right - left) / 2;
				if (prefix[mid + 1] - prefix[i] >= target) {
					right = mid; // T: keep mid
				} else {
					left = mid + 1; // F: drop mid
				}
			}
			if (prefix[left + 1] - prefix[i] >= target) { // CHECK: the survivor may be an F
				min = min(min, left - i + 1); // MEASURE: the piece i .. left
			}
		}
		return min <= n ? min : 0; // REPORT: n + 1 was never beaten, so no piece
	}

	private static int[] prefix(int[] a) {
		int n = a.length;
		int[] prefix = new int[n + 1]; // one more cell: the 0 in front
		for (int i = 1; i <= n; i++) {
			prefix[i] = prefix[i - 1] + a[i - 1]; // cell i adds the i-th number, a[i - 1]
		}
		return prefix;
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 3, 1, 2, 4, 3 };
		int[] a2 = { 1, 4, 4 };
		int[] a3 = { 1, 1, 1, 1, 1, 1, 1, 1 };
		System.out.println(minSum(a1, 7)); // 2
		System.out.println(minSum(a2, 4)); // 1
		System.out.println(minSum(a3, 11)); // 0
		System.out.println(minSum(new int[] { 1, 4, 45, 6, 0, 19 }, 51)); // 2 -- 45 + 6
	}
}
