package com.svetanis.algorithms.slidingwindow.array;

// 2962. Count Subarrays Where Max Element Appears at Least K Times
//
// Input: an array a and a number k.
// Return: how many subarrays hold the maximum of the WHOLE array at least k times, as a
// long -- there can be about n^2/2 of them. For k <= 0 every subarray counts.
//
// The maximum is fixed before the loop starts, so the window only counts copies of one
// known value, and holding k copies survives growth: a longer subarray holds at least as
// many.
//
// The one idea (countSubArr): count by start. Once a[left..right] holds k copies, every
// end from right to n - 1 gives a valid subarray starting at left: n - right of them,
// added in one step. Then left moves on, and the loop keeps doing that while the window
// still holds k copies. Each start is counted once, at the first end that completes it.
//
// The one idea (countSubArr2): the same count with the loops swapped. For each left,
// right (one past the window's last index) grows until a[left..right - 1] holds k copies;
// then the ends right - 1 .. n - 1 all work: n - right + 1 of them. When right reaches n
// short of k copies, no later start can do better, so the loop stops.
//
// Time: O(n) for both -- left and right only move forward
// Space: O(1) -- the maximum, one copy count and two indices
//
// Siblings: no other file solves 2962. The two methods above are its two versions.
// string/CountSubStrs3Chars (1358) makes the same "count the ends" move on a different
// condition. CountGoodSubArrays (2537) is the same "at least" count by end instead.

public final class CountSubArrsMaxAtLeastK {

	public static long countSubArr(int[] a, int k) {
		int n = a.length;
		if (n == 0) {
			return 0;
		}
		int max = max(a);
		int left = 0;
		int maxCount = 0;
		long subArrCount = 0;
		for (int right = 0; right < n; right++) {
			if (a[right] == max) {
				maxCount++; // ADD: one more copy of the maximum
			}
			while (left <= right && maxCount >= k) { // SHRINK while valid
				subArrCount += n - right; // COUNT: ends right .. n - 1, all starting at left
				if (a[left] == max) {
					maxCount--; // REMOVE: a[left] leaves
				}
				left++;
			}
		}
		return subArrCount;
	}

	// seeded with a[0], so an all-negative array still finds its own
	// maximum among its elements
	private static int max(int[] a) {
		int max = a[0];
		for (int num : a) {
			max = Math.max(max, num);
		}
		return max;
	}

	public static long countSubArr2(int[] a, int k) {
		int n = a.length;
		if (n == 0) {
			return 0;
		}
		if (k <= 0) {
			return (long) n * (n + 1) / 2; // RETURN: every subarray holds at least 0 copies
		}
		int max = max(a);
		int right = 0;
		int maxCount = 0;
		long subArrCount = 0;
		for (int left = 0; left < n; left++) {
			while (right < n && maxCount < k) { // ADD until a[left..right - 1] holds k copies
				if (a[right] == max) {
					maxCount++;
				}
				right++;
			}
			if (maxCount < k) {
				break; // STOP: right reached the end short of k copies
			}
			subArrCount += n - right + 1; // COUNT: ends right - 1 .. n - 1, all starting at left
			if (a[left] == max) {
				maxCount--; // REMOVE: a[left] leaves before left moves on
			}
		}
		return subArrCount;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3, 2, 3, 3 };
		System.out.println(countSubArr(a1, 2)); // 6
		int[] a2 = { 1, 4, 2, 1 };
		System.out.println(countSubArr(a2, 3)); // 0
	}
}
