package com.svetanis.algorithms.slidingwindow.hashmap;

import static java.lang.Math.max;

import java.util.HashMap;
import java.util.Map;

// Most different values in any window of size k -- no LeetCode number; source unknown
//
// Input: an array a and a window size k.
// Return: the largest number of different values held by any k neighbouring elements of a.
// When no window of size k exists -- k <= 0 or k larger than the array -- it returns 0. That
// is an assumption: no statement for this problem was found to confirm it.
//
// The one idea: two neighbouring windows share all but two elements, so the map of value ->
// copies in the window is edited, not rebuilt: a[i - k] goes out on the left and a[i] comes
// in on the right. A value whose last copy leaves has its key removed, so the map's size is
// always the number of different values in the current window, and the largest size seen is
// the answer.
//
// Time: O(n) -- each element enters and leaves the map once, O(1) per map operation on average.
// Space: O(k) -- the map holds at most k keys.
//
// Siblings:
//   MaxUnique -- the same problem and the same window, line for line; it reads a[i - k]'s
//     count from the map twice where this file reads it once.
//   CountDistinctElementsInWindow -- the same window, returning the number of different values
//     in every window instead of the largest.
//   CountKLenSubStrNoRepeatedChars (1100) -- a window of size k on a string, counting the
//     windows whose k characters are all different.

public final class MaxNumOfDistinctElementsInWindow {

	public static int maxDistinct(int[] a, int k) {
		if (k <= 0 || k > a.length) { // EARLY: no window of size k exists
			return 0;
		}

		Map<Integer, Integer> map = new HashMap<>(); // value -> copies in the window
		for (int i = 0; i < k; i++) { // FILL the first window, 0 .. k - 1
			int freq = map.getOrDefault(a[i], 0);
			map.put(a[i], freq + 1); // ADD a[i]
		}

		int count = map.keySet().size(); // COUNT the different values in the first window
		int max = count;

		for (int i = k; i < a.length; i++) { // SLIDE: a[i] comes in, a[i - k] goes out
			int f = map.get(a[i - k]);
			if (f == 1) { // OUT: a[i - k] was the last copy; the key goes
				map.remove(a[i - k]);
				count--;
			} else {
				map.put(a[i - k], f - 1); // OUT: one copy fewer
			}

			int freq = map.getOrDefault(a[i], 0);
			map.put(a[i], freq + 1); // IN a[i]
			count = map.keySet().size(); // COUNT: one key per different value in the window
			max = max(max, count); // RECORD the best window so far
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a1 = { 5, 3, 5, 2, 3, 2 };
		System.out.println(maxDistinct(a1, 3)); // 3

		int[] a2 = { 5, 5, 5, 5, 5, 5 };
		System.out.println(maxDistinct(a2, 3)); // 1

		int[] a3 = { 1, 2, 1, 3, 4, 2, 3 };
		System.out.println(maxDistinct(a3, 4)); // 4
		System.out.println(maxDistinct(a3, 0)); // 0
		System.out.println(maxDistinct(a3, 8)); // 0
	}
}
