package com.svetanis.algorithms.slidingwindow.fixed;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 2461. Maximum Sum of Distinct Subarrays With Length K
//
// Takes nums as a and k. Returns the largest sum of k consecutive numbers that are all
// different from each other, 0 if no such window exists, and -1 if a is shorter than k.
// The sum is a long: k and each value both reach 1e5, so a window can total 1e10.
//
// maxSumMap: a window of k numbers is all distinct exactly when a count map of it has k
// keys. One loop grows the window to k, measures it, then takes the leftmost number out,
// deleting its key when the count reaches 0.
// maxSum: a set never holds a duplicate, so the window shrinks from the left until the
// number entering is not in it, or until it is no longer than k; a window that is then
// exactly k long is all distinct by construction, and is measured.
//
// Time: O(n) for both -- each number enters the window once and leaves at most once, each
//   set or map operation O(1) on average
// Space: O(k) for both -- the set or map holds only the window
//
// Sibling: MaxSumDistinctSubArraySizeK is the count map with the first window built by a
// helper before the loop, over a List.

public final class MaxSumDistinctSubArraySizeKSubmit {

	public static long maxSum(int[] a, int k) {
		int n = a.length;
		if (n < k) {
			return -1;
		}
		long max = 0;
		long sum = 0;
		int left = 0;
		Set<Integer> set = new HashSet<>(); // the window's numbers, except the one entering
		for (int right = 0; right < n; right++) {
			int curr = a[right];
			sum += curr; // ADD
			while (left < right && set.contains(curr) || right - left + 1 > k) { // SHRINK: a duplicate of curr, or longer than k
				sum -= a[left];
				set.remove(a[left]);
				left++;
			}
			if (right - left + 1 == k) { // MEASURE: only a window exactly k long
				max = Math.max(max, sum);
			}
			set.add(curr); // ADD: curr joins the set after the duplicate test
		}
		return max;
	}

	public static long maxSumMap(int[] a, int k) {
		int n = a.length;
		if (n < k) {
			return -1;
		}
		long max = 0;
		long sum = 0;
		int left = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int right = 0; right < n; right++) {
			int curr = a[right];
			sum += curr; // IN
			map.merge(curr, 1, Integer::sum);
			if (right - left + 1 == k) { // the window has filled itself
				if (map.size() == k) {
					max = Math.max(max, sum); // RECORD: k keys means k different numbers
				}
				int first = a[left];
				sum -= first; // OUT
				map.merge(first, -1, Integer::sum);
				if (map.get(first) == 0) {
					map.remove(first); // REMOVE: the key goes, so size() counts distinct values
				}
				left++;
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 5, 4, 2, 9, 9, 9 };
		System.out.println(maxSum(a1, 3)); // 15
		int[] a2 = { 4, 4, 4 };
		System.out.println(maxSum(a2, 3)); // 0
	}
}
