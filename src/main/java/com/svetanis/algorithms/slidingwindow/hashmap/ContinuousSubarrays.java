package com.svetanis.algorithms.slidingwindow.hashmap;

import java.util.TreeMap;

// 2762. Continuous Subarrays
//
// Input: an integer array a.
// Return: how many contiguous subarrays have every two of their values differ by at
// most 2 -- the same as max - min <= 2. A long, since n values have n(n+1)/2 subarrays.
//
// The one idea: if a subarray passes, so does every shorter subarray inside it --
// taking values away never raises the max or lowers the min. So for each right end,
// the passing subarrays ending there are exactly those starting at left .. right,
// where left is the first start that passes, and left only moves forward as right
// grows. A TreeMap from value to how many times it occurs in the window gives the
// window's min (firstKey) and max (lastKey); while they differ by more than 2, the
// leftmost value leaves. Then every start from left to right passes: right - left + 1
// subarrays, each counted once, at its right end.
//
// Time: O(n) -- right and left each move forward n times in total, so the inner while
// runs at most n times over the whole run. The map never holds more than 4 keys: a
// passing window spans at most 3 consecutive integers, plus the value just added. So
// each TreeMap operation is O(1).
// Space: O(1) -- at most 4 keys in the map, for the same reason.
//
// Siblings: LongestSubArrAbsDiffLessThanK is 1438, the same TreeMap window with the
// limit given as input instead of 2, measuring the longest window instead of counting.
// CountSubArrsProductLessThanK (slidingwindow/array) counts with the same
// right - left + 1 line, for a product below k.

public final class ContinuousSubarrays {

	public static long countContinuousSubarrays(int[] a) {
		int left = 0;
		long count = 0;
		TreeMap<Integer, Integer> map = new TreeMap<>();
		for (int right = 0; right < a.length; right++) {
			int curr = a[right];
			map.merge(curr, 1, Integer::sum); // ADD
			while (map.lastKey() - map.firstKey() > 2) { // SHRINK while max - min > 2
				int out = a[left]; // OUT: the leftmost value leaves
				map.merge(out, -1, Integer::sum);
				if (map.get(out) == 0) { // DROP its key, so firstKey and lastKey see only the window
					map.remove(out);
				}
				left++;
			}
			count += right - left + 1; // COUNT: starts left .. right, all ending at right
		}
		return count; // RETURN
	}

	public static void main(String[] args) {
		int[] a1 = { 5, 4, 2, 4 };
		System.out.println(countContinuousSubarrays(a1)); // 8
		int[] a2 = { 1, 2, 3 };
		System.out.println(countContinuousSubarrays(a2)); // 6
		int[] a3 = { 1, 4, 7 };
		System.out.println(countContinuousSubarrays(a3)); // 3
		int[] a4 = {};
		System.out.println(countContinuousSubarrays(a4)); // 0
	}
}
