package com.svetanis.algorithms.slidingwindow.hashmap;

import java.util.TreeMap;

// 1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
//
// Input: an array a and a number limit, assumed limit >= 0.
// Return: the length of the longest contiguous subarray in which any two values differ by at
// most limit; 0 for an empty array.
//
// The one idea: every two values in a subarray differ by at most limit exactly when its
// largest and smallest values do. Dropping the leftmost element can only lower the largest
// value or raise the smallest, so a subarray that passes still passes after it -- which makes
// it a window. The TreeMap holds the window's values, sorted, each with how many copies of it
// the window holds, so firstKey is the smallest value and lastKey the largest. The window
// grows by a[right], drops from the left until largest - smallest <= limit, and its length
// is taken.
// A negative limit is not met even by one element: the shrink empties the map, and lastKey
// on an empty map throws NoSuchElementException.
//
// Time: O(n log n) -- each index enters and leaves the window once, but every put, remove,
// firstKey and lastKey on the TreeMap costs O(log n). A pair of monotonic deques, one for the
// largest value and one for the smallest, would make this O(n).
// Space: O(n) -- the map holds the whole window in the worst case.
//
// Siblings:
//   ContinuousSubarrays (2762) -- the same TreeMap window with the limit fixed at 2, counting
//     the subarrays (right - left + 1 for each right) instead of measuring the longest.
//   queue.SlidingWindowMaxDeque (239) -- the monotonic deque that keeps a window's largest
//     value; two of them replace the TreeMap here.

public final class LongestSubArrAbsDiffLessThanK {

	public static int longestSubArr(int[] a, int limit) {
		int max = 0;
		int left = 0;
		TreeMap<Integer, Integer> map = new TreeMap<>(); // value -> copies in the window, sorted
		for (int right = 0; right < a.length; right++) {
			map.put(a[right], map.getOrDefault(a[right], 0) + 1); // ADD a[right]
			while (map.lastKey() - map.firstKey() > limit) { // SHRINK while max - min > limit
				map.put(a[left], map.get(a[left]) - 1); // DROP one copy of a[left]
				if (map.get(a[left]) == 0) { // GONE: that was its last copy
					map.remove(a[left]);
				}
				left++;
			}
			max = Math.max(max, right - left + 1); // MEASURE: left..right passes
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a1 = { 8, 2, 4, 7 };
		System.out.println(longestSubArr(a1, 4)); // 2

		int[] a2 = { 10, 1, 2, 4, 7, 2 };
		System.out.println(longestSubArr(a2, 5)); // 4

		int[] a3 = { 4, 2, 2, 2, 4, 4, 2, 2 };
		System.out.println(longestSubArr(a3, 0)); // 3

		int[] a4 = {};
		System.out.println(longestSubArr(a4, 0)); // 0
	}
}
