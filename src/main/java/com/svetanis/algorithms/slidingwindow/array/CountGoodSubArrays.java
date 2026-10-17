package com.svetanis.algorithms.slidingwindow.array;

import java.util.HashMap;
import java.util.Map;

// 2537. Count the Number of Good Subarrays
//
// Input: an array a and a number k.
// Return: how many subarrays are good, as a long. A subarray is good when it holds at
// least k pairs of positions i < j with a[i] == a[j]. For k <= 0 every subarray is good.
//
// Being good survives growth: adding elements to a good subarray only adds pairs. So for
// a fixed end right, the good starts are 0 .. some last start, and the loop keeps left at
// that last start. A new element a[right] makes one pair with each copy of its value
// already inside. Dropping a[left] removes one pair with each other copy of its value,
// count - 1 of them; left moves on only while the window without a[left] stays good, so
// it stops one step before the window turns bad. Then, if the window is good, starts
// 0 .. left all are: left + 1 of them.
//
// Time: O(n) -- right and left only move forward; each map update is O(1) on average
// Space: O(n) -- a count for each distinct value in the window
// pairs never exceeds k + 2n, so it fits in an int unless k is near the int limit.
//
// Siblings: no other file solves 2537. CountSubArrsMaxAtLeastK (2962) is the same
// "at least" count shrinking while valid and counting ends (n - right) instead of
// starts.

public final class CountGoodSubArrays {

	public static long countGood(int[] a, int k) {
		if (k <= 0) {
			return (long) a.length * (a.length + 1) / 2; // RETURN: every subarray holds at least 0 pairs
		}
		Map<Integer, Integer> map = new HashMap<>();
		long total = 0;
		int left = 0;
		int pairs = 0;
		for (int right = 0; right < a.length; right++) {
			int curr = a[right];
			pairs += map.getOrDefault(curr, 0); // ADD: a[right] pairs with each copy already inside
			map.merge(curr, 1, Integer::sum);
			while (pairs - map.get(a[left]) + 1 >= k) { // SHRINK while the window without a[left] is still good
				pairs -= map.merge(a[left], -1, Integer::sum);
				left++;
			}
			if (pairs >= k) {
				total += left + 1; // COUNT: starts 0 .. left, all ending at right
			}
		}
		return total;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 1, 1, 1, 1 };
		System.out.println(countGood(a1, 10)); // 1
		int[] a2 = { 3, 1, 4, 3, 2, 2, 4 };
		System.out.println(countGood(a2, 2)); // 4
	}
}
