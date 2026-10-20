package com.svetanis.algorithms.slidingwindow.hashmap;

import static java.lang.Math.max;

import java.util.HashSet;
import java.util.Set;

// Maximum Sum Unique-Window in b[] Based on a[] -- no LeetCode number
//
// Given two arrays a and b of equal size n, find the maximum sum possible of a window in b
// such that the elements at the same window positions in a are all unique.
//
//   a = [0, 1, 2, 3, 0, 1, 4], b = [9, 8, 1, 2, 3, 4, 5] -> 20, the window [9, 8, 1, 2]
//     in b, where a holds [0, 1, 2, 3].
//   a = [0, 1, 2, 0, 2], b = [5, 6, 7, 8, 2] -> 21, the window [6, 7, 8] in b, where a
//     holds [1, 2, 0].
//
// Input: two arrays of the same length, a (the keys) and b (the values).
// Return: the largest sum b[i] + ... + b[j] over the windows i..j in which a[i..j] holds no
// value twice; 0 for empty arrays.
//
// The one idea: the set holds the keys of the window left..right. Before a[right] joins, the
// window drops from the left until a[right]'s key is no longer in it, so left..right is the
// longest window ending at right with no repeated key. Every other such window ending at right
// is a piece of it, and with b non-negative a piece never sums to more -- so measuring this
// one window at each right finds the answer.
//
// The statement gives no limits on the values. Non-negative b is read from its examples,
// where every value in b is positive, and the method depends on it. With a negative value a
// shorter window can sum to more: a = {2, 1, 2, 0}, b = {-1, 8, -6, -7} returns 7 (window 0..1) where window 1..1 alone
// is 8. And max starts at 0, the empty window, so an all-negative b returns 0.
//
// Time: O(n) -- right and left each move forward at most n times; set operations are O(1) on
// average.
// Space: O(n) -- the set holds the window's keys, at most n.
//
// Siblings:
//   fixed.MaxSumDistinctSubArraySizeK (2461) -- the same "no value twice" window, with one
//     array as both keys and values and the window length fixed at k.
//   string.LongestDistinctSubStrLen (3) -- the same set window on a string, measuring the
//     longest window instead of a sum from a second array.

public final class MaxPossibleSumInWindow {

	public static int maxSum(int[] a, int[] b) {
		int n = a.length;
		int sum = 0;
		int max = 0;
		int left = 0;

		Set<Integer> set = new HashSet<>(); // the keys a[left..right - 1]
		for (int right = 0; right < n; right++) {
			while (set.contains(a[right])) { // SHRINK while a[right]'s key is already in the window
				set.remove(a[left]); // DROP a[left]'s key
				sum -= b[left]; // DROP its value
				left++;
			}
			set.add(a[right]); // ADD a[right]'s key
			sum += b[right]; // ADD its value
			max = max(max, sum); // MEASURE: no key repeats in left..right
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a = { 0, 1, 2, 3, 0, 1, 4 };
		int[] b = { 9, 8, 1, 2, 3, 4, 5 };
		System.out.println(maxSum(a, b)); // 20

		int[] a1 = { 0, 1, 2, 0, 2 };
		int[] b1 = { 5, 6, 7, 8, 2 };
		System.out.println(maxSum(a1, b1)); // 21

		int[] a2 = {};
		int[] b2 = {};
		System.out.println(maxSum(a2, b2)); // 0
	}
}
