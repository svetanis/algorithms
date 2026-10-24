package com.svetanis.algorithms.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 1338. Reduce Array Size to The Half
//
// Input: an array a. Choose a set of values and remove every copy of each of them from a. Returns
// the size of the smallest set that removes at least half of the elements. On LeetCode:
// 2 <= a.length <= 10^5, a.length is even, 1 <= a[i] <= 10^5.
//
// The one idea: removing a value removes all of its copies, so one value is worth its count --
// how many times it occurs. Which value it is no longer matters, only the counts. To reach half
// with as few values as possible, take the values with the most copies first. Why that is safe:
// any s values together remove at most the s largest counts added up, so if the s largest counts
// do not reach half, no set of s values does. The walk adds counts from the largest down and
// stops the first time the removed total reaches half of a.length.
//
// The two methods: minSetSize counts into an array indexed by value, of size max + 1, and sorts
// all of those counts, zeros included; minSetSize2 counts in a HashMap and sorts only the counts
// of the values that occur, largest first.
//
// Time: minSetSize O(n + M log M) -- M is the largest value; it sorts M + 1 counts. minSetSize2
// O(n log n) -- it sorts at most n counts.
// Space: minSetSize O(M) -- the count array; minSetSize2 O(n) -- the map and the list of counts.

public final class ReduceArrayToHalf {

	public static int minSetSize(int[] a) {
		int max = 0;
		for (int num : a) {
			max = Math.max(max, num); // MAX value, to size the count array
		}
		int[] counts = new int[max + 1]; // COUNTS: counts[v] = copies of v
		for (int num : a) {
			counts[num]++;
		}
		Arrays.sort(counts); // SORT: the largest counts go to the right; which value owns each no longer matters
		int size = 0; // SET size so far
		int count = 0; // REMOVED elements so far
		for (int i = max;; i--) { // WALK from the largest count down; the counts add up to a.length, so it returns
			if (counts[i] > 0) {
				count += counts[i]; // TAKE the value with the next largest count
				size += 1;
				if (count * 2 >= a.length) { // HALF removed
					return size; // RETURN
				}
			}
		}
	}

	public static int minSetSize2(int[] a) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int num : a) {
			map.merge(num, 1, Integer::sum); // COUNT the copies of each value
		}
		List<Integer> frequencies = new ArrayList<>(map.values()); // COUNTS only; the values no longer matter
		Collections.sort(frequencies, Comparator.reverseOrder()); // SORT: largest count first
		int size = 0; // SET size so far
		int count = 0; // REMOVED elements so far
		while (2 * count < a.length || size > frequencies.size()) { // UNTIL half is removed
			count += frequencies.get(size); // TAKE the value with the next largest count
			size += 1;
		}
		return size; // RETURN
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 3, 3, 3, 5, 5, 5, 2, 2, 7 };
		int[] a2 = { 7, 7, 7, 7, 7, 7 };

		System.out.println(minSetSize(a1)); // 2
		System.out.println(minSetSize(a2)); // 1
	}
}
