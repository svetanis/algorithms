package com.svetanis.algorithms.slidingwindow.hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Distinct values in every window of size k
//
// Input: an array a of n integers and a window size k.
// Return: one number per window of k consecutive positions, left to right: how many
// different values that window holds. There are n - k + 1 windows.
//
// The one idea: neighbouring windows share all but two positions -- the next window
// loses a[i - k] on the left and gains a[i] on the right. So keep a count map of the
// window (value -> how many times it occurs there) and change only those two entries
// per step. A value whose count drops to 0 is removed from the map, so the map's size
// is exactly the number of different values in the window.
//
// k <= 0 and k > n return an empty list. A window of k > n positions does not fit in
// the array. For k <= 0 the empty list rests on reading a window as holding at least
// one value; that is a choice, not taken from a problem statement.
//
// Time: O(n) -- the first loop fills k positions, and the second does constant map
// work for each of the other n - k.
// Space: O(k) -- the map holds at most k values; the result holds n - k + 1 numbers.
//
// Siblings: MaxNumOfDistinctElementsInWindow slides the same window and keeps only the
// largest count. CountKLenSubStrNoRepeatedChars (countSubStrNoRepeats2) slides the same
// count map over a string and counts the windows whose map has k keys.
// DistinctSubstrSize3 (slidingwindow/string) is that count with k fixed at 3.

public final class CountDistinctElementsInWindow {

	public static List<Integer> count(int[] a, int k) {
		List<Integer> list = new ArrayList<>();
		if (k <= 0 || k > a.length) { // EARLY: no window of k values fits
			return list;
		}
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < k; i++) {
			int freq = map.getOrDefault(a[i], 0);
			map.put(a[i], freq + 1); // ADD the first window's values
		}
		list.add(map.keySet().size()); // RECORD the first window

		for (int i = k; i < a.length; i++) {
			int f = map.get(a[i - k]); // OUT: a[i - k] leaves the window
			if (f == 1) {
				map.remove(a[i - k]); // DROP: its last copy left, so it no longer counts
			} else {
				map.put(a[i - k], f - 1);
			}

			int freq = map.getOrDefault(a[i], 0);
			map.put(a[i], freq + 1); // IN: a[i] joins the window

			list.add(map.keySet().size()); // RECORD: different values in a[i - k + 1 .. i]
		}
		return list; // RETURN
	}

	public static void main(String[] args) {
		int[] a = { 1, 2, 1, 3, 4, 2, 3 };
		System.out.println(count(a, 4)); // expected [3, 4, 4, 3]
		System.out.println(count(a, 1)); // expected [1, 1, 1, 1, 1, 1, 1]
		System.out.println(count(a, 7)); // expected [4]
		System.out.println(count(a, 8)); // expected []
		System.out.println(count(a, 0)); // expected []
	}
}
