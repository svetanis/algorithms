package com.svetanis.algorithms.greedy;

import java.util.Arrays;

// 3107. Minimum Operations to Make Median of Array Equal to K
//
// Input: an array a and a number k. One operation adds 1 to one element or takes 1 from it. The
// median is the middle element once a is sorted; when the length is even there are two middle
// elements and the larger one counts. Returns the fewest operations that make the median equal to
// k. On LeetCode: 1 <= a.length <= 2 * 10^5, 1 <= a[i] <= 10^9, 1 <= k <= 10^9. Sorts the
// caller's array in place.
//
// The one idea: sort, and call m = n / 2 the median's index. For the median to be k, at least
// m + 1 elements must end at k or below (the ones that fill indices 0..m of the sorted result),
// and at least n - m must end at k or above (indices m..n - 1). Say a[m] > k. Then too few
// elements are at k or below, and some must come down. Any m + 1 elements could be the ones
// brought down, but the m + 1 smallest -- a[0..m] -- are the cheapest, and each one is brought
// down exactly to k, the shortest move that does the job; the ones already at k or below cost
// nothing. Everything right of m is at least a[m] > k already, so the other condition holds for
// free. The sum is |a[m] - k| plus a[i] - k for each i < m with a[i] > k; since a is sorted,
// those i sit just left of m, and the walk left stops at the first a[i] <= k. a[m] < k is the
// mirror image: a[m..n - 1] are the cheapest to bring up, and the walk goes right. The sorted
// order says which elements must move, and each moves the least it can, so no choice is ever
// taken back.
//
// Time: O(n log n) -- the sort; the walk after it is at most n steps.
// Space: O(1) beyond the sort, which works in the caller's array.

public final class MinOperations {

	public static long minOperations(int[] a, int k) {
		Arrays.sort(a); // SORT: in place -- the caller's array comes back sorted
		int n = a.length;
		int mindex = n / 2; // MEDIAN: index n / 2, the larger middle when n is even
		long min = Math.abs(a[mindex] - k); // MOVE the median itself to k
		if (a[mindex] > k) { // TOO BIG: elements left of it that are above k come down to k
			for (int i = mindex - 1; i >= 0 && a[i] > k; i--) { // WALK left until an element is <= k
				min += a[i] - k; // LOWER a[i] to k
			}
		} else { // TOO SMALL: elements right of it that are below k come up to k
			for (int i = mindex + 1; i < n && a[i] < k; i++) { // WALK right until an element is >= k
				min += k - a[i]; // RAISE a[i] to k
			}
		}
		return min; // RETURN: a long -- up to 2 * 10^5 moves of up to 10^9 each
	}

	public static void main(String[] args) {
		int[] a = { 2, 5, 6, 8, 5 };
		System.out.println(minOperations(a, 4)); // 2
		System.out.println(minOperations(a, 7)); // 3

		int[] a1 = { 1, 2, 3, 4, 5, 6 };
		System.out.println(minOperations(a1, 4)); // 0
	}
}
